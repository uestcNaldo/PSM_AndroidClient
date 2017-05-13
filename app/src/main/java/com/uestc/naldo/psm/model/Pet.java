package com.uestc.naldo.psm.model;

/**
 * Created by Naldo on 2017/5/10.
 */

public class Pet {
    private int id;
    private String name;
    private int age;
    private String sex;
    private String species;

    public Pet(int id, String name, int age, String sex, String species){
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.species = species;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
