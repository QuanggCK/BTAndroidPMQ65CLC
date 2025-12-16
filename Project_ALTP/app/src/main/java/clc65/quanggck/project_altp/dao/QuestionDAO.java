package clc65.quanggck.project_altp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import clc65.quanggck.project_altp.model.Question;
import clc65.quanggck.project_altp.database.DatabaseHelper;

public class QuestionDAO {

    private SQLiteDatabase db;

    public QuestionDAO(Context context) {
        db = new DatabaseHelper(context).getReadableDatabase();
    }

    public List<Question> getAll() {
        List<Question> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM Question", null);

        while (c.moveToNext()) {
            Question q = new Question();
            q.id = c.getInt(0);
            q.content = c.getString(1);
            q.a = c.getString(2);
            q.b = c.getString(3);
            q.c = c.getString(4);
            q.d = c.getString(5);
            q.correct = c.getString(6);
            q.difficultyId = c.getInt(7);
            list.add(q);
        }
        c.close();
        return list;
    }

    public List<Question> getQuestionsByDifficulty(int difficultyId) {

        List<Question> list = new ArrayList<>();

        Cursor c = db.rawQuery(
                "SELECT * FROM Question WHERE difficulty_id = ?",
                new String[]{String.valueOf(difficultyId)}
        );

        while (c.moveToNext()) {
            Question q = new Question();
            q.id = c.getInt(0);
            q.content = c.getString(1);
            q.a = c.getString(2);
            q.b = c.getString(3);
            q.c = c.getString(4);
            q.d = c.getString(5);
            q.correct = c.getString(6);
            q.difficultyId = c.getInt(7);

            list.add(q);
        }

        c.close();
        return list;
    }

}
