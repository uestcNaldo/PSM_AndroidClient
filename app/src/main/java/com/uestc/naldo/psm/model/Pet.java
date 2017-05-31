package com.uestc.naldo.psm.model;

import java.io.Serializable;
import java.sql.Date;


public class Pet implements Serializable{

    private Long id;

    private String name;
    private Integer age;
    private String sex;
    private String species;

    private String hrStatus;

    private Date start;

    private Date end;

    private Long oid;

    private Long cid;


    public Pet(Long id, String name, Integer age, String sex, String species,
               String hrStatus, Date start, Date end, long ownerId, Long courseId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.species = species;
        this.hrStatus = hrStatus;
        this.start = start;
        this.end = end;
        this.oid = ownerId;
        this.cid = courseId;
    }


    public Pet() {}

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

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getHrStatus() {
        return this.hrStatus;
    }

    public void setHrStatus(String hrStatus) {
        this.hrStatus = hrStatus;
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

    public Long getOid() {
        return this.oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public Long getCid() {
        return this.cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

}
