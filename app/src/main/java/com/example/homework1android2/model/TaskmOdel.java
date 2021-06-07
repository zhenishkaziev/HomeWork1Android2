package com.example.homework1android2.model;

import java.io.Serializable;

public class TaskmOdel implements Serializable {
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


