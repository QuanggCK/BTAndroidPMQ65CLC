package clc65.quanggck.project_altp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_addquestion, btn_exit, btn_infogame, btn_settings, btn_infoPlayer, btn_newgame;


    // Hàm tìm các controller
    private void TimCT() {
        btn_addquestion = findViewById(R.id.btn_addquestion);
        btn_exit = findViewById(R.id.btn_exit);
        btn_infogame = findViewById(R.id.btn_infogame);
        btn_settings = findViewById(R.id.btn_settings);
        btn_infoPlayer = findViewById(R.id.btn_infoPlayer);
        btn_newgame = findViewById(R.id.btn_newgame);

    }

    // Intent thêm câu hỏi
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

    // Intent info game
    private void InfoGame() {
        btn_infogame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoGameIntent = new Intent(MainActivity.this, InfoGameActivity.class);
                startActivity(infoGameIntent);
            }
        });
    }

    // Intent settings
    private void Settings() {
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });
    }

    // Intent info player
    private void InfoPlayer() {
        btn_infoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MainActivity.this, InfoPlayerActivity.class);
                startActivity(settingsIntent);
            }
        });
    }

    // Intent chơi
    public void Play(View v) {
        Intent playIntent = new Intent(MainActivity.this, PLayActivity.class);
        startActivity(playIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        MusicManager.play(this);
        TimCT();
        ThemCauHoi();
        InfoGame();
        Settings();
        InfoPlayer();
        btn_newgame.setOnClickListener(v -> Play(v));

        btn_exit.setOnClickListener(v -> Exit(v));
    }
}
