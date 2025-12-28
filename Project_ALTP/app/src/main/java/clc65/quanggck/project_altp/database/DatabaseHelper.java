package clc65.quanggck.project_altp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "altp.db";
    private static final int DB_VERSION = 3;
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Tạo bảng Difficulty
        db.execSQL(
                "CREATE TABLE Difficulty (" +
                        "id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL)"
        );

        // Tạo bảng Question
        db.execSQL(
                "CREATE TABLE Question (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "content TEXT NOT NULL," +
                        "a TEXT NOT NULL," +
                        "b TEXT NOT NULL," +
                        "c TEXT NOT NULL," +
                        "d TEXT NOT NULL," +
                        "correct TEXT NOT NULL," +
                        "difficulty_id INTEGER NOT NULL," +
                        "rate_a INTEGER DEFAULT 0," +
                        "rate_b INTEGER DEFAULT 0," +
                        "rate_c INTEGER DEFAULT 0," +
                        "rate_d INTEGER DEFAULT 0)"
        );

        // Tạo bảng HighScore
        db.execSQL(
                "CREATE TABLE HighScore (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "player_name TEXT NOT NULL," +
                        "score INTEGER NOT NULL," +
                        "money INTEGER NOT NULL," +
                        "createdAt INTEGER NOT NULL)"
        );

        // Thêm dữ liệu vào bảng Difficulty
        for (int i = 1; i <= 15; i++) {
            db.execSQL("INSERT INTO Difficulty(id, name) VALUES (" + i + ", 'LEVEL_" + i + "')");
        }


        String sql = "INSERT INTO Question(content, a, b, c, d, correct, difficulty_id, rate_a, rate_b, rate_c, rate_d) VALUES ";

        // 5 câu dễ
        // Câu 1: Đáp án A
        sql += "('Đâu là thủ đô của Việt Nam?', 'Hà Nội', 'TP. Hồ Chí Minh', 'Đà Nẵng', 'Cần Thơ', 'A', 1, 90, 5, 3, 2),";
        // Câu 2: Đáp án C
        sql += "('Một năm có bao nhiêu tháng?', '10', '11', '12', '13', 'C', 2, 2, 3, 92, 3),";
        // Câu 3: Đáp án B
        sql += "('Con vật nào sau đây sống dưới nước?', 'Con mèo', 'Con cá', 'Con gà', 'Con chim', 'B', 3, 5, 85, 5, 5),";
        // Câu 4: Đáp án B
        sql += "('Vịnh Hạ Long thuộc tỉnh nào?', 'Quảng Bình', 'Quảng Ninh', 'Quảng Trị', 'Quảng Ngãi', 'B', 4, 10, 70, 10, 10),";
        // Câu 5: Đáp án C
        sql += "('Trong truyện cổ tích, ai là người nhặt được quả thị?', 'Tấm', 'Cám', 'Bà lão', 'Ông Bụt', 'C', 5, 15, 10, 65, 10),";

        // 5 câu trung bình
        // Câu 6: Đáp án A
        sql += "('Đỉnh núi nào cao nhất Việt Nam?', 'Fansipan', 'Pu Si Lung', 'Bạch Mộc Lương Tử', 'Pu Ta Leng', 'A', 6, 60, 15, 15, 10),";
        // Câu 7: Đáp án C
        sql += "('Giải Nobel không có lĩnh vực nào sau đây?', 'Vật lý', 'Hóa học', 'Toán học', 'Y học', 'C', 7, 10, 10, 55, 25),";
        // Câu 8: Đáp án B
        sql += "('Hành tinh nào gần Mặt Trời nhất?', 'Sao Kim', 'Sao Thủy', 'Sao Hỏa', 'Trái Đất', 'B', 8, 25, 50, 15, 10),";
        // Câu 9: Đáp án C
        sql += "('Quốc gia nào có diện tích lớn nhất thế giới?', 'Mỹ', 'Trung Quốc', 'Nga', 'Canada', 'C', 9, 10, 20, 45, 25),";
        // Câu 10: Đáp án C
        sql += "('Tác giả của tác phẩm \"Truyện Kiều\" là ai?', 'Nguyễn Khuyến', 'Nguyễn Trãi', 'Nguyễn Du', 'Nguyễn Bỉnh Khiêm', 'C', 10, 10, 15, 60, 15),";

        // 5 câu cuối
        // Câu 11: Đáp án C
        sql += "('Nguyên tố hóa học nào có ký hiệu là Fe?', 'Kẽm', 'Đồng', 'Sắt', 'Chì', 'C', 11, 15, 15, 50, 20),";
        // Câu 12: Đáp án B
        sql += "('World Cup 2022 được tổ chức tại quốc gia nào?', 'Pháp', 'Qatar', 'Brazil', 'Nga', 'B', 12, 20, 45, 20, 15),";
        // Câu 13: Đáp án B
        sql += "('Ai là vị vua cuối cùng của triều đại nhà Nguyễn?', 'Khải Định', 'Bảo Đại', 'Duy Tân', 'Hàm Nghi', 'B', 13, 25, 40, 20, 15),";
        // Câu 14: Đáp án A
        sql += "('Tên thật của nhà văn Tô Hoài là gì?', 'Nguyễn Sen', 'Nguyễn Khải', 'Đoàn Giỏi', 'Nam Cao', 'A', 14, 35, 25, 20, 20),";
        // Câu 15: Đáp án C
        sql += "('Kiến trúc sư thiết kế Tháp Eiffel là người nước nào?', 'Anh', 'Đức', 'Pháp', 'Ý', 'C', 15, 20, 20, 32, 28)";

        // Thực thi lệnh insert
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS Question");
        db.execSQL("DROP TABLE IF EXISTS Difficulty");
        db.execSQL("DROP TABLE IF EXISTS HighScore");
        // Tạo lại từ đầu
        onCreate(db);
    }
    public long insertQuestion(String content, String a, String b, String c, String d,
                               String correct, int difficulty,
                               int rateA, int rateB, int rateC, int rateD) {

        SQLiteDatabase db = this.getWritableDatabase();
        android.content.ContentValues values = new android.content.ContentValues();

        values.put("content", content);
        values.put("a", a);
        values.put("b", b);
        values.put("c", c);
        values.put("d", d);
        values.put("correct", correct);
        values.put("difficulty_id", difficulty);
        values.put("rate_a", rateA);
        values.put("rate_b", rateB);
        values.put("rate_c", rateC);
        values.put("rate_d", rateD);

        long result = db.insert("Question", null, values);
        db.close();
        return result;
    }
}