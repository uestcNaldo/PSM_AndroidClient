package com.uestc.naldo.psm.model;

/**
 * Created by Naldo on 2017/5/11.
 */

public class Trainer {
    private int id;
    private String username;
    private String password;
    private String name;
    private int age;
    private String sex;

    private String email;
    private String position;
    private String maxim;
    private String intro;

    public Trainer(int id,  String username, String password, String name, int age, String sex){
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMaxim() {
        return maxim;
    }

    public void setMaxim(String maxim) {
        this.maxim = maxim;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
