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
        db = openOrCreateDatabase("books", MODE_PRIVATE, null);
        // Create table book
        String sqlXoaBang = "DROP TABLE IF EXISTS BOOKS";
        String sqlTaoBang = "CREATE TABLE BOOKS(BookID integer PRIMARY KEY, BookName text, Page integer, Price Float, Description text)";

        db.execSQL(sqlXoaBang);
        db.execSQL(sqlTaoBang);

        // Test
        db.close();

    }
}