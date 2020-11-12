package li.xiaoxu.greendeco;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
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
    Button btn_nav_home;
    Button btn_nav_map;
    static final int REQUEST_CODE_ENTRY = 0;

    String[] s1;
    String[] s2;
    int[] images = {R.drawable.carnival_mask, R.drawable.cassette, R.drawable.computer, R.drawable.dinosaur, R.drawable.flamingo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        btn_nav_home = (Button) findViewById(R.id.btn_nav_home);
        btn_nav_home.setOnClickListener((v)->{
            Intent i = new Intent();
            onActivityResult(REQUEST_CODE_ENTRY,RESULT_OK,i);
            finish();
        });

        //Implicit Intent to GI Website
        btn_nav_map = (Button) findViewById(R.id.btn_nav_map);
        btn_nav_map.setOnClickListener((v)->{
            Intent i2 = new Intent(MainActivity.this, MapsMarkerActivity.class);
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