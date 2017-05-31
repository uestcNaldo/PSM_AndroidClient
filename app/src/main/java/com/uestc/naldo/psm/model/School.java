package com.uestc.naldo.psm.model;


public class School {

    private Long id;


    private String name;

    private String contact;

    private String intro;

    private String phone;

    private String address;

    public School(Long id, String name, String contact, String intro,
            String phone, String address) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.intro = intro;
        this.phone = phone;
        this.address = address;
    }


    public School() {}

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
