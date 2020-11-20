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

public class SitesActivity extends Activity {
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

        ArrayList<String> mArrayList;

        Cursor cursor = db.getData();

        int index1 = cursor.getColumnIndex(Constants.ADDRESS);
        int index2 = cursor.getColumnIndex(Constants.TYPOLOGY);
        int index3 = cursor.getColumnIndex(Constants.DESCRIPTION);

        mArrayList = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String siteAddress = cursor.getString(index1);
            String siteTypology = cursor.getString(index2);
            String siteDescription = cursor.getString(index3);

            String s = siteAddress + "," + siteTypology + "," + siteDescription;

            mArrayList.add(s);
            cursor.moveToNext();
        }
        db.getSitesData();

        myAdapter = new MyAdapter(mArrayList);
        myRecycler.setAdapter(myAdapter);

        TextView siteAddressTextView = (TextView) findViewById(R.id.siteAddressEntry);
        TextView siteTypologyTextView = (TextView) findViewById(R.id.siteTypologyEntry);
        TextView siteDescriptionTextView = (TextView) findViewById(R.id.siteDescriptionEntry);

        Toast.makeText(this, "row " + 1 + ":  " + siteAddressTextView.getText() + " " + siteTypologyTextView.getText() + " " + siteDescriptionTextView.getText(), Toast.LENGTH_LONG).show();
    }
}