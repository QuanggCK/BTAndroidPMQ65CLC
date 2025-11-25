package clc65.quanggck.vidusqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getBookData();

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

        // Tracking datas
        String sqlSelectAll = "SELECT * FROM BOOKS";
        Cursor resultSet = db.rawQuery(sqlSelectAll, null);
        ArrayList<String> dstenSach = new ArrayList<String>();
        ArrayList<String> dsSach = new ArrayList<String>();


        // Move to first record
        if (resultSet.moveToFirst()) {
            do {
                // Take data of current record, pointed by resultSet
                int maSach = resultSet.getInt(0); // Inside is columnIndex
                String tenSach = resultSet.getString(1);
                int soTrang = resultSet.getInt(2);
                float gia = resultSet.getFloat(3);
                String moTa = resultSet.getString(4);

                // Wrap into one subject => Create an entity(Java class method)
                BOOKS book = new BOOKS(maSach, tenSach, soTrang, gia, moTa);

                // At this demo work, we just print the data on ListView

                // Add into a list variable

                /// Use an ArrayList to save BookName
                dstenSach.add(tenSach);
                dsSach.add(book.toString());

                // Move to the next record, if not, break the loop
            } while (resultSet.moveToNext());
        }

        // Test
        resultSet.close();
        db.close();

        // Print the data on ListView
        ListView lvDsTenSach = findViewById(R.id.lvDsTenSach);
        ArrayAdapter<String> adapterTenSach = new ArrayAdapter<String>(MainActivity.this,
                                                                android.R.layout.simple_list_item_1,
                                                                dstenSach);
        lvDsTenSach.setAdapter(adapterTenSach);

    }
    ArrayList<String> getBookData(){
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

        // Tracking datas
        String sqlSelectAll = "SELECT * FROM BOOKS";
        Cursor resultSet = db.rawQuery(sqlSelectAll, null);
        ArrayList<String> dstenSach = new ArrayList<String>();
        ArrayList<String> dsSach = new ArrayList<String>();


        // Move to first record
        if (resultSet.moveToFirst()) {
            do {
                // Take data of current record, pointed by resultSet
                int maSach = resultSet.getInt(0); // Inside is columnIndex
                String tenSach = resultSet.getString(1);
                int soTrang = resultSet.getInt(2);
                float gia = resultSet.getFloat(3);
                String moTa = resultSet.getString(4);

                // Wrap into one subject => Create an entity(Java class method)
                BOOKS book = new BOOKS(maSach, tenSach, soTrang, gia, moTa);

                // At this demo work, we just print the data on ListView

                // Add into a list variable

                /// Use an ArrayList to save BookName
                dstenSach.add(tenSach);
                dsSach.add(book.toString());

                // Move to the next record, if not, break the loop
            } while (resultSet.moveToNext());
        }

        // Test
        resultSet.close();
        db.close();
        return dsSach;
    }
}