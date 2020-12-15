package li.xiaoxu.greendeco.ui.sites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import li.xiaoxu.greendeco.MyAdapter;
import li.xiaoxu.greendeco.MyDatabase;
import li.xiaoxu.greendeco.MyHelper;
import li.xiaoxu.greendeco.R;

public class SitesFragment extends Fragment implements AdapterView.OnItemClickListener{

    RecyclerView myRecycler;
    MyDatabase db;
    MyAdapter myAdapter;
    MyHelper helper;

    private SitesViewModel sitesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_sites, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myRecycler = (RecyclerView) getView().findViewById(R.id.RecyclerView_sites);
        myRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        db = new MyDatabase(getActivity());
        helper = new MyHelper(getContext());

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
        Toast.makeText(getContext(), "row " + (1+position) + ":  " + siteAddressTextView.getText() + " " + siteTypologyTextView.getText() + " " + siteDescriptionTextView.getText(), Toast.LENGTH_LONG).show();
    }
}
