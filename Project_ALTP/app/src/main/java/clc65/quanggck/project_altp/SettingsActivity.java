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

    // Biến lưu trạng thái rung (Mặc định là true - đang bật)
    // Tốt nhất bạn nên lưu biến này vào SharedPreferences hoặc một class Manager giống MusicManager để dùng toàn app
    public static boolean isVibrateEnabled = true;

    // ===== Ánh xạ View =====
    private void TimCT() {
        btn_return = findViewById(R.id.btn_return);
        btn_mute   = findViewById(R.id.btn_mute);
        btn_guide  = findViewById(R.id.btn_guide);
        btn_vibrate = findViewById(R.id.btn_vibrate);
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

    // ===== Mở / tắt chế độ rung =====
    private void Vibrate() {
        // Đảo ngược trạng thái (Đang bật -> Tắt, Đang tắt -> Bật)
        isVibrateEnabled = !isVibrateEnabled;

        if (isVibrateEnabled) {
            Toast.makeText(this, "Đã BẬT rung", Toast.LENGTH_SHORT).show();
            // Rung nhẹ 1 cái để báo hiệu
            rungMay(300);
        } else {
            Toast.makeText(this, "Đã TẮT rung", Toast.LENGTH_SHORT).show();
        }
    }

    // Hàm hỗ trợ rung máy (Dùng chung cho cả app sau này)
    private void rungMay(long milliseconds) {
        if (isVibrateEnabled) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (v != null && v.hasVibrator()) {
                // Kiểm tra version Android để dùng hàm phù hợp
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // Cho Android 8.0 trở lên
                    v.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    // Cho Android cũ hơn
                    v.vibrate(milliseconds);
                }
            }
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
}