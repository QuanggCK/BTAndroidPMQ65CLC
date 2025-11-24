package clc65.quanggck.vidusqllite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create CSDL
        db = openOrCreateDatabase("books.db", MODE_PRIVATE, null);
        //Because these lines above we just have to one-time code, we should not do this
//        // Create table book
//        String sqlXoaBang = "DROP TABLE IF EXISTS BOOKS";
//        String sqlTaoBang = "CREATE TABLE BOOKS(BookID integer PRIMARY KEY, BookName text, Page integer, Price Float, Description text)";
//
//        db.execSQL(sqlXoaBang);
//        db.execSQL(sqlTaoBang);
//        // Add some datas
//        String sqlThem = " INSERT INTO BOOKS VALUES (1,'Shinja Zero',315,105000,'Isekai with Tatasuki Makoto') ";
//        String sqlThem2 = " INSERT INTO BOOKS VALUES (2, 'Shinja Zero 2',320,100000,'Isekai with Tatasuki Makoto') ";
//        db.execSQL(sqlThem);
//        db.execSQL(sqlThem2);
//        /// Keep in mind that we can upload the outside sqllite from DB Browser to this simulator.
//        /// We can do it in both ways (coding or just input directly in the table).

        // Test
        db.close();

    }
}