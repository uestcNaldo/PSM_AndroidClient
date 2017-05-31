package com.uestc.naldo.psm.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Naldo on 2017/5/21.
 */

public class OperationResult {
    @SerializedName("opt")
    private String opt;
    @SerializedName("code")
    private Integer code;
    @SerializedName("message")
    private String message;

    public OperationResult(){}

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
