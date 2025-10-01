package clc65.quanggck.tong_2_so;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ///(1) Khai báo các biến đối tượng nhằm liên kết đến các xml views(id)
    EditText edtnumbera, edtnumberb;
    TextView result;
    Button btn_calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /// (2) Link biến Java với id tương ứng
        edtnumbera= findViewById(R.id.edt_numa);
        edtnumberb = findViewById(R.id.edt_numb);
        btn_calculate = findViewById(R.id.btn_calculate);
        result = findViewById(R.id.tv_result);
        /// Trong VD này chưa dùng tới nút tính toan, vi đã dùng OnClick


    }
    public void HamTinhTong(View v){
        /// Code tinh tong
        /// Form: Lấy dữ liệu --> Xử lý --> Xuất kq
        String str_so1 = edtnumbera.getText().toString();
        String str_so2 = edtnumberb.getText().toString();
        int soA= Integer.parseInt(str_so1);
        int soB= Integer.parseInt(str_so2);

        int Tong = soA + soB;
        String strResult = String.valueOf(Tong);

        /// Hiển thị kết quả trên TextView
        result.setText(strResult);


    }
}