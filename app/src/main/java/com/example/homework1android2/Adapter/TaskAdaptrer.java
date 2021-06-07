package com.example.homework1android2.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework1android2.Interface.OnItemClick;
import com.example.homework1android2.R;
import com.example.homework1android2.SharedPreference;
import com.example.homework1android2.model.TaskmOdel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskAdaptrer extends RecyclerView.Adapter<TaskAdaptrer.ViewHolder> {

  public OnItemClick onItemClick;
     public List<TaskmOdel> list = new ArrayList<>();
    SharedPreference sp;


//    public TaskAdaptrer(List<TaskmOdel> list) {
//        this.list = list;
//    }

    public  void addModel (TaskmOdel model, OnItemClick onItemClick){
        this.onItemClick = onItemClick;
        list.add(model);
        notifyDataSetChanged();
    }

    public void editModel(TaskmOdel taskmOdel, int position){
        list.get(position).setTitle(taskmOdel.getTitle());
        notifyItemChanged(position);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task
                , parent, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_ttext);


        }

        public void onBind(TaskmOdel model) {
            title.setText(model.getTitle());
            itemView.setOnClickListener(v -> {
                onItemClick.onItemClick(getAdapterPosition(), model);
            });
            if (model.getBackground() != null) {
                switch (model.getBackground()){
                    case "black":
                        title.setTextColor(Color.parseColor("#B1B1B1"));
                        itemView.setBackgroundResource(R.drawable.item_draw);
                        break;
                    case "white":
                        title.setTextColor(Color.parseColor("#A1865E"));
                        itemView.setBackgroundResource(R.drawable.draw_white);
                        break;
                    case "red":
                         title.setTextColor(Color.parseColor("#EAA72E"));
                         itemView.setBackgroundResource(R.drawable.draw_red);
                         break;
                }

            }
        }

    }
    public void filterList(List<TaskmOdel> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
    }
