package com.uestc.naldo.psm.model;

import java.io.Serializable;
import java.sql.Date;




public class Feedback implements Serializable{

    private Long id;

    private String title;

    private String content;

    private Date date;

    private Long ownerId;

    public Feedback(Long id, String title, String content, Date date,
            Long ownerId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.ownerId = ownerId;
    }


    public Feedback() {}

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

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

}
