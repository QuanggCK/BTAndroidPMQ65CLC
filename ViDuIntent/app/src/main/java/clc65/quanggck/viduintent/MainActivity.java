package clc65.quanggck.viduintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_transist = findViewById(R.id.btn_transist);
        btn_transist.setOnClickListener(new View.OnClickListener()
        {
           @Override
            public void onClick(View v){
               //1. Tao intent
               Intent iTransit = new Intent(MainActivity.this, SecondActivity.class);
               //2. Goi du lieu vao ITransit
                    //2.1 Lay du lieu
                    EditText edt_name = findViewById(R.id.edt_name);
                    String data = edt_name.getText().toString();
                    //2.2 Goi vao iTransit, dung putExtra(key, value)
                    iTransit.putExtra("ht",data);
                    iTransit.putExtra("cjd","QuanggCK");  // cai value la du lieu co dinh
                    iTransit.putExtra("tuoi",1000);

               //3. Kich hoat man hinh 2
               startActivity(iTransit);


           }
        });
    }
}