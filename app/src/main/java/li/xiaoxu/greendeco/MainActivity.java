package li.xiaoxu.greendeco;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*
MainActivity:
Glossary - RecyclerView
(List of Words)
 */
public class MainActivity extends AppCompatActivity {

    //Recycler GI Education Cards
    RecyclerView recyclerView;

    String s1[], s2[];
    int images[] = {R.drawable.home, R.drawable.content1};

    Button btn_nav_home;
    Button btn_nav_map;
    static final int REQUEST_CODE_ENTRY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView GI Education cards
        recyclerView = findViewById(R.id.my_RecyclerView);

        s1 = getResources().getStringArray(R.array.green_title);
        s2 = getResources().getStringArray(R.array.educ_description);

        HomeAdapter homeAdapter = new HomeAdapter(this, s1, s2, images);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Explicit intent to home page
        btn_nav_home = (Button) findViewById(R.id.btn_nav_home);
        btn_nav_home.setOnClickListener((v)->{
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        //Explicit intent to map page
        btn_nav_map = (Button) findViewById(R.id.btn_nav_map);
        btn_nav_map.setOnClickListener((v)->{
            finish();
            Intent i = new Intent(this, MapsMarkerActivity.class);
            startActivity(i);
        });

        //Explicit Intent to GI sites
        btn_nav_map = (Button) findViewById(R.id.btn_nav_sites);
        btn_nav_map.setOnClickListener((v)->{
            finish();
            Intent i = new Intent(this, SitesActivity.class);
            startActivity(i);
        });

        //Explicit Intent to settings
        btn_nav_map = (Button) findViewById(R.id.btn_nav_set);
        btn_nav_map.setOnClickListener((v)->{
            finish();
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
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