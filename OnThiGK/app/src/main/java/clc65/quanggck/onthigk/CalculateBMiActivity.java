package clc65.quanggck.onthigk;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class CalculateBMiActivity extends AppCompatActivity {

    TextInputLayout input_weight, input_height, output_bmi;
    Button btnCalculate, btnBack;

    public void TimCT() {
        input_weight = findViewById(R.id.input_weight);
        input_height = findViewById(R.id.input_height);
        output_bmi = findViewById(R.id.output_bmi);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnBack = findViewById(R.id.btnBack);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);
        TimCT();

        btnCalculate.setOnClickListener(v -> TinhBMI());
        btnBack.setOnClickListener(v -> finish());
    }

    public void TinhBMI() {
        String strWeight = input_weight.getEditText().getText().toString().trim();
        String strHeight = input_height.getEditText().getText().toString().trim();

        double weight = Double.parseDouble(strWeight);
        double height = Double.parseDouble(strHeight);

        if (weight <= 0 || height <= 0) {
            Toast.makeText(this, "Giá trị nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }

        double BMI = weight / (height * height);
        String strBMI = String.format("%.2f", BMI);

        output_bmi.getEditText().setText(strBMI);


        if (BMI < 18.5) {
            Toast.makeText(this, "Bạn bị gầy", Toast.LENGTH_LONG).show();
        } else if (BMI <= 24.9) {
            Toast.makeText(this, "Bạn bình thường", Toast.LENGTH_LONG).show();
        } else if (BMI <= 29.9) {
            Toast.makeText(this, "Bạn bị thừa cân", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Bạn bị béo phì", Toast.LENGTH_LONG).show();
        }
    }
}
