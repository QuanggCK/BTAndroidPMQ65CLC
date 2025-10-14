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
        final int[] choice1 = {-1}; /// -1 = chưa chọn, 0 = đá, 1 = giấy, 2 = kéo
        final int[] choice2 = {-1};

        /// Player1
        btn_rock1.setOnClickListener(v -> {
            choice1[0] = 0;
            CheckResult(choice1[0], choice2[0]);
        });
        btn_paper1.setOnClickListener(v -> {
            choice1[0] = 1;
            CheckResult(choice1[0], choice2[0]);
        });
        btn_scissor1.setOnClickListener(v -> {
            choice1[0] = 2;
            CheckResult(choice1[0], choice2[0]);
        });

        // Người 2 chọn
        btn_rock2.setOnClickListener(v -> {
            choice2[0] = 0;
            CheckResult(choice1[0], choice2[0]);
        });
        btn_paper2.setOnClickListener(v -> {
            choice2[0] = 1;
            CheckResult(choice1[0], choice2[0]);
        });
        btn_sicssor2.setOnClickListener(v -> {
            choice2[0] = 2;
            CheckResult(choice1[0], choice2[0]);
        });

        // Nút chơi lại
        btn_replay.setOnClickListener(v -> {
            choice1[0] = -1;
            choice2[0] = -1;
            tv_result.setText("Hãy chọn!");
        });
    }

    // --- Hàm kiểm tra kết quả ---
    private void CheckResult(int p1, int p2) {
        if (p1 == -1 || p2 == -1) {
            tv_result.setText("Chờ cả hai người chọn...");
            return;
        }

        if (p1 == p2) {
            tv_result.setText("Hòa!");
        } else if ((p1 == 0 && p2 == 2) || (p1 == 1 && p2 == 0) || (p1 == 2 && p2 == 1)) {
            tv_result.setText("Người chơi 1 thắng!");
        } else {
            tv_result.setText("Người chơi 2 thắng!");
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
