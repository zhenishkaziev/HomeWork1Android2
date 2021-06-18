package com.example.homework1android2;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.homework1android2.db.AppDataBase;

public class App extends Application {

    public static AppDataBase instance = null;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
              SharedPreference.init(this);
    }
     public static AppDataBase getInstance(Context context) {
         if (instance == null){
             instance = Room.databaseBuilder(context, AppDataBase.class, "task-database")
                     .allowMainThreadQueries()
                     .fallbackToDestructiveMigration()
                     .build();
         }
         return instance;
     }

}
