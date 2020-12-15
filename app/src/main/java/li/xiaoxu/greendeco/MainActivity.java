package li.xiaoxu.greendeco;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import li.xiaoxu.greendeco.ui.home.HomeFragment;
import li.xiaoxu.greendeco.ui.maps.MapsFragment;
import li.xiaoxu.greendeco.ui.settings.SettingsFragment;
import li.xiaoxu.greendeco.ui.sites.SitesFragment;

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