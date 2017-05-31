package com.uestc.naldo.psm.model;


import java.io.Serializable;

public class Course implements Serializable{

    private Long id;

    private String name;
    private String inst;
    private String condition;
    private String duration;
    private String content;
    private String prise;
    private String note;


    public Course(Long id, String name, String condition, String duration,
            String content, String prise, String note) {
        this.id = id;
        this.name = name;
        this.condition = condition;
        this.duration = duration;
        this.content = content;
        this.prise = prise;
        this.note = note;
    }


    public Course() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrise() {
        return this.prise;
    }

    public void setPrise(String prise) {
        this.prise = prise;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public String getInst() {
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst;
    }
}
