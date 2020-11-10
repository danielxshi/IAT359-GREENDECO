package li.xiaoxu.greendeco;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/*
MainActivity:
Glossary - RecyclerView
(List of Words)
 */
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button webButton;
    static final int REQUEST_CODE_ENTRY = 0;

    String[] s1;
    String[] s2;
    int[] images = {R.drawable.carnival_mask, R.drawable.cassette, R.drawable.computer, R.drawable.dinosaur, R.drawable.flamingo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //Created by Daniel
        //RecyclerView
        recyclerView = findViewById(R.id.my_RecyclerView);

        s1 = getResources().getStringArray(R.array.card_icons);
        s2 = getResources().getStringArray(R.array.description);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Created by Daniel
        //Implicit Intent to GI Website
        webButton = (Button) findViewById(R.id.web_Button);
        webButton.setOnClickListener((v)->{
            Intent i = new Intent();
            onActivityResult(REQUEST_CODE_ENTRY,RESULT_OK,i);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int rqCode, int rsCode, Intent data){
        if(rqCode==REQUEST_CODE_ENTRY){
            if(rsCode==RESULT_OK){
                Uri webpage = Uri.parse("https://www.google.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW,webpage);
                startActivity(webIntent);
            }
        }
        super.onActivityResult(rqCode, rsCode, data);
    }

}