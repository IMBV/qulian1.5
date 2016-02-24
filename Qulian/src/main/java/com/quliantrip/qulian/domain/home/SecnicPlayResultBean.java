package com.quliantrip.qulian.domain.home;

import com.quliantrip.qulian.domain.BaseJson;

import java.io.Serializable;
import java.util.List;

/**
 * 首页搜索结果
 */
public class SecnicPlayResultBean extends BaseJson {



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

    public static class DataEntity implements Serializable{

        private List<WareEntity> ware;

        private List<RuleEntity> rule;

        private List<ScenicEntity> scenic;

        public void setWare(List<WareEntity> ware) {
            this.ware = ware;
        }

        public void setRule(List<RuleEntity> rule) {
            this.rule = rule;
        }

        public void setScenic(List<ScenicEntity> scenic) {
            this.scenic = scenic;
        }

        public List<WareEntity> getWare() {
            return ware;
        }

        public List<RuleEntity> getRule() {
            return rule;
        }

        public List<ScenicEntity> getScenic() {
            return scenic;
        }

        public static class WareEntity implements Serializable{

            private String id;
            private String name;
            private String img;
            private String xpoint;
            private String ypoint;
            private String city;
            private String sale;
            private String proce;
            private double agio;
            private String imgs;
            private String meter;
            private String chinese_name;

            public void setId(String id) {
                this.id = id;
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

            public void setMeter(String meter) {
                this.meter = meter;
            }

            public void setChinese_name(String chinese_name) {
                this.chinese_name = chinese_name;
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

            public String getXpoint() {
                return xpoint;
            }

            public String getYpoint() {
                return ypoint;
            }

            public String getCity() {
                return city;
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

            public String getMeter() {
                return meter;
            }

            public String getChinese_name() {
                return chinese_name;
            }
        }

        public static class RuleEntity implements Serializable{
            private String id;
            private String name;
            private String title;
            private String img;
            private String summary;
            private String min_price;
            private String buynum;
            private String sequence;
            private String username;
            private String head_img;
            private String region;
            private String sale;
            private String proce;
            private String imgs;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public void setMin_price(String min_price) {
                this.min_price = min_price;
            }

            public void setBuynum(String buynum) {
                this.buynum = buynum;
            }

            public void setSequence(String sequence) {
                this.sequence = sequence;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setHead_img(String head_img) {
                this.head_img = head_img;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public void setSale(String sale) {
                this.sale = sale;
            }

            public void setProce(String proce) {
                this.proce = proce;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getTitle() {
                return title;
            }

            public String getImg() {
                return img;
            }

            public String getSummary() {
                return summary;
            }

            public String getMin_price() {
                return min_price;
            }

            public String getBuynum() {
                return buynum;
            }

            public String getSequence() {
                return sequence;
            }

            public String getUsername() {
                return username;
            }

            public String getHead_img() {
                return head_img;
            }

            public String getRegion() {
                return region;
            }

            public String getSale() {
                return sale;
            }

            public String getProce() {
                return proce;
            }

            public String getImgs() {
                return imgs;
            }
        }

        public static class ScenicEntity implements Serializable{
            private String id;
            private String scenic;
            private String escenic;
            private String mimg;

            public void setId(String id) {
                this.id = id;
            }

            public void setScenic(String scenic) {
                this.scenic = scenic;
            }

            public void setEscenic(String escenic) {
                this.escenic = escenic;
            }

            public void setMimg(String mimg) {
                this.mimg = mimg;
            }

            public String getId() {
                return id;
            }

            public String getScenic() {
                return scenic;
            }

            public String getEscenic() {
                return escenic;
            }

            public String getMimg() {
                return mimg;
            }
        }
    }
}
