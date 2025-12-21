package clc65.quanggck.project_altp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnnounceActivity extends AppCompatActivity {

    // ===== Controller =====
    TextView tv_name, tv_money, tv_achievment;
    Button btn_playAgain, btn_home;

    // ===== Tìm controller =====
    private void TimCT() {
        tv_name = findViewById(R.id.tv_name);
        tv_money = findViewById(R.id.tv_money);
        tv_achievment = findViewById(R.id.tv_achievment);

        btn_playAgain = findViewById(R.id.btn_pause_continue);
        btn_home = findViewById(R.id.btn_homepage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_announce);
        TimCT();

        // ===== Nhận dữ liệu =====
        String name = getIntent().getStringExtra("player_name");
        int money = getIntent().getIntExtra("money", 0);

        tv_name.setText("Tên: " + name);
        tv_money.setText("Tiền: " + money + " đ");
        tv_achievment.setText("Kết quả:");

        // ===== Chơi lại =====
        btn_playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnnounceActivity.this, PLayActivity.class);
                startActivity(i);
                finish();
            }
        });

        // ===== Về trang chủ =====
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnnounceActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
    }
}
