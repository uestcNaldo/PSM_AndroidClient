package com.uestc.naldo.psm.model;

import java.io.Serializable;

/**
 * Created by Naldo on 2017/5/11.
 */

public class NotificationItem implements Serializable{
    private int id;
    private String title;
    private String date;
    private String author;

    public NotificationItem(int id, String title, String date, String author){
        this.id = id;
        this.title = title;
        this.date = date;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
