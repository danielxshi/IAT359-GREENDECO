package li.xiaoxu.greendeco;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    String data1[], data2[];
    int images[];
    Context context;

    public HomeAdapter(Context ct, String s1[], String s2[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView myText1, myText2;
        ImageView myImage;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            myText1 = itemView.findViewById(R.id.green_title);
            myText2 = itemView.findViewById(R.id.educ_description);
            myImage = itemView.findViewById(R.id.myImageView);
        }
    }

//    Context context = itemView.getContext();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}


