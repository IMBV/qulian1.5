package com.quliantrip.qulian.domain.me;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 我的收藏列表
 * private boolean ischeck = false;//表示编辑是时候
 */
public class GoodCollectListBean extends BaseJson{

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
        private List<CateEntity> cate;
        private List<HouseEntity> house;

        public void setCate(List<CateEntity> cate) {
            this.cate = cate;
        }

        public void setHouse(List<HouseEntity> house) {
            this.house = house;
        }

        public List<CateEntity> getCate() {
            return cate;
        }

        public List<HouseEntity> getHouse() {
            return house;
        }

        public static class CateEntity {
            private String id;
            private String name;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }

        public static class HouseEntity {
            private String houseid;
            private String type;
            private String is_del;
            private String id;
            private String name;
            private String meter;
            private String sale;
            private String proce;
            private double agio;
            private String imgs;
            private String chinese_name;
            private boolean ischeck = false;
            private boolean isRefresh = true;

            public String getChinese_name() {
                return chinese_name;
            }

            public void setChinese_name(String chinese_name) {
                this.chinese_name = chinese_name;
            }

            public boolean isRefresh() {
                return isRefresh;
            }

            public void setIsRefresh(boolean isRefresh) {
                this.isRefresh = isRefresh;
            }

            public boolean ischeck() {
                return ischeck;
            }

            public void setIscheck(boolean ischeck) {
                this.ischeck = ischeck;
            }

            public void setHouseid(String houseid) {
                this.houseid = houseid;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getHouseid() {
                return houseid;
            }

            public String getType() {
                return type;
            }

            public String getIs_del() {
                return is_del;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
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
}
