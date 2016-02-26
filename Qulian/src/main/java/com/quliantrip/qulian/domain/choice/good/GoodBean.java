package com.quliantrip.qulian.domain.choice.good;

/**
 *  商品展示页
 */
public class GoodBean {
    private String id;
    private String name;
    private String img;
    private String meter;
    private String chinese_name;
    private String sale;
    private String proce;
    private boolean is_house;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public void setChinese_name(String chinese_name) {
        this.chinese_name = chinese_name;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public void setProce(String proce) {
        this.proce = proce;
    }

    public void setIs_house(boolean is_house) {
        this.is_house = is_house;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getMeter() {
        return meter;
    }

    public String getChinese_name() {
        return chinese_name;
    }

    public String getSale() {
        return sale;
    }

    public String getProce() {
        return proce;
    }

    public boolean isIs_house() {
        return is_house;
    }
}
