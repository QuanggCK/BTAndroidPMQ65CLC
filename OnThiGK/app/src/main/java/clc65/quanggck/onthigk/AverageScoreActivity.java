package clc65.quanggck.onthigk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class AverageScoreActivity extends AppCompatActivity {
    Button btnTinhTB, btnBack;
    TextInputLayout input_dgk, input_dck, output_dtbmon;
    void TimDK() {
        btnTinhTB = findViewById(R.id.bt_tinh);
        input_dgk = findViewById(R.id.input_gk);
        input_dck = findViewById(R.id.input_ck);
        output_dtbmon = findViewById(R.id.output_kq);
        btnBack = findViewById(R.id.btnBack);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average_score);
        TimDK();

        btnTinhTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strGiuaKy = input_dgk.getEditText().getText().toString();
                String strCuoiKy = input_dck.getEditText().getText().toString();

                Double DiemGiuaKy = Double.parseDouble(strGiuaKy);
                Double DiemCuoiKy = Double.parseDouble(strCuoiKy);

                Double DiemTBM = DiemGiuaKy * 0.5 + DiemCuoiKy * 0.5;

                String diemTBM = String.valueOf(DiemTBM);

                output_dtbmon.getEditText().setText(diemTBM);

                if (DiemTBM >= 5) {
                    Toast.makeText(v.getContext(), "Bạn đã qua môn 🎉", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(v.getContext(), "Bạn sẽ học lại :v", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}