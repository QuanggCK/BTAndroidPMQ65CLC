package clc65.quanggck.project_altp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button  btn_exit, btn_infogame,
            btn_settings, btn_infoPlayer, btn_newgame, btn_highscore;

    // ===== Tìm controller =====
    private void TimCT() {
        btn_exit = findViewById(R.id.btn_exit);
        btn_infogame = findViewById(R.id.btn_infogame);
        btn_settings = findViewById(R.id.btn_settings);
        btn_infoPlayer = findViewById(R.id.btn_infoPlayer);
        btn_newgame = findViewById(R.id.btn_newgame);
        btn_highscore = findViewById(R.id.btn_highscore);
    }



    // ===== Info game =====
    private void InfoGame() {
        btn_infogame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(MainActivity.this, InfoGameActivity.class);
                startActivity(intent);
            }
        });
    }

    // ===== Settings =====
    private void Settings() {
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    // ===== Info Player =====
    private void InfoPlayer() {
        btn_infoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(MainActivity.this, InfoPlayerActivity.class);
                startActivity(intent);
            }
        });
    }

    // ===== Thoát game =====
    private void ExitGame() {
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Thoát game?")
                        .setMessage("Bạn có chắc muốn thoát không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("Không", null)
                        .show();
            }
        });
    }

    // ===== Dialog nhập tên + chơi =====
    private void PlayWithName() {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Nhập tên người chơi");

        final EditText edtName = new EditText(this);
        edtName.setHint("Tên của bạn");
        builder.setView(edtName);

        builder.setPositiveButton("Chơi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String playerName =
                        edtName.getText().toString().trim();

                if (playerName.isEmpty()) {
                    edtName.setError("Vui lòng nhập tên");
                    return;
                }

                Intent intent =
                        new Intent(MainActivity.this, PLayActivity.class);
                intent.putExtra("player_name", playerName);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    // ===== High Score =====
    private void HighScore() {
        btn_highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HighScoreFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        MusicManager.play(this);

        TimCT();
        InfoGame();
        Settings();
        InfoPlayer();
        ExitGame();
        HighScore();

        // Nút chơi game
        btn_newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayWithName();
            }
        });
    }
}
