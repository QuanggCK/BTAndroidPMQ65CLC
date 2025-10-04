package clc65.quanggck.th_basicgui_calculator;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tv_result, tv_name, tv_title;
    EditText edt_numberA, edt_numberB;
    Button btn_plus, btn_minus, btn_multiply, btn_divide, btn_calculate;
    String phepToan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv_result = findViewById(R.id.tv_result);
        edt_numberA = findViewById(R.id.edt_numberA);
        edt_numberB = findViewById(R.id.edt_numberB);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_calculate = findViewById(R.id.btn_calculate);

        btn_plus.setOnClickListener(v -> phepToan = "+");
        btn_minus.setOnClickListener(v -> phepToan = "-");
        btn_multiply.setOnClickListener(v -> phepToan = "*");
        btn_divide.setOnClickListener(v -> phepToan = "/");

        btn_calculate.setOnClickListener(v -> TinhToan());



    }

    private void TinhToan() {
        String strA = edt_numberA.getText().toString();
        String strB = edt_numberB.getText().toString();

        Double a = Double.parseDouble(strA);
        Double b = Double.parseDouble(strB);
        Double result = 0.0;
        switch (phepToan) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    tv_result.setText("Cannot divide to 0 !!!");
                    return;
                }
                    result = a / b;
                break;
        }
        if (result == result.intValue()) {
            tv_result.setText(String.valueOf(result.intValue()));
        } else {
            tv_result.setText(String.valueOf(result));
        }



    }
}