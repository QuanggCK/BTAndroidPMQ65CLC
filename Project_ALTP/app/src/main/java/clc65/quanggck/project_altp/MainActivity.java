package clc65.quanggck.project_altp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

// import clc65.quanggck.project_altp.admin.AddQuestionActivity;

public class MainActivity extends AppCompatActivity {

    Button  btn_exit, btn_infogame,
            btn_settings, btn_newgame, btn_highscore;
    TextView tv_name;

    private int tapCount = 0;
    private long lastClickTime = 0;

    // Hàm tìm Controller
    private void TimCT() {
        btn_exit = findViewById(R.id.btn_exit);
        btn_infogame = findViewById(R.id.btn_infogame);
        btn_settings = findViewById(R.id.btn_settings);
        tv_name = findViewById(R.id.tv_name);
        btn_newgame = findViewById(R.id.btn_newgame);
        btn_highscore = findViewById(R.id.btn_highscore);
    }

    // Intent Info game
    private void InfoGame() {
        btn_infogame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfoGameActivity.class);
                startActivity(intent);
            }
        });
    }

    // Intent Settings
    private void Settings() {
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    // Secret Admin Mode (Tự thêm câu hỏi)
    private void SecretAdminMode() {
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastClickTime > 2000) {
                    tapCount = 1;
                } else {
                    tapCount++;
                }
                lastClickTime = currentTime;
                if (tapCount >= 4 && tapCount < 7) {
                    int conLai = 7 - tapCount;
                    Toast.makeText(MainActivity.this, "Còn " + conLai + " lượt nhấn nữa", Toast.LENGTH_SHORT).show();
                }
                if (tapCount == 7) {
                    tapCount = 0;
                    Toast.makeText(MainActivity.this, "Đã mở chế độ Admin!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, AddQuestionActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    // Hàm thoát game + Alert Dialogue
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

    // Hàm bắt đầu chơi
    private void PlayWithName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Nhập tên người chơi");

        final EditText edtName = new EditText(this);
        edtName.setHint("Tên của bạn");
        builder.setView(edtName);

        builder.setPositiveButton("Chơi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String playerName = edtName.getText().toString().trim();
                if (playerName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, PLayActivity.class);
                intent.putExtra("player_name", playerName);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    // Hàm hiển thị HighScore
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

        TimCT();
        InfoGame();
        Settings();
        ExitGame();
        HighScore();
        SecretAdminMode();

        btn_newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayWithName();
            }
        });
    }

    // Hàm phát nhạc
    @Override
    protected void onResume() {
        super.onResume();
        MusicManager.play(this, R.raw.intro, false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicManager.pause();
    }
}