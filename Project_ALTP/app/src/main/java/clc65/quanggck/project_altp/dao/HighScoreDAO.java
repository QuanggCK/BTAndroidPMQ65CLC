package clc65.quanggck.project_altp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import clc65.quanggck.project_altp.database.DatabaseHelper;
import clc65.quanggck.project_altp.model.HighScore;

public class HighScoreDAO {

    private SQLiteDatabase db;

    public HighScoreDAO(Context context) {
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    // ===== Thêm người chơi khi bắt đầu game =====
    public long insertPlayer(String playerName) {
        ContentValues cv = new ContentValues();
        cv.put("player_name", playerName);
        cv.put("score", 0);
        cv.put("money", 0);
        // QUAN TRỌNG: Phải thêm thời gian tạo, nếu không sẽ lỗi NOT NULL
        cv.put("createdAt", System.currentTimeMillis());

        return db.insert("HighScore", null, cv);
    }

    // ===== Cập nhật khi kết thúc game =====
    public void updateResult(long id, int score, int money) {
        ContentValues cv = new ContentValues();
        cv.put("score", score);
        cv.put("money", money);

        db.update(
                "HighScore",
                cv,
                "id = ?",
                new String[]{String.valueOf(id)}
        );
    }

    // ===== Lấy TOP 5 theo tiền =====
    public List<HighScore> getTop5() {
        List<HighScore> list = new ArrayList<>();

        // Query sắp xếp giảm dần theo tiền
        Cursor c = db.rawQuery(
                "SELECT id, player_name, score, money FROM HighScore ORDER BY money DESC LIMIT 5",
                null
        );

        while (c.moveToNext()) {
            HighScore h = new HighScore();
            // Dùng getColumnIndexOrThrow cho an toàn
            h.id = c.getInt(c.getColumnIndexOrThrow("id"));
            h.playerName = c.getString(c.getColumnIndexOrThrow("player_name"));
            h.score = c.getInt(c.getColumnIndexOrThrow("score"));
            h.money = c.getInt(c.getColumnIndexOrThrow("money"));

            list.add(h);
        }

        c.close();
        return list;
    }
}