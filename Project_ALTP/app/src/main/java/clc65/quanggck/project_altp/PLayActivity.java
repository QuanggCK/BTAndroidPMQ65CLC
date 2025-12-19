package clc65.quanggck.project_altp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import clc65.quanggck.project_altp.dao.QuestionDAO;
import clc65.quanggck.project_altp.model.Answer;
import clc65.quanggck.project_altp.model.Difficulty;
import clc65.quanggck.project_altp.model.Question;

public class PLayActivity extends AppCompatActivity {

    // ===== Controller =====
    TextView edt_namequestion;
    TextView tv_a, tv_b, tv_c, tv_d;
    TextView tv_currentquestion, tv_currentbonus;

    // ===== Data =====
    QuestionDAO dao;
    List<Question> questionList;
    int currentIndex = 0;

    // ===== B1: Tìm controller =====
    private void TimCT() {
        edt_namequestion = findViewById(R.id.edt_namequestion);

        tv_a = findViewById(R.id.tv_a);
        tv_b = findViewById(R.id.tv_b);
        tv_c = findViewById(R.id.tv_c);
        tv_d = findViewById(R.id.tv_d);

        tv_currentquestion = findViewById(R.id.tv_currentquestion);
        tv_currentbonus = findViewById(R.id.tv_currentbonus);
    }

    // ===== B2: Khởi tạo =====
    private void KhoiTao() {
        dao = new QuestionDAO(this);
        currentIndex = 0;
        Difficulty difficulty = Difficulty.LEVEL_1;
        questionList = dao.getQuestionsByDifficulty(difficulty);

    }



    private void HienThi() {

        if (questionList == null || questionList.isEmpty()) return;

        Question q = questionList.get(0);

        edt_namequestion.setText(q.content);
        tv_currentquestion.setText("Câu " + (currentIndex + 1));
        tv_currentbonus.setText("+" + (currentIndex + 1) * 1000);

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(q.a, q.correct.equals("A")));
        answers.add(new Answer(q.b, q.correct.equals("B")));
        answers.add(new Answer(q.c, q.correct.equals("C")));
        answers.add(new Answer(q.d, q.correct.equals("D")));

        Collections.shuffle(answers);

        tv_a.setText("A: " + answers.get(0).text);
        tv_b.setText("B: " + answers.get(1).text);
        tv_c.setText("C: " + answers.get(2).text);
        tv_d.setText("D: " + answers.get(3).text);

        tv_a.setTag(answers.get(0).isCorrect);
        tv_b.setTag(answers.get(1).isCorrect);
        tv_c.setTag(answers.get(2).isCorrect);
        tv_d.setTag(answers.get(3).isCorrect);

        ResetUI();
    }


    // ===== B4: Bắt sự kiện =====
    private void BatSuKien() {

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getTag() == null) return;

                boolean isCorrect = (boolean) v.getTag();

                if (isCorrect) {
                    v.setBackgroundResource(R.drawable.correct_bg);
                    SangCauMoi();
                } else {
                    v.setBackgroundResource(R.drawable.wrong_bg);
                }
            }
        };


        tv_a.setOnClickListener(listener);
        tv_b.setOnClickListener(listener);
        tv_c.setOnClickListener(listener);
        tv_d.setOnClickListener(listener);
    }

    // ===== Reset UI =====
    private void ResetUI() {
        tv_a.setBackgroundResource(R.drawable.mini_button);
        tv_b.setBackgroundResource(R.drawable.mini_button);
        tv_c.setBackgroundResource(R.drawable.mini_button);
        tv_d.setBackgroundResource(R.drawable.mini_button);
    }

    // ===== Sang câu mới =====
    private void SangCauMoi() {
        currentIndex++;

        if (currentIndex < 15) {
            Difficulty difficulty = Difficulty.fromLevel(currentIndex + 1);
            questionList = dao.getQuestionsByDifficulty(difficulty);

            if (questionList == null || questionList.isEmpty()) {
                KetThuc();
                return;
            }

            tv_a.postDelayed(this::HienThi, 800);
        } else {
            KetThuc();
        }

    }



    // ===== Kết thúc =====
    private void KetThuc() {
        edt_namequestion.setText("Hoàn thành!");
        tv_currentquestion.setText("Xong");
        tv_currentbonus.setText("🎉");
    }

    // ===== onCreate =====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play);

        TimCT();
        KhoiTao();
        HienThi();
        BatSuKien();
    }
}
