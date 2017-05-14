package com.uestc.naldo.psm.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;


import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Naldo on 2017/5/13.
 */
@Entity
public class Attendance {
    @Id
    private long id;

    private String status;

    private Date date;

    private long trainerId;

    @Generated(hash = 1958540317)
    public Attendance(long id, String status, Date date, long trainerId) {
        this.id = id;
        this.status = status;
        this.date = date;
        this.trainerId = trainerId;
    }

    @Generated(hash = 812698609)
    public Attendance() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTrainerId() {
        return this.trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
    }

}
