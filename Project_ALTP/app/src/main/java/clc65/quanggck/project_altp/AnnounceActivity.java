package clc65.quanggck.project_altp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import clc65.quanggck.project_altp.dao.HighScoreDAO;
import clc65.quanggck.project_altp.model.HighScore;

public class AnnounceActivity extends AppCompatActivity {

    TextView tv_name, tv_money, tv_achievment;
    Button btn_playAgain, btn_home;

    // Khai báo DAO
    HighScoreDAO highScoreDAO;

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

        // 1. Nhận dữ liệu
        Intent intent = getIntent();
        String name = intent.getStringExtra("player_name");
        int money = intent.getIntExtra("money", 0);
        int questionCount = intent.getIntExtra("question_count", 0);

        // 2. Hiển thị UI
        tv_name.setText("Người chơi: " + name);
        tv_money.setText("Tiền thưởng: " + String.format("%,d", money) + " đ");
        tv_achievment.setText("Kết quả:" + questionCount + "/15");

        // 3. --- QUAN TRỌNG: LƯU VÀO DATABASE ---
        highScoreDAO = new HighScoreDAO(this);

        // Gọi hàm insert
        highScoreDAO.insertHighScore(name, money);

        // 1. Nút Chơi lại
        btn_playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnnounceActivity.this, PLayActivity.class);
                i.putExtra("player_name", name);
                startActivity(i);
                finish();
            }
        });

// 2. Nút Về trang chủ
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnnounceActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
    }
}