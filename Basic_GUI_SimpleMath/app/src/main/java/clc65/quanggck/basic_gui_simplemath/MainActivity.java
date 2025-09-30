package clc65.quanggck.basic_gui_simplemath;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    EditText edt_numbera, edt_numberb, edt_kqua;
    Button btn_plus, btn_minus, btn_multiply, btn_divide, btn_calculate;

    String phepToan = ""; ////Chuỗi để lưu loại phép toán

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        edt_numbera = findViewById(R.id.edt_numbera);
        edt_numberb = findViewById(R.id.edt_numberb);
        edt_kqua = findViewById(R.id.edt_kqua);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_calculate = findViewById(R.id.btn_calculate);


        ///  Gắn sự kiện chọn phép toán
        btn_plus.setOnClickListener(v -> phepToan = "+");
        btn_minus.setOnClickListener(v -> phepToan = "-");
        btn_multiply.setOnClickListener(v -> phepToan = "*");
        btn_divide.setOnClickListener(v -> phepToan = "/");


        /// Gắn sự kiện tính toán
        btn_calculate.setOnClickListener(v -> Tinh());
    }
        private void Tinh() {
            try {
                double a = Double.parseDouble(edt_numbera.getText().toString());
                double b = Double.parseDouble(edt_numberb.getText().toString());
                double kq = 0;

                switch (phepToan) {
                    case "+":
                        kq = a + b;
                        break;
                    case "-":
                        kq = a + b;
                        break;
                    case "*":
                        kq = a * b;
                        break;
                    case "/":
                        if (b != 0) kq = a / b;
                        else edt_kqua.setText("Cannot divide by 0");
                        return;
                    default:
                        edt_kqua.setText("Haven't chosen the operation");
                        return;
                }
                edt_kqua.setText(String.valueOf(kq));
            } catch (Exception e) {
                edt_kqua.setText("NaN");
            }
        }
    }