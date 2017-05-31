package com.uestc.naldo.psm.model;

import java.sql.Date;

public class Attendance {

    private Long id;

    private String status;

    private Date date;

    private Long trainerId;

    public Attendance(Long id, String status, Date date, Long trainerId) {
        this.id = id;
        this.status = status;
        this.date = date;
        this.trainerId = trainerId;
    }

    public Attendance() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    public Long getTrainerId() {
        return this.trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

}
