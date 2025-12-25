package clc65.quanggck.project_altp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import clc65.quanggck.project_altp.dao.QuestionDAO;
import clc65.quanggck.project_altp.model.Difficulty;
import clc65.quanggck.project_altp.model.Question;

public class PLayActivity extends AppCompatActivity {

    TextView edt_namequestion, tv_a, tv_b, tv_c, tv_d;
    TextView tv_currentquestion, tv_currentbonus;
    Button btn_pause;

    // --- [MỚI] KHAI BÁO BIẾN CHO TRỢ GIÚP ---
    Button btn_help_toggle, btn_5050, btn_ratechoose;
    TextView tv_count_5050, tv_count_rate;
    LinearLayout layout_lifelines;

    int count5050 = 2;       // Số lần dùng 50:50
    int countRateChoose = 2; // Số lần dùng Ý kiến khán giả
    // ----------------------------------------

    String playerName;
    QuestionDAO dao;
    List<Question> questionList;

    int currentIndex = 0;
    private String correctAnswer;
    private Question currentQuestion;
    int money = 0;

    private int tinhTien(int cauSo) {
        int[] moneyTable = {
                0,
                500, 1000, 2000, 3000, 5000,
                7500, 10000, 12500, 15000, 25000,
                50000, 100000, 200000, 500000, 1000000
        };
        if (cauSo >= moneyTable.length) return moneyTable[moneyTable.length - 1];
        if (cauSo < 0) return 0;
        return moneyTable[cauSo];
    }

    private void TimCT() {
        edt_namequestion = findViewById(R.id.edt_namequestion);
        tv_a = findViewById(R.id.tv_a);
        tv_b = findViewById(R.id.tv_b);
        tv_c = findViewById(R.id.tv_c);
        tv_d = findViewById(R.id.tv_d);
        tv_currentquestion = findViewById(R.id.tv_currentquestion);
        tv_currentbonus = findViewById(R.id.tv_currentbonus);
        btn_pause = findViewById(R.id.btn_pause);

        // --- [MỚI] ÁNH XẠ CÁC VIEW TRỢ GIÚP ---
        btn_help_toggle = findViewById(R.id.btn_help_toggle);
        layout_lifelines = findViewById(R.id.layout_lifelines);

        btn_5050 = findViewById(R.id.btn_5050);
        tv_count_5050 = findViewById(R.id.tv_count_5050);

        btn_ratechoose = findViewById(R.id.btn_ratechoose);
        tv_count_rate = findViewById(R.id.tv_count_rate);
    }

    private void KhoiTao() {
        dao = new QuestionDAO(this);
        currentIndex = 0;

        // --- [MỚI] Reset lại lượt chơi khi bắt đầu game ---
        count5050 = 2;
        countRateChoose = 2;
        updateHelpUI(); // Cập nhật text hiển thị

        loadQuestionForCurrentLevel();
        HienThi();
    }

    // --- [MỚI] Hàm cập nhật hiển thị số lượt còn lại ---
    private void updateHelpUI() {
        tv_count_5050.setText("Còn: " + count5050);
        tv_count_rate.setText("Còn: " + countRateChoose);

        // Làm mờ nút nếu hết lượt
        btn_5050.setEnabled(count5050 > 0);
        btn_5050.setAlpha(count5050 > 0 ? 1.0f : 0.5f);

        btn_ratechoose.setEnabled(countRateChoose > 0);
        btn_ratechoose.setAlpha(countRateChoose > 0 ? 1.0f : 0.5f);
    }

    private void loadQuestionForCurrentLevel() {
        Difficulty currentDiff = Difficulty.fromLevel(currentIndex + 1);
        questionList = dao.getQuestionsByDifficulty(currentDiff);
        if (questionList != null) {
            Collections.shuffle(questionList);
        }
    }

    private void HienThi() {
        if (questionList == null || questionList.isEmpty()) {
            KetThuc();
            return;
        }
        currentQuestion = questionList.remove(0);

        edt_namequestion.setText(currentQuestion.content);
        tv_currentquestion.setText("Câu " + (currentIndex + 1));
        tv_currentbonus.setText(String.format("%,d", tinhTien(currentIndex)));

        List<String> answers = new ArrayList<>();
        answers.add(currentQuestion.a);
        answers.add(currentQuestion.b);
        answers.add(currentQuestion.c);
        answers.add(currentQuestion.d);

        correctAnswer = "";
        String dapAnDungCode = (currentQuestion.correct != null) ? currentQuestion.correct.trim().toUpperCase() : "A";
        switch (dapAnDungCode) {
            case "A": correctAnswer = currentQuestion.a; break;
            case "B": correctAnswer = currentQuestion.b; break;
            case "C": correctAnswer = currentQuestion.c; break;
            case "D": correctAnswer = currentQuestion.d; break;
            default:  correctAnswer = currentQuestion.a; break;
        }
        Collections.shuffle(answers);

        tv_a.setText(answers.get(0)); tv_a.setTag(answers.get(0));
        tv_b.setText(answers.get(1)); tv_b.setTag(answers.get(1));
        tv_c.setText(answers.get(2)); tv_c.setTag(answers.get(2));
        tv_d.setText(answers.get(3)); tv_d.setTag(answers.get(3));

        ResetUI();
        BatSuKien();
    }

    private void ResetUI() {
        tv_a.setBackgroundResource(R.drawable.mini_button);
        tv_b.setBackgroundResource(R.drawable.mini_button);
        tv_c.setBackgroundResource(R.drawable.mini_button);
        tv_d.setBackgroundResource(R.drawable.mini_button);

        tv_a.setEnabled(true);
        tv_b.setEnabled(true);
        tv_c.setEnabled(true);
        tv_d.setEnabled(true);

        // --- [MỚI] Hiện lại tất cả đáp án (vì câu trước có thể đã dùng 50:50) ---
        tv_a.setVisibility(View.VISIBLE);
        tv_b.setVisibility(View.VISIBLE);
        tv_c.setVisibility(View.VISIBLE);
        tv_d.setVisibility(View.VISIBLE);

        // Đóng menu trợ giúp
        layout_lifelines.setVisibility(View.GONE);
    }

    private void BatSuKien() {
        // --- LOGIC CHỌN ĐÁP ÁN (GIỮ NGUYÊN) ---
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (v.getTag() == null) return;
                String chosenAnswer = (String) v.getTag();
                boolean isCorrect = chosenAnswer.equals(correctAnswer);

                tv_a.setEnabled(false); tv_b.setEnabled(false);
                tv_c.setEnabled(false); tv_d.setEnabled(false);

                if (isCorrect) {
                    v.setBackgroundResource(R.drawable.correct_bg);
                    money = tinhTien(currentIndex + 1);
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() { SangCauMoi(); }
                    }, 1000);
                } else {
                    v.setBackgroundResource(R.drawable.wrong_bg);
                    rungMay(400);
                    HienThiDapAnDung();
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() { KetThuc(); }
                    }, 2000);
                }
            }
        };

        tv_a.setOnClickListener(listener);
        tv_b.setOnClickListener(listener);
        tv_c.setOnClickListener(listener);
        tv_d.setOnClickListener(listener);

        // --- [MỚI] SỰ KIỆN NÚT SOS ---
        btn_help_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout_lifelines.getVisibility() == View.VISIBLE) {
                    layout_lifelines.setVisibility(View.GONE);
                } else {
                    layout_lifelines.setVisibility(View.VISIBLE);
                }
            }
        });

        // --- [MỚI] SỰ KIỆN 50:50 ---
        btn_5050.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count5050 > 0) {
                    dungQuyen5050();
                    count5050--;
                    updateHelpUI();
                    layout_lifelines.setVisibility(View.GONE);
                }
            }
        });

        // --- [MỚI] SỰ KIỆN KHÁN GIẢ ---
        btn_ratechoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countRateChoose > 0) {
                    dungQuyenKhaanGia();
                    countRateChoose--;
                    updateHelpUI();
                    layout_lifelines.setVisibility(View.GONE);
                }
            }
        });
    }

    // ==========================================
    // KHU VỰC CÁC HÀM XỬ LÝ QUYỀN TRỢ GIÚP
    // ==========================================

    // 1. Hàm 50:50
    private void dungQuyen5050() {
        // Tìm danh sách các đáp án SAI
        List<TextView> wrongAnswers = new ArrayList<>();
        if (!tv_a.getTag().equals(correctAnswer)) wrongAnswers.add(tv_a);
        if (!tv_b.getTag().equals(correctAnswer)) wrongAnswers.add(tv_b);
        if (!tv_c.getTag().equals(correctAnswer)) wrongAnswers.add(tv_c);
        if (!tv_d.getTag().equals(correctAnswer)) wrongAnswers.add(tv_d);

        // Trộn ngẫu nhiên danh sách sai
        Collections.shuffle(wrongAnswers);

        // Ẩn 2 cái đầu tiên
        wrongAnswers.get(0).setVisibility(View.INVISIBLE);
        wrongAnswers.get(1).setVisibility(View.INVISIBLE);

        // Đảm bảo không click được vào vùng đã ẩn
        wrongAnswers.get(0).setEnabled(false);
        wrongAnswers.get(1).setEnabled(false);
    }

    // 2. Hàm Ý kiến khán giả (Có lấy từ DB)
    private void dungQuyenKhaanGia() {
        StringBuilder result = new StringBuilder();

        // Lấy tỷ lệ từ Object Question (Đã update ở bước trước)
        int rA = currentQuestion.rate_a;
        int rB = currentQuestion.rate_b;
        int rC = currentQuestion.rate_c;
        int rD = currentQuestion.rate_d;

        // Nếu DB chưa có dữ liệu (toàn bộ = 0) -> Dùng Random
        if (rA == 0 && rB == 0 && rC == 0 && rD == 0) {
            fakeAudienceData(result);
        } else {
            // Logic hiển thị: Nếu đáp án đó đang hiện thì lấy số từ DB, nếu ẩn (do 50:50) thì hiện 0%
            result.append("A: ").append(tv_a.getVisibility() == View.VISIBLE ? rA : 0).append("%\n");
            result.append("B: ").append(tv_b.getVisibility() == View.VISIBLE ? rB : 0).append("%\n");
            result.append("C: ").append(tv_c.getVisibility() == View.VISIBLE ? rC : 0).append("%\n");
            result.append("D: ").append(tv_d.getVisibility() == View.VISIBLE ? rD : 0).append("%\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ý kiến trường quay");
        builder.setMessage(result.toString());
        builder.setPositiveButton("Cảm ơn", null);
        builder.show();
    }

    // 3. Hàm tạo dữ liệu giả (Fallback khi DB chưa có)
    private void fakeAudienceData(StringBuilder result) {
        int percentCorrect = 50 + (int)(Math.random() * 30); // 50-80%
        int percentRest = 100 - percentCorrect;

        int w1 = (int)(Math.random() * percentRest);
        int w2 = (int)(Math.random() * (percentRest - w1));
        int w3 = percentRest - w1 - w2;

        List<Integer> wrongPercents = new ArrayList<>();
        wrongPercents.add(w1); wrongPercents.add(w2); wrongPercents.add(w3);
        Collections.shuffle(wrongPercents);

        int wrongIndex = 0;
        TextView[] arrTV = {tv_a, tv_b, tv_c, tv_d};
        String[] arrName = {"A", "B", "C", "D"};

        for (int i = 0; i < 4; i++) {
            result.append(arrName[i]).append(": ");
            if (arrTV[i].getTag().equals(correctAnswer)) {
                result.append(percentCorrect);
            } else {
                if (arrTV[i].getVisibility() == View.INVISIBLE) {
                    result.append("0");
                } else {
                    result.append(wrongPercents.get(wrongIndex));
                    wrongIndex++;
                }
            }
            result.append("%\n");
        }
    }

    // ==========================================

    private void HienThiDapAnDung() {
        checkAndHighLight(tv_a);
        checkAndHighLight(tv_b);
        checkAndHighLight(tv_c);
        checkAndHighLight(tv_d);
    }

    private void checkAndHighLight(TextView tv) {
        if (tv.getTag() != null && tv.getTag().toString().equals(correctAnswer)) {
            tv.setBackgroundResource(R.drawable.correct_bg);
        }
    }

    private void SangCauMoi() {
        currentIndex++;
        if (currentIndex >= 15) {
            KetThuc();
            return;
        }
        if (currentIndex == 5 || currentIndex == 10) {
            hoiChacChan();
        } else {
            binhThuong();
        }
    }

    private void hoiChacChan() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PLayActivity.this);
        builder.setTitle("Chúc mừng!");
        builder.setMessage("Bạn đã vượt qua mốc quan trọng câu số " + currentIndex + ".\n" +
                "Bạn đang có " + String.format("%,d", money) + " VND.\n" +
                "Bạn có muốn chơi tiếp không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Đi tiếp", (dialog, which) -> binhThuong());
        builder.setNegativeButton("Dừng lại", (dialog, which) -> KetThuc());
        builder.show();
    }

    private void binhThuong() {
        loadQuestionForCurrentLevel();
        HienThi();
    }

    private void KetThuc() {
        Intent intent = new Intent(PLayActivity.this, AnnounceActivity.class);
        intent.putExtra("money", money);
        intent.putExtra("player_name", playerName);
        intent.putExtra("question_count", currentIndex);
        startActivity(intent);
        finish();
    }

    private void PauseGame() {
        btn_pause.setEnabled(false);
        Intent intent = new Intent(PLayActivity.this, PauseActivity.class);
        startActivity(intent);
    }

    private void rungMay(long milliseconds) {
        if (SettingsActivity.isVibrateEnabled) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (v != null && v.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(milliseconds);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        playerName = getIntent().getStringExtra("player_name");
        TimCT();
        KhoiTao();

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PauseGame();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_pause.setEnabled(true);
    }
}