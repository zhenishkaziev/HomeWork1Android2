package com.example.homework1android2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
  @Entity

public class TaskmOdel implements Serializable {
      @PrimaryKey(autoGenerate = true)
          int id;

      public int getId() {
          return id;
      }

      public void setId(int id) {
          this.id = id;
      }

      String title, background;

    public String getTitle() {
        return title;
    }
      public TaskmOdel (){

      }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public TaskmOdel(String title, String background) {
        this.title = title;
        this.background = background;
    }
}


