package com.quliantrip.qulian.domain;

/**
 * Created by Qulian5 on 2016/1/14.
 * 手机号查重
 */
public class MoBileBean extends BaseJson {
    private String code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
