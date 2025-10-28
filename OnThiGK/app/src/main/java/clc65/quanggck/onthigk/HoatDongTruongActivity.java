package clc65.quanggck.onthigk;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HoatDongTruongActivity extends AppCompatActivity {
    HoatDongAdapter hoatdongAdapter;
    ArrayList<HoatDongModel> dsHoatDong;

    Button btnBack;
    RecyclerView RecyclerViewHoatDong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoat_dong_truong);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
        dsHoatDong = getData();
        RecyclerViewHoatDong = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this);
        RecyclerViewHoatDong.setLayoutManager(layoutLinear);
        hoatdongAdapter = new HoatDongAdapter(this, dsHoatDong);
        RecyclerViewHoatDong.setAdapter(hoatdongAdapter);
    }

    ArrayList<HoatDongModel> getData(){
        ArrayList<HoatDongModel> dsDuLieu = new ArrayList<>();
        HoatDongModel hd1 = new HoatDongModel("This is kinda something", "logo", "Testing for exam");
        HoatDongModel hd2 = new HoatDongModel("Duel", "logo", "I summon Enlilgirsu, The Mekk-Knight Orcust");
        HoatDongModel hd3 = new HoatDongModel("Hoạt động 3", "logo", "Intents - Fragments - RecylerView-Viewpager-Tablayout");
        dsDuLieu.add(hd1);
        dsDuLieu.add(hd2);
        dsDuLieu.add(hd3);
        return dsDuLieu;
    }
}