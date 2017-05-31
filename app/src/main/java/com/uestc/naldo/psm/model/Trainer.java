package com.uestc.naldo.psm.model;

import android.content.Intent;

import java.io.Serializable;



public class Trainer implements Serializable{

    private Long id;

    private String username;

    private String password;

    private String name;

    private String sex;

    private Integer age;

    private String email;

    private String position;

    private String maxim;

    private String intro;


    public Trainer(Long id, String username, String password, String name,
                   Integer age, String sex, String email, String position, String maxim,
                   String intro) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.position = position;
        this.maxim = maxim;
        this.intro = intro;
    }

    public Trainer() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMaxim() {
        return this.maxim;
    }

    public void setMaxim(String maxim) {
        this.maxim = maxim;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }


}
