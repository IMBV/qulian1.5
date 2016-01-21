package com.quliantrip.qulian.domain;

/**
 * Created by Qulian5 on 2016/1/20.
 */
public class HintInfoBean extends BaseJson{
    private int code;
    private String msg;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
