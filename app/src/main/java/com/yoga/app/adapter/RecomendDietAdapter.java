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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import com.yoga.app.R;
import com.yoga.app.helpers.Common;
import com.yoga.app.model.PopularYogaModel;
import com.yoga.app.model.RecommDietModel;
import com.yoga.app.yoga.DetailVideoFragment;
import com.yoga.app.yoga.FullScreenVideoFragment;

import java.util.List;

public class RecomendDietAdapter extends RecyclerView.Adapter<RecomendDietAdapter.ViewHolder> {
    Context context;
    List<RecommDietModel> recommDietModelList;

    public RecomendDietAdapter(Context context, List<RecommDietModel> recommDietModelList) {
        this.context = context;
        this.recommDietModelList = recommDietModelList;
    }

    @NonNull
    @Override
    public RecomendDietAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recom_diet, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecomendDietAdapter.ViewHolder holder, int position) {
        RecommDietModel model = recommDietModelList.get(position);
        holder.txtHeading.setText(model.getTitle());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(recommDietModelList.get(position).getImg())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPic);

    }

    @Override
    public int getItemCount() {
        return recommDietModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPic;
        TextView txtHeading;
        YouTubePlayerView url;
        LinearLayout llscreen;
        RecommDietModel recommDietModel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHeading = itemView.findViewById(R.id.txtTitle);
            llscreen = itemView.findViewById(R.id.llScreenDiet);
            imgPic = itemView.findViewById(R.id.imgRecom);
            llscreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RecommDietModel model=recommDietModelList.get(getAdapterPosition());
                    Common.openFragment(new FullScreenVideoFragment(model, model.getId(), model.getVideoLink(), model.getTitle(), model.getImg()));
                   // Common.openFragment(new DetailVideoFragment(recommDietModel, recommDietModelList.get(getAdapterPosition()).getVideoLink()));
                }
            });
        }
    }
}
