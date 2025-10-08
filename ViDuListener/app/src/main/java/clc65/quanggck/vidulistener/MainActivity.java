package clc65.quanggck.vidulistener;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    /// Kha báo các biến tướng ứng với các điều kiện trên xml layout cần tuongq
    Button btn_sayHi;
    Button btn_sayHello;

    ///  Để clean code, ta tạo 1 hàm Tìm all đk ở đầu và gọi trong onCreate
    void TimDieuKhien() {
        btn_sayHi = findViewById(R.id.btn_sayHi);
        btn_sayHello = findViewById(R.id.btn_sayHello);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
        btn_sayHi.setOnClickListener(ngheSayHi);
        //Vô danh
        btn_sayHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Hữu danh
    View.OnClickListener ngheSayHi = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "Hi", Toast.LENGTH_SHORT).show();
        }
    };
}
