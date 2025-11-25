package clc65.quanggck.superannoyingdob;

import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    NumberPicker npDay;
    TextView tvYear;
    SeekBar sbYear;
    Button button;

    public void TimCT() {
        npDay = findViewById(R.id.npDay);
        tvYear = findViewById(R.id.tvYear);
        sbYear = findViewById(R.id.sbYear);
        button = findViewById(R.id.button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TimCT();
        npDay.setMinValue(1);
        npDay.setMaxValue(31);
        sbYear.setMax(3000);
        sbYear.setProgress(0); 
        // Đồng bộ TextView với SeekBar
        sbYear.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvYear.setText(String.valueOf(progress));
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // Khi nhấn button -> hiện Toast
        button.setOnClickListener(v -> {
            int day = npDay.getValue();
            int year = sbYear.getProgress();

            Toast.makeText(MainActivity.this,
                    "Bạn sinh ngày " + day + " năm " + year,
                    Toast.LENGTH_SHORT).show();
        });
    }
}
