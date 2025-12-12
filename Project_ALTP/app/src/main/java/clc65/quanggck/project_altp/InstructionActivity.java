package clc65.quanggck.project_altp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class InstructionActivity extends AppCompatActivity {

    Button btn_return;
    WormDotsIndicator dotsIndicator;

    public void TimCT() {
        btn_return = findViewById(R.id.btn_return);
        dotsIndicator = findViewById(R.id.dotsIndicator);
    }

    public void Return(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_instruction);

        ViewPager2 viewPager = findViewById(R.id.viewPagerInstruction);
        InstructionPagerAdapter adapter = new InstructionPagerAdapter(this);
        viewPager.setAdapter(adapter);

        TimCT();

        dotsIndicator.attachTo(viewPager);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Return(v);
            }
        });
    }
}
