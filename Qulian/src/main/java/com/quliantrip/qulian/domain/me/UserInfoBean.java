package com.quliantrip.qulian.domain.me;

import com.quliantrip.qulian.domain.BaseJson;

/**
 * 用户登录信息
 */
public class UserInfoBean extends BaseJson {

    private int code;
    private String msg;
    private LoginDataBean data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(LoginDataBean data) {
        this.data = data;
    }

    public LoginDataBean getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
