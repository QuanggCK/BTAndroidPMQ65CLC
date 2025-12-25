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
        // Mở kết nối Database
        db = new DatabaseHelper(context).getReadableDatabase();
    }

    // Lấy câu hỏi theo độ khó (Level 1 -> 15)
    public List<Question> getQuestionsByDifficulty(Difficulty difficulty) {
        List<Question> list = new ArrayList<>();

        // Difficulty Enum có value là 1,2,3... khớp với difficulty_id trong DB
        int levelId = difficulty.value;

        // Query tìm câu hỏi có difficulty_id tương ứng
        Cursor c = db.rawQuery(
                "SELECT * FROM Question WHERE difficulty_id = ?",
                new String[]{ String.valueOf(levelId) }
        );

        // Duyệt qua từng dòng kết quả
        while (c.moveToNext()) {
            list.add(cursorToQuestion(c));
        }
        c.close();
        return list;
    }

    // Hàm chuyển đổi từ con trỏ Database (Cursor) sang Object (Question)
    // Dùng getColumnIndex để TỰ ĐỘNG tìm vị trí cột, không lo bị sai thứ tự
    // ... (các phần trên giữ nguyên)

    private Question cursorToQuestion(Cursor c) {
        Question q = new Question();

        try {
            q.id = c.getInt(c.getColumnIndexOrThrow("id"));
            q.content = c.getString(c.getColumnIndexOrThrow("content"));
            q.a = c.getString(c.getColumnIndexOrThrow("a"));
            q.b = c.getString(c.getColumnIndexOrThrow("b"));
            q.c = c.getString(c.getColumnIndexOrThrow("c"));
            q.d = c.getString(c.getColumnIndexOrThrow("d"));
            q.correct = c.getString(c.getColumnIndexOrThrow("correct"));

            int diffId = c.getInt(c.getColumnIndexOrThrow("difficulty_id"));
            q.difficulty = Difficulty.fromLevel(diffId);


            int indexA = c.getColumnIndex("rate_a");
            if (indexA != -1) {
                // "rate_a" trong ngoặc kép là tên cột trong DB
                // q.rate_a là tên biến trong class Question
                q.rate_a = c.getInt(indexA);
                q.rate_b = c.getInt(c.getColumnIndex("rate_b"));
                q.rate_c = c.getInt(c.getColumnIndex("rate_c"));
                q.rate_d = c.getInt(c.getColumnIndex("rate_d"));
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return q;
    }
}