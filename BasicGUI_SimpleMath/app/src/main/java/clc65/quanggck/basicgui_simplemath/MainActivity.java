package clc65.quanggck.basicgui_simplemath;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText edt_numbera, edt_numberb;
    TextView tv_result;
    Button btn_plus, btn_minus, btn_multiply, btn_divide, btn_calculate;

    String phepToan = ""; ////Chuỗi để lưu loại phép toán

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        edt_numbera = findViewById(R.id.edt_numbera);
        edt_numberb = findViewById(R.id.edt_numberb);
        tv_result = findViewById(R.id.tv_result);
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
                    kq = a - b;
                    break;
                case "*":
                    kq = a * b;
                    break;
                case "/":
                    if (b != 0) kq = a / b;
                    else tv_result.setText("Cannot divide by 0");
                    return;
                default:
                    tv_result.setText("Haven't chosen the operation");
                    return;
            }
            tv_result.setText(String.valueOf(kq));
        } catch (Exception e) {
            tv_result.setText("NaN");
        }
    }
}