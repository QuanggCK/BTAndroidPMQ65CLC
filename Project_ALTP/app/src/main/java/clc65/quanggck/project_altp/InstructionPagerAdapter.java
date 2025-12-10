package clc65.quanggck.project_altp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class InstructionPagerAdapter extends FragmentStateAdapter {

    public InstructionPagerAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new InstructionPage1Fragment();
        } else {
            return new InstructionPage2Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
