package li.xiaoxu.greendeco.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import li.xiaoxu.greendeco.HomeAdapter;
import li.xiaoxu.greendeco.R;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.home, R.drawable.content1};

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //RecyclerView GI Education cards
        recyclerView = getView().findViewById(R.id.my_RecyclerView);
        s1 = getResources().getStringArray(R.array.green_title);
        s2 = getResources().getStringArray(R.array.educ_description);

        HomeAdapter homeAdapter = new HomeAdapter(getActivity(), s1, s2, images);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}