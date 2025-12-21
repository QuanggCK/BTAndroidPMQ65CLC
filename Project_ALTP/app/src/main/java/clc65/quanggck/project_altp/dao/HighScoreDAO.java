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
    // score = số câu đúng
    // money = tiền hiện tại (chưa thắng)
    public long insertPlayer(String playerName) {
        ContentValues cv = new ContentValues();
        cv.put("player_name", playerName);
        cv.put("score", 0);
        cv.put("money", 0);

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

        Cursor c = db.rawQuery(
                "SELECT id, player_name, score, money " +
                        "FROM HighScore " +
                        "ORDER BY money DESC " +
                        "LIMIT 5",
                null
        );

        while (c.moveToNext()) {
            HighScore h = new HighScore();
            h.id = c.getInt(0);
            h.playerName = c.getString(1);
            h.score = c.getInt(2);
            h.money = c.getInt(3);
            list.add(h);
        }

        c.close();
        return list;
    }
}