package clc65.quanggck.project_altp;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class InfoGameActivity extends AppCompatActivity {

    Button btn_return;
    TextView tv_support;

    // Hàm tìm các controller
    public void TimCT(){
        btn_return = findViewById(R.id.btn_setting);
        tv_support = findViewById(R.id.tv_support);

    }

    // Hàm quay về
    public void Return(View v){
        finish();
    }


    // Hàm đổi màu chữ
    private void setGradientText(TextView tv) {
        // Tạo shader gradient 3 màu
        Shader shader = new LinearGradient(
                0, 0,
                0, tv.getHeight(),
                new int[]{
                        0xFF009ACC,
                        0xFF043D50,
                        0xFF020202
                },
                null,
                Shader.TileMode.CLAMP
        );

        tv.getPaint().setShader(shader);
        tv.invalidate();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_game);
        TimCT();

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Return(v);
            }
        });

        tv_support.post(() -> setGradientText(tv_support));

        tv_support.setOnClickListener(v -> {
            String url = "https://www.youtube.com/@quanggck7908";
            Intent intent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(url));
            startActivity(intent);
        });
    }
}