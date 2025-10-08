package clc65.quanggck.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText edt_usd;
    TextView tv_convert;
    Button btn_convert;

    
    double currencyU_V = 25000.0;

    void FindCT() {
        edt_usd = findViewById(R.id.edt_usd);
        tv_convert = findViewById(R.id.tv_convert);
        btn_convert = findViewById(R.id.btn_convert);
    }


    void Convert() {
        String usdText = edt_usd.getText().toString().trim();

        if (usdText.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số USD", Toast.LENGTH_SHORT).show();
            return;
        }


        usdText = usdText.replace(",", "").replace(" ", "");

        try {
            double usd = Double.parseDouble(usdText);
            double vnd = usd * currencyU_V;

            DecimalFormat df = new DecimalFormat("#,###");
            String formatted = df.format(Math.round(vnd)) + " VND";
            tv_convert.setText(formatted);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Giá trị nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        FindCT();

        // OnClickListener hữu danh
        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Convert();
            }
        });
    }
}
