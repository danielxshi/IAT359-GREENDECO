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

public class SitesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
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