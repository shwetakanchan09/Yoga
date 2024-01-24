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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yoga.app.R;
import com.yoga.app.helpers.Common;
import com.yoga.app.model.DietModel;
import com.yoga.app.model.PopularYogaModel;
import com.yoga.app.yoga.DetailVideoFragment;
import com.yoga.app.yoga.FullScreenVideoFragment;

import java.util.List;

public class PopularYogaAdapter extends RecyclerView.Adapter<PopularYogaAdapter.ViewHolder> {
    Context context;
    List<PopularYogaModel> popularYogaModelList;

    RequestOptions option;
    public PopularYogaAdapter(Context context, List<PopularYogaModel> popularYogaModelList) {
        this.context = context;
        this.popularYogaModelList = popularYogaModelList;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);
    }

    @NonNull
    @Override
    public PopularYogaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_yoga, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularYogaAdapter.ViewHolder holder, int position) {
        PopularYogaModel model = popularYogaModelList.get(position);
        holder.txtTitle.setText(model.getTitle());

       /* Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(popularYogaModelList.get(position).getImg())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPic);*/

        Glide.with(context)
                .load(popularYogaModelList.get(position).getImg()).apply(option)
                .into(holder.imgPic);

    }

    @Override
    public int getItemCount() {
        return popularYogaModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        ImageView imgPic;
        TextView txtTitle;
        RelativeLayout llscreen;

      //  YouTubePlayerView url;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            txtTitle = mView.findViewById(R.id.txtTitle);
            imgPic = mView.findViewById(R.id.imgPopular);
            llscreen = mView.findViewById(R.id.llscreen);

            llscreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Common.openFragment(new DetailVideoFragment(popularYogaModel, popularYogaModelList.get(getAdapterPosition()).getVideoLink()));
                    PopularYogaModel model=popularYogaModelList.get(getAdapterPosition());
                    Common.openFragment(new FullScreenVideoFragment(model, model.getId(), model.getVideoLink(), model.getTitle(), model.getImg()));
                }
            });

        }
    }
}
