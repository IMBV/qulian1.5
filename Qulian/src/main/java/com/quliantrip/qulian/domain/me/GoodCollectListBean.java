package com.quliantrip.qulian.domain.me;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 我的收藏列表
 */
public class GoodCollectListBean extends BaseJson{

    private int code;
    private String msg;

    private List<DataEntity> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String id;
        private String type;
        private String is_del;
        private String name;
        private String img;
        private String xpoint;
        private String ypoint;
        private String city;
        private String meter;
        private String sale;
        private String proce;
        private double agio;
        private String imgs;
        private boolean ischeck = false;

        public boolean ischeck() {
            return ischeck;
        }

        public void setIscheck(boolean ischeck) {
            this.ischeck = ischeck;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setXpoint(String xpoint) {
            this.xpoint = xpoint;
        }

        public void setYpoint(String ypoint) {
            this.ypoint = ypoint;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setMeter(String meter) {
            this.meter = meter;
        }

        public void setSale(String sale) {
            this.sale = sale;
        }

        public void setProce(String proce) {
            this.proce = proce;
        }

        public void setAgio(double agio) {
            this.agio = agio;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public String getIs_del() {
            return is_del;
        }

        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }

        public String getXpoint() {
            return xpoint;
        }

        public String getYpoint() {
            return ypoint;
        }

        public String getCity() {
            return city;
        }

        public String getMeter() {
            return meter;
        }

        public String getSale() {
            return sale;
        }

        public String getProce() {
            return proce;
        }

        public double getAgio() {
            return agio;
        }

        public String getImgs() {
            return imgs;
        }
    }
}
