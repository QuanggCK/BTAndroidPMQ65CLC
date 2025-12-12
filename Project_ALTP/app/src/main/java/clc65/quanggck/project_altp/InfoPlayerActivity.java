package clc65.quanggck.project_altp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InfoPlayerActivity extends AppCompatActivity {

    Button btn_return;

    // Hàm tìm các controller
    public void TimCT(){
        btn_return = findViewById(R.id.btn_return);

    }

    // Hàm quay về
    public void Return(View v){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_player);
        TimCT();
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Return(v);
            }
        });
    }
}