package com.quliantrip.qulian.domain.common;

import com.quliantrip.qulian.domain.BaseJson;

/**
 * 只有提示的类
 */
public class HintInfoBean extends BaseJson {
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
