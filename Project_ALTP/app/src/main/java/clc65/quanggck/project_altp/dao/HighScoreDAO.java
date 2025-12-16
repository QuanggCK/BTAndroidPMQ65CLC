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

    public void insert(String name, int score, int money) {
        ContentValues cv = new ContentValues();
        cv.put("player_name", name);
        cv.put("score", score);
        cv.put("money", money);
        db.insert("HighScore", null, cv);
    }

    public List<HighScore> getTop5() {
        List<HighScore> list = new ArrayList<>();
        Cursor c = db.rawQuery(
                "SELECT * FROM HighScore ORDER BY score DESC LIMIT 5",
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
