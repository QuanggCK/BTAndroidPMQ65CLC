package clc65.quanggck.vidutestbutton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView tv_hello;
        Button btn_press;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv_hello = findViewById(R.id.tv_hello);
        btn_press = findViewById(R.id.btn_press);
        btn_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_hello.setText("QuanggCK");
            }
        });

    }
}