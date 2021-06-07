package com.example.homework1android2.Interface;

import com.example.homework1android2.model.TaskmOdel;

public interface OnItemClick {
     void onItemClick (int position, TaskmOdel taskmOdel);

     void onItemLongClick (int position);
}
