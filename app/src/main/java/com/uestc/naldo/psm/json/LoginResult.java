package com.uestc.naldo.psm.json;

/**
 * Created by Naldo on 2017/5/22.
 */

public class LoginResult {

    private String opt;

    private Integer code;

    private Object user;

    public LoginResult(){}

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

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

}
