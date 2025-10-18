package clc65.quanggck.vdlistviewadapter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv_deck;
    public void TimCT(){
        lv_deck = (ListView) findViewById(R.id.lv_deck);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TimCT();

        ///(1) Chuẩn bị nguồn dữ liệu hiển thị

        //--Khai bao nguồn dữ liệu--//
        ArrayList<String> ls_deck;
        ls_deck = new ArrayList<String>();

        //--Lay du lieu dua vao ls_deck--// -> Lấy từ File, Database, Internet,...
        ls_deck = getData();
        ///(2)--Khai bao adapter--//
        ArrayAdapter<String> deckAdapter;
        deckAdapter = new ArrayAdapter<String>(this,
                                                android.R.layout.simple_list_item_1,
                                                ls_deck);
        lv_deck.setAdapter(deckAdapter);
        /// (4) --Xử lý sự kiện--//
        lv_deck.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Xử lý việc chọn Item ở đây
                //Biến position chứa vị trí cảu Item được chọn
                //(4.1) Lấy giá trị phần tử
                //Cách 1: Lấy gián tiếp qua Adapter
                    String giatriChon = deckAdapter.getItem(position).toString();
                ///Cách 2: Lấy trực tiếp từ biến chứa danh sách
                //String giatriChon = ls_deck.get(position);
                //


                //(4.2) Lam gi voi no thif tuy bai toan
                String thongBao = "U chosed : " + giatriChon;
                Toast.makeText(MainActivity.this, thongBao, Toast.LENGTH_LONG).show();
            }
        });
    }
    ArrayList<String> getData(){
        /// Code đọc dữ liệu và cất vào biến tạm, trước khi return cho
        ArrayList<String> dsTam = new ArrayList<String>();
        //Code ở đây
        //Bài này, ta hard-code, ta fake dữ liệu tại đây
        dsTam.add("Orcust Fiendsmith");
        dsTam.add("Blue-Eyes Primite");
        dsTam.add("K9 Orcust");
        dsTam.add("Dragonmaid Dracotail");
        return dsTam;
    }
}