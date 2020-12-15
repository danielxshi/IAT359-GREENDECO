package li.xiaoxu.greendeco;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //https://www.akshayrana.in/2020/07/bottom-navigation-bar-in-android.html
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        bottomNav.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    navController.navigate(R.id.navigation_home);
                    navController.popBackStack(R.id.navigation_home, true);
                    return true;

                case R.id.navigation_sites:
                    navController.navigate(R.id.navigation_sites);
                    return true;

                case R.id.navigation_maps:
                    navController.navigate(R.id.navigation_maps);
                    return true;

                case R.id.navigation_settings:
                    navController.navigate(R.id.navigation_settings);
                    return true;
            }
            return false;
        });
    }
}