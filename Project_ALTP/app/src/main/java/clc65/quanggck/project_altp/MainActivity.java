package clc65.quanggck.project_altp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_addquestion, btn_exit;
    // Hàm tìm các controller
    private void TimCT() {
        btn_addquestion = findViewById(R.id.btn_addquestion);
        btn_exit = findViewById(R.id.btn_exit);
    }

    // Hàm thêm câu hỏi
    private void ThemCauHoi() {
        btn_addquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addQuestionIntent = new Intent(MainActivity.this, AddQuestionActivity.class);
                startActivity(addQuestionIntent);
            }
        });
    }

    // Hàm thoát game
    public void Exit(View v) {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Thoát game?")
                .setMessage("Bạn có chắc muốn thoát không?")
                .setPositiveButton("Có", (dialog, which) -> {
                    finishAffinity();
                    System.exit(0);
                })
                .setNegativeButton("Không", null)
                .show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TimCT();
        ThemCauHoi();
    }
}
