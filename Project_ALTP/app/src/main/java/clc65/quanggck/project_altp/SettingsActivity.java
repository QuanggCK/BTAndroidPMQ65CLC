package clc65.quanggck.project_altp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    Button btn_return, btn_mute, btn_guide;

    // ===== Ánh xạ View =====
    private void TimCT() {
        btn_return = findViewById(R.id.btn_return);
        btn_mute   = findViewById(R.id.btn_mute);
        btn_guide  = findViewById(R.id.btn_guide);
    }

    // ===== Quay lại Activity trước =====
    private void Return() {
        finish();
    }

    // ===== Bật / tắt âm thanh =====
    private void Mute() {
        if (MusicManager.isMuted()) {
            MusicManager.unmute();
            Toast.makeText(this, "Đã mở âm thanh", Toast.LENGTH_SHORT).show();
        } else {
            MusicManager.mute();
            Toast.makeText(this, "Đã tắt âm thanh", Toast.LENGTH_SHORT).show();
        }
    }


    private void Guide() {
        Intent intent = new Intent(SettingsActivity.this, InstructionActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        TimCT();

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Return();
            }
        });

        btn_mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mute();
            }
        });

        btn_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guide();
            }
        });
    }
}
