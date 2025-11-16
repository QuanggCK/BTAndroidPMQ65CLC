package clc65.quanggck.lamthem_footerfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("RestrictedApi")
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottom_nav);

        // Load Fragment mặc định
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment;
                int itemId = item.getItemId();
                if (itemId == R.id.btn_home) {
                    selectedFragment = new HomeFragment();
                } else if (itemId == R.id.btn_search) {
                    selectedFragment = new SearchFragment();
                } else if (itemId == R.id.btn_profile) {
                    selectedFragment = new ProfileFragment();
                } else {
                    return false;
                }
                loadFragment(selectedFragment);
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();
    }
}