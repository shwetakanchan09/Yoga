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
import com.squareup.picasso.Picasso;
import com.yoga.app.R;
import com.yoga.app.helpers.Common;
import com.yoga.app.model.PopularYogaModel;
import com.yoga.app.model.WeightLossModel;
import com.yoga.app.yoga.DetailVideoFragment;
import com.yoga.app.yoga.FullScreenVideoFragment;

import java.util.List;

public class WeightLossAdapter extends RecyclerView.Adapter<WeightLossAdapter.ViewHolder> {
    Context context;
    List<WeightLossModel> weightLossModelList;

    public WeightLossAdapter(Context context, List<WeightLossModel> weightLossModelList) {
        this.context = context;
        this.weightLossModelList = weightLossModelList;
    }

    @NonNull
    @Override
    public WeightLossAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weightloss, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeightLossAdapter.ViewHolder holder, int position) {
        WeightLossModel model = weightLossModelList.get(position);
        holder.txtTitle.setText(model.getTitle());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(weightLossModelList.get(position).getImg())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPic);


    }

    @Override
    public int getItemCount() {
        return weightLossModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPic;
        TextView txtTitle;
        RelativeLayout llscreen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPic = itemView.findViewById(R.id.imgWeight);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            llscreen = itemView.findViewById(R.id.llscreen);
            llscreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WeightLossModel model=weightLossModelList.get(getAdapterPosition());
                    Common.openFragment(new FullScreenVideoFragment(model, model.getId(), model.getVideoLink(), model.getTitle(), model.getImg()));
                    //Common.openFragment(new DetailVideoFragment(weightLossModel,weightLossModelList.get(getAdapterPosition()).getVideoLink()));
                }
            });

        }
    }
}
