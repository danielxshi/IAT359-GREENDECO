package li.xiaoxu.greendeco.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import li.xiaoxu.greendeco.MyAdapter;
import li.xiaoxu.greendeco.R;

import static android.app.Activity.RESULT_OK;

public class HomeViewModel extends ViewModel, AppCompatActivity

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    RecyclerView recyclerView;
    Button webButton;
    static final int REQUEST_CODE_ENTRY = 0;

    String[] s1;
    String[] s2;
    int[] images = {R.drawable.carnival_mask, R.drawable.cassette, R.drawable.computer, R.drawable.dinosaur, R.drawable.flamingo};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = recyclerView.findViewById();

        s1 = getResources().getStringArray(R.array.card_icons);
        s2 = getResources().getStringArray(R.array.description);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Created by Daniel
        //Implicit Intent to GI Website
        webButton = (Button) recyclerView.findViewById();
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