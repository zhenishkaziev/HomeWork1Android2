package com.example.homework1android2;

import java.io.Serializable;

public class TaskmOdel implements Serializable {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String title, description;

    public TaskmOdel(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
