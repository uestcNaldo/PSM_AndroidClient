package com.uestc.naldo.psm.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by Naldo on 2017/5/14.
 */
@Entity
public class Feedback {
    @Id
    private long id;

    private String title;

    private String content;

    private Date date;

    private long ownerId;

    @Generated(hash = 442274122)
    public Feedback(long id, String title, String content, Date date,
            long ownerId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.ownerId = ownerId;
    }

    @Generated(hash = 802671868)
    public Feedback() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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

    public long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

}
