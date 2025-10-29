package thigki1.phamminhquang.myapplication;

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

public class DienTichSanActivity extends AppCompatActivity {
    Button btnTinhTB, btnBack;
    TextInputLayout output_dtbmon, input_dai, input_rong;
    void TimDK() {
        btnTinhTB = findViewById(R.id.bt_tinh);
        input_dai = findViewById(R.id.input_dai);
        input_rong = findViewById(R.id.input_rong);
        output_dtbmon = findViewById(R.id.output_kq);
        btnBack = findViewById(R.id.btnBack);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_tich_san);
        TimDK();
        btnTinhTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strDai = input_dai.getEditText().getText().toString();
                String strRong = input_rong.getEditText().getText().toString();

                Double Dai = Double.parseDouble(strDai);
                Double Rong = Double.parseDouble(strRong);

                Double DienTich = Dai * Rong;


                String diemTBM = String.valueOf(DienTich);

                output_dtbmon.getEditText().setText(diemTBM);
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