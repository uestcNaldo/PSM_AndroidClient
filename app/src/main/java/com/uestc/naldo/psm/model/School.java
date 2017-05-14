package com.uestc.naldo.psm.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Naldo on 2017/5/14.
 */
@Entity
public class School {
    @Id
    private long id;

    @NotNull
    private String name;

    private String contact;

    private String intro;

    private String phone;

    private String address;

    @Generated(hash = 1287824807)
    public School(long id, @NotNull String name, String contact, String intro,
            String phone, String address) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.intro = intro;
        this.phone = phone;
        this.address = address;
    }

    @Generated(hash = 1579966795)
    public School() {
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

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
