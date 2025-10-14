package clc65.quanggck.lamthem_baobuakeo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tv_result;
    Button btn_rock1, btn_paper1, btn_scissor1, btn_rock2, btn_paper2, btn_sicssor2, btn_replay;

    /// Hàm kiếm Controller
    public void FindCT() {
        tv_result = findViewById(R.id.tv_result);
        btn_rock1 = findViewById(R.id.btn_rock1);
        btn_paper1 = findViewById(R.id.btn_paper1);
        btn_scissor1 = findViewById(R.id.btn_scissor1);
        btn_rock2 = findViewById(R.id.btn_rock2);
        btn_paper2 = findViewById(R.id.btn_paper2);
        btn_sicssor2 = findViewById(R.id.btn_sicssor2);
        btn_replay = findViewById(R.id.btn_replay);
    }

    /// Hàm chơi bao búa kéo
    public void Play() {
        final int[] choicePlayer = {-1}; // -1: Chưa chọn, 0: Búa, 1: Bao, 2: Kéo
        final int[] choiceBot = {-1};    // Máy random

        // Hàm random của máy
        java.util.Random random = new java.util.Random();

        // Khi người chơi chọn
        btn_rock2.setOnClickListener(v -> {
            choicePlayer[0] = 0; // Búa
            choiceBot[0] = random.nextInt(3);
            CheckResult(choiceBot[0], choicePlayer[0]);
        });

        btn_paper2.setOnClickListener(v -> {
            choicePlayer[0] = 1; // Bao
            choiceBot[0] = random.nextInt(3);
            CheckResult(choiceBot[0], choicePlayer[0]);
        });

        btn_sicssor2.setOnClickListener(v -> {
            choicePlayer[0] = 2; // Kéo
            choiceBot[0] = random.nextInt(3);
            CheckResult(choiceBot[0], choicePlayer[0]);
        });

        // Nút chơi lại
        btn_replay.setOnClickListener(v -> {
            choicePlayer[0] = -1;
            choiceBot[0] = -1;
            tv_result.setText("Chơi lại nào!");
        });
    }


    // --- Hàm kiểm tra kết quả ---
    private void CheckResult(int bot, int player) {
        if (bot == -1 || player == -1) {
            tv_result.setText("Chờ chọn...");
            return;
        }

        if (bot == player) {
            tv_result.setText("Hòa!");
        } else if ((player == 0 && bot == 2) || (player == 1 && bot == 0) || (player == 2 && bot == 1)) {
            tv_result.setText("Bạn thắng!");
        } else {
            tv_result.setText("Máy thắng!");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        FindCT();
        Play();
    }
}
