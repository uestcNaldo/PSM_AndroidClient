package com.uestc.naldo.psm.json;

import com.uestc.naldo.psm.model.Pet;

import java.util.List;

public class PetListResult {

    private String opt;

    private Integer code;

    private List<Pet> petList;

    public PetListResult() {}


    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

}
