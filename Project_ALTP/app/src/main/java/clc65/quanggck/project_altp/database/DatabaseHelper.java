package clc65.quanggck.project_altp.database;

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

        // Difficulty
        db.execSQL(
                "CREATE TABLE Difficulty (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL)"
        );

        // Question
        db.execSQL(
                "CREATE TABLE Question (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "content TEXT NOT NULL," +
                        "a TEXT NOT NULL," +
                        "b TEXT NOT NULL," +
                        "c TEXT NOT NULL," +
                        "d TEXT NOT NULL," +
                        "correct TEXT NOT NULL," +
                        "difficulty_id INTEGER NOT NULL)"
        );

        // HighScore
        db.execSQL(
                "CREATE TABLE HighScore (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "player_name TEXT NOT NULL," +
                        "score INTEGER NOT NULL," +
                        "money INTEGER NOT NULL)"
        );

        // dữ liệu mẫu
        db.execSQL("INSERT INTO Difficulty(name) VALUES ('Dễ'),('Trung bình'),('Khó')");

        db.execSQL(
                "INSERT INTO Question(content,a,b,c,d,correct,difficulty_id) VALUES " +
                        "('1 + 1 = ?', '1','2','3','4','B',1)," +
                        "('2 + 2 = ?', '2','3','4','5','C',1)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Question");
        db.execSQL("DROP TABLE IF EXISTS Difficulty");
        db.execSQL("DROP TABLE IF EXISTS HighScore");
        onCreate(db);
    }
}
