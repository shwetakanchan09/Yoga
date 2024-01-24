package com.yoga.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import com.yoga.app.R;
import com.yoga.app.helpers.Common;
import com.yoga.app.model.DietModel;
import com.yoga.app.yoga.DetailVideoFragment;

import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.ViewHolder> {
    Context context;
    List<DietModel> dietModelList;

    public DietAdapter(Context context, List<DietModel> dietModelList) {
        this.context = context;
        this.dietModelList = dietModelList;
    }

    @NonNull
    @Override
    public DietAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yoga_video, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DietAdapter.ViewHolder holder, int position) {
        DietModel model = dietModelList.get(position);
        holder.txtHeading.setText(model.getTitle());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dietModelList.get(position).getImg())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPic);

    }

    @Override
    public int getItemCount() {
        return dietModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPic;
        TextView txtHeading;
        YouTubePlayerView url;
        ConstraintLayout llscreen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHeading = itemView.findViewById(R.id.txtHeading);
            // url = itemView.findViewById(R.id.url);
            llscreen = itemView.findViewById(R.id.llscreen);
            imgPic = itemView.findViewById(R.id.imgYoga);
            llscreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DietModel model=dietModelList.get(getAdapterPosition());
                    Common.openFragment(new DetailVideoFragment(model, model.getId(), model.getVideoLink(), model.getTitle(), model.getImg()));
                }
            });

        }
    }
}
