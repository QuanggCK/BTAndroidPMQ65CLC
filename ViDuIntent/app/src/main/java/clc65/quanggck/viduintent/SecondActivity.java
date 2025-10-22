package clc65.quanggck.viduintent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //1. Nhan ve Intent
        Intent iNhanDuoc = getIntent();
        //2. Boc ra
        iNhanDuoc.getStringExtra("ht");
        iNhanDuoc.getStringExtra("cjd");
        iNhanDuoc.getIntExtra("tuoi",0);
        //3. Xu ly
            //3.1 Set len TextView




        //Nut Back
        Button btn_return = findViewById(R.id.btn_return);
        btn_return.setOnClickListener(v -> {
            Intent iQuayLai = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(iQuayLai);
        });

    }
}