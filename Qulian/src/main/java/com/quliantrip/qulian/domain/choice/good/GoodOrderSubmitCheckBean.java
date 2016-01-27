package com.quliantrip.qulian.domain.choice.good;

import com.quliantrip.qulian.domain.BaseJson;

/**
 * Created by Qulian5 on 2016/1/27.
 */
public class GoodOrderSubmitCheckBean extends BaseJson {
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
        private String sale;
        private String num;
        private String date;

        public void setSale(String sale) {
            this.sale = sale;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSale() {
            return sale;
        }

        public String getNum() {
            return num;
        }

        public String getDate() {
            return date;
        }
    }
}
