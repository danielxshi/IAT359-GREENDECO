package li.xiaoxu.greendeco;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SitesActivity extends Activity {

    EditText selectType;
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites);

        db = new MyDatabase(this);
    }

    public void viewResults(View view)
    {
        Intent intent = new Intent(this, RecyclerActivity.class);
//        intent.putExtra("query", "all");
        startActivity(intent);
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
