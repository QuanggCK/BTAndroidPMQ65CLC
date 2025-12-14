package clc65.quanggck.project_altp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PLayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play);

    }
    private DoKho layDoKhoTheoSoCau(int soCau) {
        if (soCau >= 1 && soCau <= 5) {
            return DoKho.EZ;
        } else if (soCau >= 6 && soCau <= 10) {
            return DoKho.MEDIUM;
        } else {
            return DoKho.HARD;
        }
    }

}