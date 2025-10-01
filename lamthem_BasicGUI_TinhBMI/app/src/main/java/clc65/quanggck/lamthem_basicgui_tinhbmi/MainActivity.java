package clc65.quanggck.lamthem_basicgui_tinhbmi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    EditText edt_height, edt_weight;
    CheckBox cbox_asian;
    Button btn_calculate;
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edt_height = findViewById(R.id.edt_height);
        edt_weight = findViewById(R.id.edt_weight);
        cbox_asian = findViewById(R.id.cbox_asian);
        tv_result = findViewById(R.id.tv_result);

        btn_calculate.setOnClickListener(v -> TinhBMI());
    }

    public void TinhBMI(){
        double height = Double.parseDouble(edt_height.getText().toString());
        double weight = Double.parseDouble(edt_weight.getText().toString());

        double bmi = weight / Math.pow(height,2);

        String category;
        if (cbox_asian.isChecked()){
            if (bmi <=18.5) category = "This Asian is so slim.";
            else if (bmi< 23) category = "This Asian is fit.";
            else if (bmi <27.5) category = "This Asian is fat.";
            else category = "This Asian is fat as fuck.";
        }else {
            if (bmi <=18.5) category = "This un_Asian is so slim.";
            else if (bmi< 25) category = "This un_Asian is fit.";
            else if (bmi <30) category = "This un_Asian is fat.";
            else category = "This un_Asian is fat as fuck.";
        }

        tv_result.setText("BMI = " + bmi +" -> " + category);
    }
}