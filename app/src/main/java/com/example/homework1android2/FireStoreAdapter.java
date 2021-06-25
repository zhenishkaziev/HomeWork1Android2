package com.example.homework1android2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FireStoreAdapter extends RecyclerView.Adapter<FireStoreAdapter.ViewHolder> {

    List <FireModel> list = new ArrayList<>();

     public void addMessage(List<FireModel> models) {
         this.list = models;
         list.add((FireModel) models);
         notifyDataSetChanged();
     }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fire, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
               holder.txtFireText.setText(list.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return  list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtFireText;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtFireText = itemView.findViewById(R.id.item_fire_text);
        }
    }
}
