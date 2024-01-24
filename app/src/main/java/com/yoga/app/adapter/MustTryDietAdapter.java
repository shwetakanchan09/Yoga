package com.yoga.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import com.yoga.app.R;
import com.yoga.app.helpers.Common;
import com.yoga.app.model.MustTryDietModel;
import com.yoga.app.model.PopularYogaModel;
import com.yoga.app.yoga.DetailVideoFragment;
import com.yoga.app.yoga.FullScreenVideoFragment;

import java.util.List;

public class MustTryDietAdapter extends RecyclerView.Adapter<MustTryDietAdapter.ViewHolder> {
    Context context;
    List<MustTryDietModel> mustTryDietModelList;

    public MustTryDietAdapter(Context context, List<MustTryDietModel> mustTryDietModelList) {
        this.context = context;
        this.mustTryDietModelList = mustTryDietModelList;
    }

    @NonNull
    @Override
    public MustTryDietAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommended, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MustTryDietAdapter.ViewHolder holder, int position) {
        MustTryDietModel model = mustTryDietModelList.get(position);
        holder.txtHeading.setText(model.getTitle());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(mustTryDietModelList.get(position).getImg())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPic);

    }

    @Override
    public int getItemCount() {
        return mustTryDietModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPic;
        TextView txtHeading;
        YouTubePlayerView url;
        RelativeLayout llscreen;
        MustTryDietModel mustTryDietModel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHeading = itemView.findViewById(R.id.txtHeading);
            llscreen = itemView.findViewById(R.id.llscreen);
            imgPic = itemView.findViewById(R.id.imgYoga);
            llscreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MustTryDietModel model=mustTryDietModelList.get(getAdapterPosition());
                    Common.openFragment(new FullScreenVideoFragment(model, model.getId(), model.getVideoLink(), model.getTitle(), model.getImg()));
                   // Common.openFragment(new DetailVideoFragment(mustTryDietModel, mustTryDietModelList.get(getAdapterPosition()).getVideoLink()));
                }
            });

        }
    }
}
