package com.quliantrip.qulian.domain.choice;

/**
 * Created by Qulian5 on 2016/1/28.
 */
public class PlayMethodOrderSuBmitItemBean {
    private String playid;//玩法的id
    private String sku_id;
    private String date;//订单的日期
    private String num;//购买数量
    private String service;//服务时间
    private String store;//商店的id
    private String price;//价格

    public String getPlayid() {
        return playid;
    }

    public void setPlayid(String playid) {
        this.playid = playid;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "\"playid\":\"" + playid +
                "\",\"sku_id\":\"" + sku_id+
                "\",\"date\":\"" + date+
                "\",\"num\":\"" + num  +
                "\",\"service\":\"" + service +
                "\",\"store\":\"" + store +
                "\",\"price\":\"" + price +
                "\"}";
    }
}
