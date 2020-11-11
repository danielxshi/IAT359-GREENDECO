package li.xiaoxu.greendeco.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import li.xiaoxu.greendeco.R;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private li.xiaoxu.greendeco.ui.home.HomeViewModel homeViewModel;

    RecyclerView recyclerView;
    Button webButton;
    static final int REQUEST_CODE_ENTRY = 0;

    String[] s1;
    String[] s2;
    int[] images = {R.drawable.carnival_mask, R.drawable.cassette, R.drawable.computer, R.drawable.dinosaur, R.drawable.flamingo};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(li.xiaoxu.greendeco.ui.home.HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {

        //Created by Daniel
        //RecyclerView
        recyclerView = requireView().findViewById(R.id.my_RecyclerView);

        s1 = getResources().getStringArray(R.array.card_icons);
        s2 = getResources().getStringArray(R.array.description);

        MyAdapter myAdapter = new MyAdapter(getActivity(), s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Created by Daniel
        //Implicit Intent to GI Website
        webButton = (Button) getActivity().findViewById(R.id.web_Button);
        webButton.setOnClickListener((v) -> {
            Intent i = new Intent();
            onActivityResult(REQUEST_CODE_ENTRY, RESULT_OK, i);
            getActivity().finish();
        });
    }

    @Override
    public void onActivityResult(int rqCode, int rsCode, Intent data){
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