package clc65.quanggck.project_altp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import clc65.quanggck.project_altp.dao.QuestionDAO;
import clc65.quanggck.project_altp.model.Answer;
import clc65.quanggck.project_altp.model.Difficulty;
import clc65.quanggck.project_altp.model.Question;

public class PLayActivity extends AppCompatActivity {

    TextView edt_namequestion, tv_a, tv_b, tv_c, tv_d;
    TextView tv_currentquestion, tv_currentbonus;
    Button btn_pause;

    String playerName;

    QuestionDAO dao;
    List<Question> questionList;
    int currentIndex = 0;
    private String correctAnswer;

    int money = 0;

    private void TimCT() {
        edt_namequestion = findViewById(R.id.edt_namequestion);
        tv_a = findViewById(R.id.tv_a);
        tv_b = findViewById(R.id.tv_b);
        tv_c = findViewById(R.id.tv_c);
        tv_d = findViewById(R.id.tv_d);
        tv_currentquestion = findViewById(R.id.tv_currentquestion);
        tv_currentbonus = findViewById(R.id.tv_currentbonus);
        btn_pause = findViewById(R.id.btn_pause);
    }

    private void KhoiTao() {
        dao = new QuestionDAO(this);
        currentIndex = 0;
        questionList = dao.getQuestionsByDifficulty(Difficulty.LEVEL_1);
    }

    private int tinhTien(int cauSo) {
        int[] moneyTable = {
                0, 500, 1000, 2000, 3000, 5000, 7500,
                10000, 12500, 15000, 25000,
                50000, 100000, 250000, 500000, 1000000
        };
        return moneyTable[cauSo];
    }

    private Question currentQuestion;

    private void HienThi() {
        if (questionList == null || questionList.isEmpty()) {
            KetThuc();
            return;
        }

        // LẤY CÂU HỎI
        currentQuestion = questionList.remove(0);

        edt_namequestion.setText(currentQuestion.content);
        tv_currentquestion.setText("Câu " + (currentIndex + 1));
        tv_currentbonus.setText("+" + tinhTien(currentIndex + 1));

        List<String> answers = new ArrayList<>();
        answers.add(currentQuestion.a);
        answers.add(currentQuestion.b);
        answers.add(currentQuestion.c);
        answers.add(currentQuestion.d);

        // Reset correctAnswer để tránh giữ giá trị của câu cũ
        correctAnswer = "";

        // XÁC ĐỊNH ĐÁP ÁN ĐÚNG (TEXT)
        // Dùng trim() và toUpperCase() ngay từ đầu để tránh lỗi do data bẩn
        String dapAnDung = (currentQuestion.correct != null) ? currentQuestion.correct.trim().toUpperCase() : "";

        switch (dapAnDung) {
            case "A":
                correctAnswer = currentQuestion.a;
                break;
            case "B":
                correctAnswer = currentQuestion.b;
                break;
            case "C":
                correctAnswer = currentQuestion.c;
                break;
            case "D":
                correctAnswer = currentQuestion.d;
                break;
            default:
                // Nếu data bị lỗi không phải A,B,C,D, gán tạm vào A để không crash
                correctAnswer = currentQuestion.a;
                break;
        }

        Collections.shuffle(answers);

        // HIỂN THỊ + GÁN TAG
        tv_a.setText(answers.get(0));
        tv_b.setText(answers.get(1));
        tv_c.setText(answers.get(2));
        tv_d.setText(answers.get(3));

        tv_a.setTag(answers.get(0));
        tv_b.setTag(answers.get(1));
        tv_c.setTag(answers.get(2));
        tv_d.setTag(answers.get(3));

        ResetUI();
        BatSuKien();
    }

    private void HienThiDapAnDung() {
        // Kiểm tra an toàn trước khi xử lý
        if (correctAnswer == null) return;

        String correct = correctAnswer.trim();

        // Kiểm tra từng ô, nếu trùng nội dung với đáp án đúng thì hiện màu xanh
        checkAndHighLight(tv_a, correct);
        checkAndHighLight(tv_b, correct);
        checkAndHighLight(tv_c, correct);
        checkAndHighLight(tv_d, correct);
    }

    // Hàm phụ để code gọn hơn
    private void checkAndHighLight(TextView tv, String correctText) {
        if (tv.getTag() != null && tv.getTag().toString().trim().equals(correctText)) {
            tv.setBackgroundResource(R.drawable.correct_bg);
        }
    }


    private void BatSuKien() {

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if (v.getTag() == null) return;

                String chosenAnswer = (String) v.getTag();
                boolean isCorrect = chosenAnswer.equals(correctAnswer);

                // khóa click
                tv_a.setOnClickListener(null);
                tv_b.setOnClickListener(null);
                tv_c.setOnClickListener(null);
                tv_d.setOnClickListener(null);

                if (isCorrect) {
                    v.setBackgroundResource(R.drawable.correct_bg);
                    money = tinhTien(currentIndex + 1);

                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SangCauMoi();
                        }
                    }, 800);

                } else {
                    v.setBackgroundResource(R.drawable.wrong_bg);
                    HienThiDapAnDung();
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            KetThuc();
                        }
                    }, 800);
                }
            }
        };

        tv_a.setOnClickListener(listener);
        tv_b.setOnClickListener(listener);
        tv_c.setOnClickListener(listener);
        tv_d.setOnClickListener(listener);
    }



    private void ResetUI() {
        tv_a.setBackgroundResource(R.drawable.mini_button);
        tv_b.setBackgroundResource(R.drawable.mini_button);
        tv_c.setBackgroundResource(R.drawable.mini_button);
        tv_d.setBackgroundResource(R.drawable.mini_button);
    }

    private void SangCauMoi() {
        currentIndex++;

        if (currentIndex < 15) {
            questionList = dao.getQuestionsByDifficulty(Difficulty.fromLevel(currentIndex + 1));
            HienThi();
        } else {
            KetThuc();
        }
    }

    private void KetThuc() {
        Intent intent = new Intent(PLayActivity.this, AnnounceActivity.class);
        intent.putExtra("money", money);
        intent.putExtra("player_name", playerName);
        startActivity(intent);
    }

    private void PauseGame() {
        btn_pause.setEnabled(false);
        Intent intent = new Intent(PLayActivity.this, PauseActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        playerName = getIntent().getStringExtra("player_name");

        TimCT();
        KhoiTao();
        HienThi();

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
