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
import com.yoga.app.model.PopularYogaModel;
import com.yoga.app.model.RecomendMediModel;
import com.yoga.app.yoga.DetailVideoFragment;
import com.yoga.app.yoga.FullScreenVideoFragment;

import java.util.List;

public class RecommendMediAdapter extends RecyclerView.Adapter<RecommendMediAdapter.ViewHolder> {
    Context context;
    List<RecomendMediModel> recomendMediModelList;

    public RecommendMediAdapter(Context context, List<RecomendMediModel> recomendMediModelList) {
        this.context = context;
        this.recomendMediModelList = recomendMediModelList;
    }

    @NonNull
    @Override
    public RecommendMediAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recomend_meditation, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendMediAdapter.ViewHolder holder, int position) {
        RecomendMediModel model = recomendMediModelList.get(position);
        holder.txtTitle.setText(model.getTitle());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(recomendMediModelList.get(position).getImg())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPic);
    }

    @Override
    public int getItemCount() {
        return recomendMediModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPic;
        TextView txtTitle;
        LinearLayout llscreen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPic = itemView.findViewById(R.id.imgRecom);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            llscreen = itemView.findViewById(R.id.llscreen);
            llscreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RecomendMediModel model=recomendMediModelList.get(getAdapterPosition());
                    Common.openFragment(new FullScreenVideoFragment(model, model.getId(), model.getVideoLink(), model.getTitle(), model.getImg()));
                   // Common.openFragment(new DetailVideoFragment(recomendMediModel, recomendMediModelList.get(getAdapterPosition()).getVideoLink()));
                }
            });

        }
    }
}
