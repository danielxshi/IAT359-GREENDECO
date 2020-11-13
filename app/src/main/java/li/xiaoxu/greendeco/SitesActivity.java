package li.xiaoxu.greendeco;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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

public class SitesActivity extends Activity implements AdapterView.OnItemClickListener {
    RecyclerView myRecycler;
    MyDatabase db;
    MyAdapter myAdapter;
    MyHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites);
        myRecycler = (RecyclerView) findViewById(R.id.my_RecyclerView);

        db = new MyDatabase(this);
        helper = new MyHelper(this);

        Intent i = getIntent();

        String query = i.getStringExtra("query"); //TODO
        ArrayList<String> mArrayList;

        if (query.equals("all")){
            Cursor cursor = db.getData();

            int index1 = cursor.getColumnIndex(Constants.ADDRESS);
            int index2 = cursor.getColumnIndex(Constants.TYPOLOGY);
            int index3 = cursor.getColumnIndex(Constants.DESCRIPTION);

            mArrayList = new ArrayList<String>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String siteaddress = cursor.getString(index1);
                String sitetypology = cursor.getString(index2);
                String sitedescription = cursor.getString(index3);

                String s = siteaddress + "," + sitetypology + "," + sitedescription;

                mArrayList.add(s);
                cursor.moveToNext();
            }
            db.getAllTheData();
        } else {
            mArrayList = db.getSelectedData(query);
        }



        myAdapter = new MyAdapter(mArrayList);
        myRecycler.setAdapter(myAdapter);
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

//public class SitesActivity extends AppCompatActivity {
//
//    RecyclerView recyclerView;
//
//
//    String[] s1;
//    String[] s2;
//    int[] images = {R.drawable.carnival_mask, R.drawable.cassette, R.drawable.computer, R.drawable.dinosaur, R.drawable.flamingo};
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sites);
//
//    //Created by Daniel
//    //RecyclerView
//    recyclerView = findViewById(R.id.my_RecyclerView);
//
//    s1 = getResources().getStringArray(R.array.card_icons);
//    s2 = getResources().getStringArray(R.array.description);
//
//    MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);
//        recyclerView.setAdapter(myAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//
//}
//
//
//}