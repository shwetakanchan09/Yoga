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

import com.bumptech.glide.Glide;
import com.yoga.app.R;
import com.yoga.app.helpers.Common;
import com.yoga.app.model.SingleMeditationModel;
import com.yoga.app.model.UpNext;
import com.yoga.app.yoga.FullScreenVideoFragment;

public class MeditationUpNextAdapter extends RecyclerView.Adapter<MeditationUpNextAdapter.ViewHolder> {
    Context context;
    SingleMeditationModel singleMeditationModel;

    public MeditationUpNextAdapter(Context context, SingleMeditationModel singleMeditationModel) {
        this.context = context;
        this.singleMeditationModel = singleMeditationModel;
    }

    @NonNull
    @Override
    public MeditationUpNextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_up_next, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MeditationUpNextAdapter.ViewHolder holder, int position) {
        holder.title.setText(""+singleMeditationModel.getUpNext().get(position).getTitle());
        Glide.with(context).load(""+singleMeditationModel.getImg()).into(holder.imgUpNext);

    }

    @Override
    public int getItemCount() {
        return singleMeditationModel.getUpNext().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgUpNext;
        TextView title;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUpNext = itemView.findViewById(R.id.imgUpNext);
            title = itemView.findViewById(R.id.txtTitle);
            linearLayout = itemView.findViewById(R.id.llitem);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpNext model = singleMeditationModel.getUpNext().get(getAdapterPosition());
                    Common.openFragment(new FullScreenVideoFragment(model, model.getId(), model.getVideoLink(), model.getTitle(), model.getImg()));
                }
            });

        }
    }
}
