package clc65.quanggck.project_altp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class PauseActivity extends AppCompatActivity {

    TextView tv_return;
    AppCompatButton btn_pause_continue, btn_homepage;

    // Hàm tìm Controller
    private void TimCT() {
        tv_return = findViewById(R.id.tv_return);
        btn_pause_continue = findViewById(R.id.btn_pause_continue);
        btn_homepage = findViewById(R.id.btn_homepage);
    }

    // Hàm bắt sự kiện
    private void BatSuKien() {
        // Xử lý nút quay lại
        tv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // quay lại PlayActivity
            }
        });

        // Xử lý nút tiếp tục
        btn_pause_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // tiếp tục game
            }
        });

        // Xử lý nút về trang chủ
        btn_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PauseActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pause);

        TimCT();
        BatSuKien();
    }


}
