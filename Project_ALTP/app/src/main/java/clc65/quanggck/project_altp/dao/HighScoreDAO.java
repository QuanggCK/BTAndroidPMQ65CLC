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

    // ===== 1. Hàm này dùng để LƯU KẾT QUẢ CUỐI CÙNG (Dùng trong AnnounceActivity) =====
    public void insertHighScore(String name, int money) {
        ContentValues cv = new ContentValues();
        cv.put("player_name", name);
        cv.put("money", money);

        // Nếu database có cột score thì thêm, ko thì để 0 hoặc bỏ dòng này
        cv.put("score", 0);

        // Nếu database có cột createdAt thì thêm dòng này
        cv.put("createdAt", System.currentTimeMillis());

        db.insert("HighScore", null, cv);
    }

    // ===== 2. Lấy TOP 5 theo tiền =====
    public List<HighScore> getTop15() {
        List<HighScore> list = new ArrayList<>();

        // Query sắp xếp giảm dần theo tiền (DESC)
        Cursor c = db.rawQuery(
                "SELECT * FROM HighScore ORDER BY money DESC LIMIT 15",
                null
        );

        if (c != null) {
            while (c.moveToNext()) {
                HighScore h = new HighScore();

                // Lấy dữ liệu
                h.id = c.getInt(c.getColumnIndexOrThrow("id"));
                h.playerName = c.getString(c.getColumnIndexOrThrow("player_name"));
                h.money = c.getInt(c.getColumnIndexOrThrow("money"));

                // Nếu model HighScore của bạn có biến score thì lấy, ko thì thôi
                try {
                    h.score = c.getInt(c.getColumnIndexOrThrow("score"));
                } catch (Exception e) {
                    h.score = 0;
                }

                list.add(h);
            }
            c.close();
        }
        return list;
    }
}