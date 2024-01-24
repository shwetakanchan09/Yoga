package com.yoga.app.roomdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yoga.app.R;

import java.util.ArrayList;
import java.util.List;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.ViewHolder> {
    Context context;
    List<Save> arrayList;
    AdapterListener adapterListener;
    RequestOptions option;

    public SaveAdapter(Context context, AdapterListener adapterListener) {
        this.context = context;
        this.adapterListener = adapterListener;
        arrayList = new ArrayList<>();

        option = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);
    }
    public void bookmarkSave(Save save){
        arrayList.add(save);
        notifyDataSetChanged();
    }
    public void clearData(){
        arrayList.clear();
        notifyDataSetChanged();
    }
    public void removeUser(int position) {
        arrayList.remove(position);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public SaveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_yoga, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SaveAdapter.ViewHolder holder, int position) {
        Save save = arrayList.get(position);
        holder.txtTitle.setText(save.getTitle());
        Glide.with(context)
                .load(arrayList.get(position).getImage()).apply(option)
                .into(holder.imgPic);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPic;
        TextView txtTitle;
        RelativeLayout llscreen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            imgPic = itemView.findViewById(R.id.imgPopular);
            llscreen = itemView.findViewById(R.id.llscreen);
        }
    }
}
