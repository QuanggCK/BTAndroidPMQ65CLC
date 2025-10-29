package thigki1.phamminhquang.myapplication;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CtdetailActivity extends AppCompatActivity {
    CongTrinhAdapter hoatdongAdapter;
    ArrayList<CongTrinhModel> dsHoatDong;

    Button btnBack;
    RecyclerView RecyclerViewHoatDong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctdetail);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
        dsHoatDong = getData();
        RecyclerViewHoatDong = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this);
        RecyclerViewHoatDong.setLayoutManager(layoutLinear);
        hoatdongAdapter = new CongTrinhAdapter(this, dsHoatDong);
        RecyclerViewHoatDong.setAdapter(hoatdongAdapter);
    }

    ArrayList<CongTrinhModel> getData(){
        ArrayList<CongTrinhModel> dsDuLieu = new ArrayList<>();
        CongTrinhModel hd1 = new CongTrinhModel("Công trình 1", "img_1", "16/10/2005");
        CongTrinhModel hd2 = new CongTrinhModel("Công trình 2", "img_2", "16/10/2005");
        CongTrinhModel hd3 = new CongTrinhModel("Hoạt động 3", "logo", "Intents - Fragments - RecylerView-Viewpager-Tablayout");
        dsDuLieu.add(hd1);
        dsDuLieu.add(hd2);
        dsDuLieu.add(hd3);
        return dsDuLieu;
    }
}