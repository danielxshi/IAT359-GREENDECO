package li.xiaoxu.greendeco;

//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static li.xiaoxu.greendeco.R.layout.row;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public ArrayList<String> list;
    Context context;

    public MyAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(row,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {

        String[]  results = (list.get(position).toString()).split(",");
        if (results.length > 2) { //3 ,
            holder.addressTextView.setText(results[0]);
            holder.typologyTextView.setText(results[1]);
            holder.descriptionTextView.setText(results[2]);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView addressTextView;
        public TextView typologyTextView;
        public TextView descriptionTextView;
        public LinearLayout myLayout;

        Context context;

        public MyViewHolder(View itemView) {
            super(itemView);
            myLayout = (LinearLayout) itemView;

            addressTextView = (TextView) itemView.findViewById(R.id.siteAddressEntry);
            typologyTextView = (TextView) itemView.findViewById(R.id.siteTypologyEntry);
            descriptionTextView = (TextView) itemView.findViewById(R.id.siteDescriptionEntry);

            itemView.setOnClickListener(this);
            context = itemView.getContext();

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return .size();
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,
                    "You have clicked " + ((TextView)view.findViewById(R.id.siteAddressEntry)).getText().toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
//
//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//
//    String data1[], data2[];
//    int images[];
//    Context context;
//
//    public MyAdapter(Context ct, String s1[], String s2[], int img[]){
//        context = ct;
//        data1 = s1;
//        data2 = s2;
//        images = img;
//    }
//
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.my_row,parent,false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
//        holder.myText1.setText(data1[position]);
//        holder.myText2.setText(data2[position]);
//        holder.myImage.setImageResource(images[position]);
//    }
//
//    @Override
//    public int getItemCount() {
//        return images.length;
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView myText1, myText2;
//        ImageView myImage;
//
//        public MyViewHolder(@NonNull View itemView){
//            super(itemView);
//            myText1 = itemView.findViewById(R.id.icon_text);
//            myText2 = itemView.findViewById(R.id.description_txt);
//            myImage = itemView.findViewById(R.id.myImgView);
//        }
//
//    }
//}
