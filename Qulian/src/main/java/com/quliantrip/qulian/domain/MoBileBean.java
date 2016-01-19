package com.quliantrip.qulian.domain;

/**
 * Created by Qulian5 on 2016/1/14.
 * 手机号查重
 */
public class MoBileBean extends BaseJson {

    private int code;
    private String msg;
    private String data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getData() {
        return data;
    }
}
