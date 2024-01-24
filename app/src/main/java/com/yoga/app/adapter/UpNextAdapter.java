package com.yoga.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yoga.app.R;
import com.yoga.app.activity.FullScreeenVideoActivity;
import com.yoga.app.helpers.Common;
import com.yoga.app.model.SingleYogaModel;
import com.yoga.app.model.UpNext;
import com.yoga.app.yoga.FullScreenVideoFragment;

public class UpNextAdapter extends RecyclerView.Adapter<UpNextAdapter.ViewHolder> {

    Context context;
    SingleYogaModel singleYogaModel;

    public UpNextAdapter(Context context, SingleYogaModel singleYogaModel) {
        this.context = context;
        this.singleYogaModel = singleYogaModel;
    }

    @NonNull
    @Override
    public UpNextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_up_next, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UpNextAdapter.ViewHolder holder, int position) {
        holder.title.setText(""+singleYogaModel.getUpNext().get(position).getTitle());
        Glide.with(context).load(""+singleYogaModel.getImg()).into(holder.imgUpNext);

    }

    @Override
    public int getItemCount() {
        return singleYogaModel.getUpNext().size();
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
                    UpNext model = singleYogaModel.getUpNext().get(getAdapterPosition());
                    Common.openFragment(new FullScreenVideoFragment(model, model.getId(), model.getVideoLink(), model.getTitle(), model.getImg()));

                   /* UpNext model = singleYogaModel.getUpNext().get(getAdapterPosition());
                    Intent intent = new Intent(context, FullScreeenVideoActivity.class);
                    intent.putExtra("videolink",model.getVideoLink());
                    context.startActivity(intent);*/

                }
            });
        }
    }
}
