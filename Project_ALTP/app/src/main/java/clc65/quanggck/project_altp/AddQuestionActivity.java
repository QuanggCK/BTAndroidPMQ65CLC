package clc65.quanggck.project_altp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddQuestionActivity extends AppCompatActivity {

    Button btn_return;
    // Hàm tìm các controller
    public void TimCT(){
        btn_return = findViewById(R.id.btn_setting);
    }

    // Hàm quay về
    public void Return(View v){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_question);
        TimCT();

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Return(v);
            }
        });
    }
}
