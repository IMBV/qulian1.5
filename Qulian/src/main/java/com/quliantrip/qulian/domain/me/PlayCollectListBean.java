package com.quliantrip.qulian.domain.me;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 玩法收藏列表
 */
public class PlayCollectListBean extends BaseJson{
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
        private String houseid;
        private String id;
        private String type;
        private String is_del;
        private String name;
        private String title;
        private String img;
        private String summary;
        private String min_price;
        private String userid;
        private String sequence;
        private String buynum;
        private String username;
        private String head_img;
        private String region;
        private String sale;
        private String proce;
        private String imgs;
        private boolean ischeck = false;
        private boolean isRefresh =  true;

        public boolean isRefresh() {
            return isRefresh;
        }

        public void setIsRefresh(boolean isRefresh) {
            this.isRefresh = isRefresh;
        }

        public String getHouseid() {
            return houseid;
        }

        public void setHouseid(String houseid) {
            this.houseid = houseid;
        }

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

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setSequence(String sequence) {
            this.sequence = sequence;
        }

        public void setBuynum(String buynum) {
            this.buynum = buynum;
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

        public String getType() {
            return type;
        }

        public String getIs_del() {
            return is_del;
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

        public String getUserid() {
            return userid;
        }

        public String getSequence() {
            return sequence;
        }

        public String getBuynum() {
            return buynum;
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
}
