package clc65.quanggck.project_altp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class InstructionPaperAdapter extends FragmentStateAdapter {

    public InstructionPaperAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0)
            return new InstructionFragment1();
        else
            return new PlayInstruction2Fragment();
    }

    @Override
    public int getItemCount() {
        return 2;  // có 2 màn hình
    }
}
