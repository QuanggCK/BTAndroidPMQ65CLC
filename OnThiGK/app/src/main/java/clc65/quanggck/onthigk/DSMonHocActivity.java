package clc65.quanggck.onthigk;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DSMonHocActivity extends AppCompatActivity {

    ListView lv_dsMonHoc;
    Button btnBack;
    ArrayList<String> dsMonHoc;
    ArrayAdapter<String> dsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsmon_hoc);

        // Ánh xạ view
        lv_dsMonHoc = findViewById(R.id.lv_dsMonHoc);
        btnBack = findViewById(R.id.btnBack);

        // Lấy dữ liệu
        dsMonHoc = getData();

        // Khởi tạo adapter
        dsAdapter = new ArrayAdapter<>(this, R.layout.item_monhoc, dsMonHoc);
        lv_dsMonHoc.setAdapter(dsAdapter);

        // Sự kiện click trên list
        lv_dsMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String MonDuocChon = dsAdapter.getItem(position);
                Toast.makeText(DSMonHocActivity.this, "Môn được chọn: " + MonDuocChon, Toast.LENGTH_LONG).show();
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
        ArrayList<String> dsCacMonHoc = new ArrayList<>();
        dsCacMonHoc.add("Tin học đại cương");
        dsCacMonHoc.add("Lập trình Java");
        dsCacMonHoc.add("Phát triển Ứng dụng Web 1");
        dsCacMonHoc.add("Kinh tế chính trị");
        dsCacMonHoc.add("Lập trình thiết bị di động");
        dsCacMonHoc.add("Quản lý dự án phần mềm");
        dsCacMonHoc.add("Lập trình Python");
        dsCacMonHoc.add("Cấu trúc dữ liệu và giải thuật");
        dsCacMonHoc.add("Công nghệ phần mềm");
        dsCacMonHoc.add("Mạng máy tính");
        dsCacMonHoc.add("Kiến trúc máy tính và hệ điều hành");
        return dsCacMonHoc;
    }
}
