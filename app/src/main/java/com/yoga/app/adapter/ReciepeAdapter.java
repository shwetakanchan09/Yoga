package com.yoga.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yoga.app.R;
import com.yoga.app.model.SingleDietModel;
import com.yoga.app.model.SingleYogaModel;
import com.yoga.app.model.UpNext;

public class ReciepeAdapter extends RecyclerView.Adapter<ReciepeAdapter.ViewHolder> {
    Context context;
    SingleDietModel singleDietModel;

    public ReciepeAdapter(Context context, SingleDietModel singleDietModel) {
        this.context = context;
        this.singleDietModel = singleDietModel;
    }

    @NonNull
    @Override
    public ReciepeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reciepe, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReciepeAdapter.ViewHolder holder, int position) {
        holder.txtReciepe.setText(""+singleDietModel.getUpNext().get(position).getIngredients());

    }

    @Override
    public int getItemCount() {
        return singleDietModel.getUpNext().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtReciepe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtReciepe = itemView.findViewById(R.id.txtReciepe);



        }
    }
}
