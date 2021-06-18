package com.example.homework1android2.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.homework1android2.model.TaskmOdel;

@Database(entities = {TaskmOdel.class}, version = 1, exportSchema = false)

public abstract class AppDataBase extends RoomDatabase {
     public abstract TaskDao getTaskDao();

}
