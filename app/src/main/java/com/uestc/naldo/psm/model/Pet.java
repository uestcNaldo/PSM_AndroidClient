package com.uestc.naldo.psm.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by Naldo on 2017/5/10.
 */
@Entity
public class Pet {
    @Id
    private long id;
    @NotNull
    private String name;
    private int age;
    private String sex;
    private String species;

    private String hr_content;

    private Date start;

    private Date end;

    private long ownerId;

    private long courseId;

    @Generated(hash = 720581985)
    public Pet(long id, @NotNull String name, int age, String sex, String species,
            String hr_content, Date start, Date end, long ownerId, long courseId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.species = species;
        this.hr_content = hr_content;
        this.start = start;
        this.end = end;
        this.ownerId = ownerId;
        this.courseId = courseId;
    }

    @Generated(hash = 1478286243)
    public Pet() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
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

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getHr_content() {
        return this.hr_content;
    }

    public void setHr_content(String hr_content) {
        this.hr_content = hr_content;
    }

    public Date getStart() {
        return this.start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return this.end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

}
