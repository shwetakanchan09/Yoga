package com.yoga.app.adapter;

import static androidx.core.view.GravityCompat.apply;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.yoga.app.R;
import com.yoga.app.helpers.Common;
import com.yoga.app.model.SingleYogaModel;
import com.yoga.app.yoga.DetailVideoFragment;
import com.yoga.app.model.YogaModel;

import java.util.List;

public class YogaAdapter extends RecyclerView.Adapter<YogaAdapter.ViewHolder> {
    Context context;
    List<YogaModel> yogaModelList;

    public YogaAdapter(Context context, List<YogaModel> yogaModelList) {
        this.context = context;
        this.yogaModelList = yogaModelList;
    }

    @NonNull
    @Override
    public YogaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yoga_video, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull YogaAdapter.ViewHolder holder, int position) {
        YogaModel model = yogaModelList.get(position);
        holder.txtHeading.setText(model.getTitle());
        Log.d("TAG", "onBindViewHolder: "+model.getImg());
        Glide.with(context).load(model.getImg()).into(holder.imgPic);

/*
        holder.llscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailYogaFragment.class);
                intent.putExtra("video", model.getVideoLink());
                context.startActivity(intent);
            }
        });
*/

      /*  holder.url.getYouTubePlayerWhenReady(youTubePlayer -> model.getVideoLink());
        holder.url.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "dRadE0kjS_Q";
                youTubePlayer.loadVideo(model.getVideoLink(), 0f);
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return yogaModelList.size();
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
                    YogaModel model=yogaModelList.get(getAdapterPosition());
                    Common.openFragment(new DetailVideoFragment(model, model.getId(), model.getVideoLink(), model.getTitle(), model.getImg()));
                }
            });

        }
    }
}
