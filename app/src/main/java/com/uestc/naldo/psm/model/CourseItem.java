package com.uestc.naldo.psm.model;

import java.io.Serializable;

/**
 * Created by Naldo on 2017/5/12.
 */

public class CourseItem implements Serializable{

    private int id;
    private String name;
    private String duration;

    public CourseItem(int id, String name, String duration){
        this.id = id;
        this.name = name;
        this.duration = duration;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
