package com.uestc.naldo.psm.model;

import java.io.Serializable;


public class TrainerItem implements Serializable{
    private int id;
    private String name;
    private String sex;
    private String position;


    public TrainerItem(int id, String name, String sex, String position){
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.position = position;
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



    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
