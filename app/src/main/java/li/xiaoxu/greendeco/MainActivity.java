package li.xiaoxu.greendeco;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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

        openFragment(new HomeFragment());


        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        openFragment(new HomeFragment());
                        return true;

                    case R.id.navigation_sites:
                        openFragment(new SitesFragment());
                        return true;

                    case R.id.navigation_maps:
                        openFragment(new MapsFragment());
                        return true;

                    case R.id.navigation_settings:
                        openFragment(new SettingsFragment());
                        return true;
                }
                return false;
            }
        });
    }

    void openFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}