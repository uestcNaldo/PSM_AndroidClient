package com.uestc.naldo.psm.model;


import java.io.Serializable;
import java.sql.Date;

public class Notification implements Serializable{

    private Long id;
    private String title;
    private String content;

    private Date date;

    private Long aid;


    public Notification(Long id, String title, String content, Date date,
            Long aid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.aid = aid;
    }


    public Notification() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(String datetime) {
        this.date = date;
    }

    public Long getAid() {
        return this.aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

}
