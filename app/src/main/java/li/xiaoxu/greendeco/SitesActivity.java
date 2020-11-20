package li.xiaoxu.greendeco;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SitesActivity extends Activity implements AdapterView.OnItemClickListener{
    RecyclerView myRecycler;
    MyDatabase db;
    MyAdapter myAdapter;
    MyHelper helper;
    Button btn_nav_home;
    Button btn_nav_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites);

        myRecycler = (RecyclerView) findViewById(R.id.RecyclerView_sites);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));

        db = new MyDatabase(this);
        helper = new MyHelper(this);

        ArrayList<String> mArrayList;

//
//        //Explicit intent to home page
//        btn_nav_home = (Button) findViewById(R.id.btn_nav_home);
//        btn_nav_home.setOnClickListener((v)->{
//            Intent i = new Intent(this, MainActivity.class);
//            startActivity(i);
//        });
//
//        //Explicit intent to map page
//        btn_nav_map = (Button) findViewById(R.id.btn_nav_map);
//        btn_nav_map.setOnClickListener((v)->{
//            finish();
//            Intent i = new Intent(this, MapsMarkerActivity.class);
//            startActivity(i);
//        });
//
//        //Explicit Intent to GI sites
//        btn_nav_map = (Button) findViewById(R.id.btn_nav_sites);
//        btn_nav_map.setOnClickListener((v)->{
//            finish();
//            Intent i = new Intent(this, SitesActivity.class);
//            startActivity(i);
//        });
//
//        //Explicit Intent to settings
//        btn_nav_map = (Button) findViewById(R.id.btn_nav_set);
//        btn_nav_map.setOnClickListener((v)->{
//            finish();
//            Intent i = new Intent(this, Settings.class);
//            startActivity(i);
//        });


        mArrayList = db.getSitesData();
        myAdapter = new MyAdapter(mArrayList);
        myRecycler.setAdapter(myAdapter);

//        Toast.makeText(this, "row " + 1 + ":  " + siteAddressTextView.getText() + " " + siteTypologyTextView.getText() + " " + siteDescriptionTextView.getText(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LinearLayout clickedRow = (LinearLayout) view;
        TextView siteAddressTextView = (TextView) view.findViewById(R.id.siteAddressEntry);
        TextView siteTypologyTextView = (TextView) view.findViewById(R.id.siteTypologyEntry);
        TextView siteDescriptionTextView = (TextView) view.findViewById(R.id.siteDescriptionEntry);
        Toast.makeText(this, "row " + (1+position) + ":  " + siteAddressTextView.getText() + " " + siteTypologyTextView.getText() + " " + siteDescriptionTextView.getText(), Toast.LENGTH_LONG).show();
    }
}