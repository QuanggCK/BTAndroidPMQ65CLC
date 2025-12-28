package clc65.quanggck.project_altp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import clc65.quanggck.project_altp.dao.HighScoreDAO;

public class AnnounceActivity extends AppCompatActivity {

    TextView tv_name, tv_money, tv_achievment;
    Button btn_playAgain, btn_home;

    HighScoreDAO highScoreDAO;

    // Hàm tìm Controller
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
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_announce);


        TimCT();

        // Nhận dữ liệu từ màn hình chơi
        Intent intent = getIntent();
        String name = intent.getStringExtra("player_name");
        int money = intent.getIntExtra("money", 0);
        int questionCount = intent.getIntExtra("question_count", 0);

        // Hiển thị dữ liệu lên màn hình
        if (tv_name != null) tv_name.setText("Tên: " + (name != null ? name : "Unknown"));
        if (tv_money != null) tv_money.setText("Tiền: " + String.format("%,d", money) + " đ");
        if (tv_achievment != null) tv_achievment.setText("Kết quả: " + questionCount + "/15");

        // 3. Lưu thông tin vào HighScoreDAO
        try {
            highScoreDAO = new HighScoreDAO(this);
            if (money > 0) {
                highScoreDAO.insertHighScore(name, money);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Xử lý nút chơi lại
        if (btn_playAgain != null) {
            btn_playAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MusicManager.stop(); // Tắt nhạc
                    Intent i = new Intent(AnnounceActivity.this, PLayActivity.class);
                    i.putExtra("player_name", name);
                    startActivity(i);
                    finish();
                }
            });
        }

        // Xử lý nút Về trang chủ
        if (btn_home != null) {
            btn_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MusicManager.stop(); // Tắt nhạc
                    Intent i = new Intent(AnnounceActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicManager.pause();
    }
}