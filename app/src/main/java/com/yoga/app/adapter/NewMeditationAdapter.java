package com.yoga.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.yoga.app.R;
import com.yoga.app.helpers.Common;
import com.yoga.app.model.NewMeditationModel;
import com.yoga.app.model.PopularYogaModel;
import com.yoga.app.yoga.DetailVideoFragment;
import com.yoga.app.yoga.FullScreenVideoFragment;

import java.util.List;

public class NewMeditationAdapter extends RecyclerView.Adapter<NewMeditationAdapter.ViewHolder> {
    Context context;
    List<NewMeditationModel> newMeditationModelList;

    public NewMeditationAdapter(Context context, List<NewMeditationModel> newMeditationModelList) {
        this.context = context;
        this.newMeditationModelList = newMeditationModelList;
    }

    @NonNull
    @Override
    public NewMeditationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_meditation, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewMeditationAdapter.ViewHolder holder, int position) {
        NewMeditationModel model = newMeditationModelList.get(position);
        holder.txtTitle.setText(model.getTitle());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(newMeditationModelList.get(position).getImg())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPic);
    }

    @Override
    public int getItemCount() {
        return newMeditationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPic;
        TextView txtTitle;
        LinearLayout llscreen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtMediTitle);
            imgPic = itemView.findViewById(R.id.imgNew);
            llscreen = itemView.findViewById(R.id.llscreen);

            llscreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NewMeditationModel model=newMeditationModelList.get(getAdapterPosition());
                    Common.openFragment(new FullScreenVideoFragment(model,model.getId(), model.getVideoLink(), model.getTitle(), model.getImg()));
                   // Common.openFragment(new DetailVideoFragment(newMeditationModel, newMeditationModelList.get(getAdapterPosition()).getVideoLink()));
                }
            });
        }
    }
}
