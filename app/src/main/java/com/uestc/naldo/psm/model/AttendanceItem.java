package com.uestc.naldo.psm.model;

/**
 * Created by Naldo on 2017/5/14.
 */

public class AttendanceItem {

    private int id;

    private String date;

    private String status;

    private int trainer_id;

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
