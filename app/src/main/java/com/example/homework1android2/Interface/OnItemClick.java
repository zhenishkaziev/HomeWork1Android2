package com.example.homework1android2.Interface;

import com.example.homework1android2.model.TaskmOdel;

public interface OnItemClick {
     void onItemClickk(int position, TaskmOdel taskmOdel);

     void deleteClick(int position, TaskmOdel taskmOdel);

     void onLOngClickListener (int position, TaskmOdel taskmOdel);

}
