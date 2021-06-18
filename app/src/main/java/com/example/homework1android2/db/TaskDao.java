package com.example.homework1android2.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.homework1android2.model.TaskmOdel;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM taskmodel")
    LiveData<List <TaskmOdel>> getAll();

    @Insert
    void insert(TaskmOdel taskmOdel);

    @Delete
     void  delete (TaskmOdel model);

    @Update
    void update (TaskmOdel taskmOdel);
}


