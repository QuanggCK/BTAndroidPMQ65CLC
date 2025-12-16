package clc65.quanggck.project_altp;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class QuestionDAO {

    DatabaseHelper dbHelper;

    public QuestionDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public List<Question> getQuestionsByDifficulty(int difficultyId) {
        List<Question> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM Question WHERE difficulty_id = ? ORDER BY RANDOM()",
                new String[]{String.valueOf(difficultyId)}
        );

        while (cursor.moveToNext()) {
            Question q = new Question();
            q.id = cursor.getInt(0);
            q.content = cursor.getString(1);
            q.a = cursor.getString(2);
            q.b = cursor.getString(3);
            q.c = cursor.getString(4);
            q.d = cursor.getString(5);
            q.correct = cursor.getString(6);
            q.difficultyId = cursor.getInt(7);

            list.add(q);
        }
        cursor.close();
        return list;
    }
}
