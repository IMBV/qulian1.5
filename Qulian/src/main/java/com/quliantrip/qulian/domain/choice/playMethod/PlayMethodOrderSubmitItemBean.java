package com.quliantrip.qulian.domain.choice.playMethod;

/**
 * 玩法订单单条选中的数据集合
 */
public class PlayMethodOrderSubmitItemBean {
    public PlayMethodOrderSubmitItemBean(String playid, String sku_id, String date, String dateString, String num, String service, String store, String price) {
        this.playid = playid;
        this.sku_id = sku_id;
        this.date = date;
        this.dateString = dateString;
        this.num = num;
        this.service = service;
        this.store = store;
        this.price = price;
    }

    private String playid;//玩法条目的id
    private String sku_id;//属性选着的id
    private String date;//订单的日期_时间搓
    private String dateString;//订单日期时间字符串
    private String num;//购买数量
    private String service;//服务时间
    private String store;//商店的id
    private String price;//价格


    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

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
