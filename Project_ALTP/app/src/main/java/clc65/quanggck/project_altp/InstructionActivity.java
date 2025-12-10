package clc65.quanggck.project_altp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_instruction);

        ViewPager2 viewPager = findViewById(R.id.viewPagerInstruction);
        InstructionPagerAdapter adapter = new InstructionPagerAdapter(this);
        viewPager.setAdapter(adapter);

    }
}
