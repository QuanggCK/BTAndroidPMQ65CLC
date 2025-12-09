package clc65.quanggck.project_altp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends AppCompatActivity {

    Button btn_return, btn_mute;

    // Hàm tìm các controller
    public void TimCT(){
        btn_return = findViewById(R.id.btn_return);
        btn_mute = findViewById(R.id.btn_mute);

    }
    // Hàm quay về
    public void Return(View v){
        finish();
    }

    // Hàm tắt âm thanh
    public void Mute(View v) {
        if (MusicManager.isMuted()) {
            MusicManager.unmute();
            Toast.makeText(this, "Đã mở âm thanh", Toast.LENGTH_SHORT).show();
        }else {
            MusicManager.mute();
            Toast.makeText(this, "Đã tắt âm thanh", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        TimCT();
        btn_mute.setOnClickListener(v -> Mute(v));

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Return(v);
            }
        });
    }
}