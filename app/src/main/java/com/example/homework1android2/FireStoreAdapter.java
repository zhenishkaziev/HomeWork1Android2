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

    List<FireModel> list = new ArrayList<>();


    public void addListOfModel (List <FireModel> models){
        list.addAll(models);
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
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtText;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtText = itemView.findViewById(R.id.item_fire_text);
        }

        public void bind(FireModel fireModel) {
                txtText.setText(fireModel.getText());
        }
    }
}
