package clc65.quanggck.project_altp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    Button btn_return, btn_mute, btn_guide, btn_vibrate;
    public static boolean isVibrateEnabled = true;

    // Hàm tìm Controller
    private void TimCT() {
        btn_return = findViewById(R.id.btn_return);
        btn_mute   = findViewById(R.id.btn_mute);
        btn_guide  = findViewById(R.id.btn_guide);
        btn_vibrate = findViewById(R.id.btn_vibrate);
    }

    // Hàm quay về
    private void Return() {
        finish();
    }

    // Hàm mở / tắt âm thanh
    private void Mute() {
        if (MusicManager.isMuted()) {
            MusicManager.unmute();
            updateMuteButtonUI(); // Cập nhật giao diện nút
            Toast.makeText(this, "Đã mở âm thanh", Toast.LENGTH_SHORT).show();
        } else {
            MusicManager.mute();
            updateMuteButtonUI(); // Cập nhật giao diện nút
            Toast.makeText(this, "Đã tắt âm thanh", Toast.LENGTH_SHORT).show();
        }
    }

    // Hiển thị trạng thái nút
    private void updateMuteButtonUI() {
        if (MusicManager.isMuted()) {
            btn_mute.setText("Bật âm thanh");
        } else {
            btn_mute.setText("Tắt âm thanh");
        }
    }

    // Hiển thị trạng thái rung
    private void Vibrate() {
        isVibrateEnabled = !isVibrateEnabled;

        if (isVibrateEnabled) {
            Toast.makeText(this, "Đã BẬT rung", Toast.LENGTH_SHORT).show();
            rungMay(300);
            btn_vibrate.setText("Tắt rung");
        } else {
            Toast.makeText(this, "Đã TẮT rung", Toast.LENGTH_SHORT).show();
            btn_vibrate.setText("Bật rung");
        }
    }

    // Hàm rung máy
    private void rungMay(long milliseconds) {
        if (isVibrateEnabled) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (v != null && v.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(milliseconds);
                }
            }
        }
    }

    // Hàm hướng dẫn
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

        updateMuteButtonUI();
        btn_vibrate.setText(isVibrateEnabled ? "Tắt rung" : "Bật rung");

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

        btn_vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrate();
            }
        });

        btn_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guide();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        MusicManager.play(this, R.raw.intro, false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicManager.pause();
    }
}