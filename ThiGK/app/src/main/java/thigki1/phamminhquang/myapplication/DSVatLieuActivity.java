package thigki1.phamminhquang.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DSVatLieuActivity extends AppCompatActivity {

    ListView lv_dsVatLieu;
    Button btnBack;
    ArrayList<String> dsVatLieu;
    ArrayAdapter<String> dsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsvat_lieu);

        // Ánh xạ view
        lv_dsVatLieu = findViewById(R.id.lv_dsVatLieu);
        btnBack = findViewById(R.id.btnBack);

        // Lấy dữ liệu
        dsVatLieu = getData();

        // Khởi tạo adapter
        dsAdapter = new ArrayAdapter<>(this, R.layout.item_vatlieu, dsVatLieu);
        lv_dsVatLieu.setAdapter(dsAdapter);

        // Sự kiện click trên list
        lv_dsVatLieu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String MonDuocChon = dsAdapter.getItem(position);
                Toast.makeText(DSVatLieuActivity.this, "Tên loại vật liệu xây dựng: " + MonDuocChon, Toast.LENGTH_LONG).show();
            }
        });

        // Sự kiện nút "Quay lại"
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Đóng Activity, quay về màn trước
            }
        });
    }

    // Hàm trả về danh sách các môn học
    ArrayList<String> getData() {
        ArrayList<String> dsCacVatLieu = new ArrayList<>();
        dsCacVatLieu.add("Xi măng");
        dsCacVatLieu.add("Gạch");
        dsCacVatLieu.add("Đá ốp lát");
        dsCacVatLieu.add("Ống nhựa");
        dsCacVatLieu.add("Sơn chống thấm");
        dsCacVatLieu.add("Cát xây");
        dsCacVatLieu.add("Cát tô");
        dsCacVatLieu.add("Thép cây");
        dsCacVatLieu.add("Sắt hộp");
        dsCacVatLieu.add("Ngói");
        dsCacVatLieu.add("Tôn lạnh");
        dsCacVatLieu.add("Gỗ công nghiệp");
        dsCacVatLieu.add("Gỗ tự nhiên");
        dsCacVatLieu.add("Kính cường lực");
        dsCacVatLieu.add("Vữa xây");
        dsCacVatLieu.add("Keo dán gạch");
        dsCacVatLieu.add("Chất chống thấm");
        dsCacVatLieu.add("Ống đồng");
        dsCacVatLieu.add("Dây điện");
        dsCacVatLieu.add("Ống nước PVC");
        return dsCacVatLieu;
    }
}
