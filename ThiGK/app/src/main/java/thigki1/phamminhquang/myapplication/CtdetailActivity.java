package thigki1.phamminhquang.myapplication;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        // 🔽 Thêm sự kiện click item cho RecyclerView
        RecyclerViewHoatDong.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(CtdetailActivity.this,
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }
                    });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    CongTrinhModel item = dsHoatDong.get(position);
                    Toast.makeText(CtdetailActivity.this,
                            "Bạn đang chọn: " + item.getMainContent(),
                            Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) { }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }
        });
    }

    ArrayList<CongTrinhModel> getData(){
        ArrayList<CongTrinhModel> dsDuLieu = new ArrayList<>();
        CongTrinhModel hd1 = new CongTrinhModel("Công trình 1", "img_1", "16/10/2005");
        CongTrinhModel hd2 = new CongTrinhModel("Công trình 2", "img_2", "16/10/2005");
        CongTrinhModel hd3 = new CongTrinhModel("Công trình 3", "img_3", "16/10/2005");
        dsDuLieu.add(hd1);
        dsDuLieu.add(hd2);
        dsDuLieu.add(hd3);
        return dsDuLieu;
    }
}
