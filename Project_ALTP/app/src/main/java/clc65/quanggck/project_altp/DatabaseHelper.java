package clc65.quanggck.project_altp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "altp.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Difficulty (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL)");

        db.execSQL("CREATE TABLE Question (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "content TEXT NOT NULL," +
                "a TEXT NOT NULL," +
                "b TEXT NOT NULL," +
                "c TEXT NOT NULL," +
                "d TEXT NOT NULL," +
                "correct TEXT NOT NULL," +
                "difficulty_id INTEGER," +
                "FOREIGN KEY(difficulty_id) REFERENCES Difficulty(id))");

        db.execSQL("CREATE TABLE HighScore (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "player_name TEXT NOT NULL," +
                "score INTEGER NOT NULL," +
                "difficulty_id INTEGER," +
                "play_time DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY(difficulty_id) REFERENCES Difficulty(id))");


        // dữ liệu mẫu
        db.execSQL("INSERT INTO Difficulty(name) VALUES ('Dễ'),('Trung bình'),('Khó')");

        db.execSQL(
                "INSERT INTO Question(content,a,b,c,d,correct,difficulty_id) VALUES " +
                        "('Thủ đô Việt Nam là?', 'Hà Nội','Huế','Đà Nẵng','HCM','A',1)," +
                        "('2 + 2 = ?', '3','4','5','6','B',1)," +
                        "('Java là gì?', 'Ngôn ngữ','HĐH','Trình duyệt','Game','A',2)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Question");
        db.execSQL("DROP TABLE IF EXISTS Difficulty");
        onCreate(db);
    }
}
