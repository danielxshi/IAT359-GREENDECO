package li.xiaoxu.greendeco;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/*
MainActivity:
Glossary - RecyclerView
(List of Words)
 */
public class MainActivity extends AppCompatActivity {

    Button btn_nav_home;
    Button btn_nav_map;
    static final int REQUEST_CODE_ENTRY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Created by Daniel
        //Implicit Intent to GI Website
        btn_nav_home = (Button) findViewById(R.id.btn_nav_home);
        btn_nav_home.setOnClickListener((v)->{
            Intent i = new Intent();
            onActivityResult(REQUEST_CODE_ENTRY,RESULT_OK,i);
            finish();
        });

        //Implicit Intent to GI Website
        btn_nav_map = (Button) findViewById(R.id.btn_nav_map);
        btn_nav_map.setOnClickListener((v)->{
            Intent i = new Intent(this, MapsMarkerActivity.class);
            startActivity(i);
        });

        //Implicit Intent to GI Website
        btn_nav_map = (Button) findViewById(R.id.btn_nav_sites);
        btn_nav_map.setOnClickListener((v)->{
            Intent i = new Intent(this, SitesActivity.class);
            startActivity(i);
        });

        //Implicit Intent to settings
        btn_nav_map = (Button) findViewById(R.id.btn_nav_set);
        btn_nav_map.setOnClickListener((v)->{
            Intent i2 = new Intent(this, Settings.class);
            startActivity(i2);
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