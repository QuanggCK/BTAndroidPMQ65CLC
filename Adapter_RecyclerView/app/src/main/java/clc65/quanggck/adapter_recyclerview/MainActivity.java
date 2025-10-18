package clc65.quanggck.adapter_recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LandScapeAdapter landScapeAdapter;
    ArrayList<LandScape> recyclerViewDatas;
    RecyclerView recyclerViewLandScape;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //(3): Chuẩn bị dữ liệu cho ArrayList
        recyclerViewDatas = getDataForRecyclerView();
        //(4): Khởi tạo adapter
        landScapeAdapter = new LandScapeAdapter(this, recyclerViewDatas);
        //(5): Khởi tạo recyclerView
        recyclerViewLandScape = findViewById(R.id.recyclerLand);
        //(6): Tạo adapter gắn với nguồn dữ liệu
        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this);
        recyclerViewLandScape.setLayoutManager(layoutLinear);
        //(7): Gắn adapter với Recycler
        recyclerViewLandScape.setAdapter(landScapeAdapter);

    }
    ArrayList<LandScape> getDataForRecyclerView(){
        ArrayList<LandScape> dsDuLieu = new ArrayList<>();
        LandScape LandScape1 = new LandScape("Buckingham Palace", "buckingham.jpg");
        LandScape LandScape2 = new LandScape("Eiffel Tower", "eiffel.jpg");
        LandScape LandScape3 = new LandScape("Thap HaNoi","flag_tower_of_hanoi.jpg");
        LandScape LandScape4 = new LandScape("Statue of Liberty","statue_of_liberty");
        dsDuLieu.add(LandScape1);
        dsDuLieu.add(LandScape2);
        dsDuLieu.add(LandScape3);
        dsDuLieu.add(LandScape4);
        return dsDuLieu;


    }
}