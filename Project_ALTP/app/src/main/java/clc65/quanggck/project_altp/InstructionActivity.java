package clc65.quanggck.project_altp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class InstructionActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    WormDotsIndicator dotsIndicator;

    public void TimCT() {
        viewPager = findViewById(R.id.viewPagerInstruction);
        dotsIndicator = findViewById(R.id.dotsIndicator);
    }

    public void SetUpViewPager() {
        InstructionPagerAdapter adapter = new InstructionPagerAdapter(this);
        viewPager.setAdapter(adapter);
        dotsIndicator.attachTo(viewPager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_instruction);

        TimCT();
        SetUpViewPager();
    }
}
