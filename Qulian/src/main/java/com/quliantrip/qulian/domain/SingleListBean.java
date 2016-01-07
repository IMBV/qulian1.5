package com.quliantrip.qulian.domain;

/**
 * Created by Qulian5 on 2016/1/7.
 */
public class SingleListBean {
    public SingleListBean(String id, String des) {
        this.id = id;
        this.des = des;
    }

    private String id;
    private String des;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
