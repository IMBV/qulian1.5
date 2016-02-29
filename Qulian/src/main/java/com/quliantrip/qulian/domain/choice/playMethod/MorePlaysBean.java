package com.quliantrip.qulian.domain.choice.playMethod;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 加载更多玩法条目
 */
public class MorePlaysBean extends BaseJson {
    private int code;
    private String msg;

    private List<PlayBean> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<PlayBean> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<PlayBean> getData() {
        return data;
    }
}
