package com.quliantrip.qulian.domain.me;

import com.quliantrip.qulian.domain.BaseJson;

/**
 * 手机找回密码短息验证后的操作
 */
public class SearchBackPhoneBean extends BaseJson{
    private int code;
    private String msg;
    private DataEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private String reset_key;

        public void setReset_key(String reset_key) {
            this.reset_key = reset_key;
        }

        public String getReset_key() {
            return reset_key;
        }
    }
}
