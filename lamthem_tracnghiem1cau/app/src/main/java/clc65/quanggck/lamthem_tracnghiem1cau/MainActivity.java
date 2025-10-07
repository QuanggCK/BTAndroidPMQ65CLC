package clc65.quanggck.lamthem_tracnghiem1cau;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView questionText, resultText;
    Button btn1, btn2, btn3, btn4, btnNext;

    int correctAnswer;
    boolean answered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gán id
        questionText = findViewById(R.id.textView2);
        resultText = findViewById(R.id.tv_result);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btnNext = findViewById(R.id.btn_nextquestion);


        generateQuestion();


        View.OnClickListener answerClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answered) return; // Ngăn chọn lại
                answered = true;

                Button clicked = (Button) v;
                int selected = Integer.parseInt(clicked.getText().toString());

                if (selected == correctAnswer) {
                    resultText.setText("Correct");
                } else {
                    resultText.setText("Wrong");
                }
            }
        };

        btn1.setOnClickListener(answerClick);
        btn2.setOnClickListener(answerClick);
        btn3.setOnClickListener(answerClick);
        btn4.setOnClickListener(answerClick);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQuestion();
            }
        });
    }

    void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(50) + 1;
        int b = rand.nextInt(50) + 1;
        correctAnswer = a + b;
        answered = false;

        questionText.setText(a + " + " + b + " = ?");
        resultText.setText("");


        int[] answers = new int[4];
        int correctIndex = rand.nextInt(4);

        for (int i = 0; i < 4; i++) {
            if (i == correctIndex) {
                answers[i] = correctAnswer;
            } else {
                int wrong;
                do {
                    wrong = correctAnswer + rand.nextInt(11) - 5; // lệch -5 đến +5
                } while (wrong == correctAnswer || wrong < 0);
                answers[i] = wrong;
            }
        }

        btn1.setText(String.valueOf(answers[0]));
        btn2.setText(String.valueOf(answers[1]));
        btn3.setText(String.valueOf(answers[2]));
        btn4.setText(String.valueOf(answers[3]));
    }
}
