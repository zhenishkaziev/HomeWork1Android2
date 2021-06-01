package com.example.homework1android2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewTreeViewModelKt;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskAdaptrer extends RecyclerView.Adapter<TaskAdaptrer.ViewHolder> {

    List<TaskmOdel> list = new ArrayList<>();



    public TaskAdaptrer(List<TaskmOdel> list) {
        this.list = list;
    }

    public  void addModel (TaskmOdel model){
        list.add(model);
        notifyDataSetChanged();
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       TextView title, description;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
                 title = itemView.findViewById(R.id.title_text);
                 description = itemView.findViewById(R.id.description_text);

        }
        public void  onBind (TaskmOdel model){
            title.setText(model.getTitle());
            description.setText(model.getDescription());
        }
    }
}
