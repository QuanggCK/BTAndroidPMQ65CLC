package clc65.quanggck.onthigk;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class BaiTapThemActivity extends AppCompatActivity {
    Button btnBack;
    ToggleButton toggle_light;
    ConstraintLayout mainLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai_tap_them);
        btnBack = findViewById(R.id.btnBack);
        toggle_light = findViewById(R.id.toggle_light);
        mainLayout = findViewById(R.id.main);
        btnBack.setOnClickListener(v ->
                finish());
        ;

        toggle_light.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mainLayout.setBackgroundColor(Color.BLACK);
                drawRandomStars(mainLayout);
            } else {
                mainLayout.setBackgroundColor(Color.WHITE);
                mainLayout.setForeground(null);
            }
        });
    }
    private void drawRandomStars(View view) {
        int width = view.getWidth();
        int height = view.getHeight();

        int numStars = 100;
        Random random = new Random();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        for (int i = 0; i < numStars; i++) {
            float x = random.nextInt(width);
            float y = random.nextInt(height);
            float radius = 1 + random.nextFloat() * 3; // bán kính 1-4 px
            canvas.drawCircle(x, y, radius, paint);
        }

        // Đặt bitmap này làm lớp phủ trên layout
        view.setForeground(new BitmapDrawable(getResources(), bitmap));
    }
}