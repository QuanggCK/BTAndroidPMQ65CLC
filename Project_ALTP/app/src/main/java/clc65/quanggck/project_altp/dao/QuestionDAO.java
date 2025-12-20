package clc65.quanggck.project_altp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import clc65.quanggck.project_altp.database.DatabaseHelper;
import clc65.quanggck.project_altp.model.Difficulty;
import clc65.quanggck.project_altp.model.Question;

public class QuestionDAO {

    private SQLiteDatabase db;

    public QuestionDAO(Context context) {
        db = new DatabaseHelper(context).getReadableDatabase();
    }

    public List<Question> getAll() {
        List<Question> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM Question", null);

        while (c.moveToNext()) {
            list.add(cursorToQuestion(c));
        }

        c.close();
        return list;
    }

    public List<Question> getQuestionsByDifficulty(Difficulty difficulty) {

        List<Question> list = new ArrayList<>();

        // Enum LEVEL_1 → LEVEL_15
        // ordinal(): 0 → 14
        int difficultyId = difficulty.ordinal() + 1;

        Cursor c = db.rawQuery(
                "SELECT * FROM Question WHERE difficulty_id = ?",
                new String[]{ String.valueOf(difficultyId) }
        );

        while (c.moveToNext()) {
            Question q = new Question();

            q.id = c.getInt(0);
            q.content = c.getString(1);
            q.a = c.getString(2);
            q.b = c.getString(3);
            q.c = c.getString(4);
            q.d = c.getString(5);
            q.rate_a = c.getInt(6);
            q.rate_b = c.getInt(7);
            q.rate_c = c.getInt(8);
            q.rate_d = c.getInt(9);
            q.correct = c.getString(10);
            int diffId = c.getInt(11);
            q.difficulty = Difficulty.fromLevel(diffId);


            list.add(q);
        }

        c.close();
        return list;
    }



    // ======================
    // Convert Cursor → Question
    // ======================
    private Question cursorToQuestion(Cursor c) {

        Question q = new Question();

        q.id = c.getInt(0);
        q.content = c.getString(1);
        q.a = c.getString(2);
        q.b = c.getString(3);
        q.c = c.getString(4);
        q.d = c.getString(5);
        q.correct = c.getString(6);

        int difficultyId = c.getInt(7);
        q.difficulty = Difficulty.values()[difficultyId - 1];

        return q;
    }
}
