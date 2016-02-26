package com.quliantrip.qulian.domain.choice.good;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 加载更多数据对象
 */
public class MoreGoodsBean extends BaseJson {
    private int code;
    private String msg;

    private List<GoodBean> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<GoodBean> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<GoodBean> getData() {
        return data;
    }
}
