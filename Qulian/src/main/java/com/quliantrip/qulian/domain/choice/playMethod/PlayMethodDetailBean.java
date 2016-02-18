package com.quliantrip.qulian.domain.choice.playMethod;

import com.google.gson.annotations.SerializedName;
import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 玩法详情的实体类
 */
public class PlayMethodDetailBean extends BaseJson {

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

        private PlayEntity play;

        @SerializedName("package")
        private List<PackageEntity> packageX;

        public void setPlay(PlayEntity play) {
            this.play = play;
        }

        public void setPackageX(List<PackageEntity> packageX) {
            this.packageX = packageX;
        }

        public PlayEntity getPlay() {
            return play;
        }

        public List<PackageEntity> getPackageX() {
            return packageX;
        }

        public static class PlayEntity {
            private String id;
            private String name;
            private String img;
            private String desc;
            private String buydesc;
            private String sequence;
            private String min_price;
            private String userid;
            private String summary;
            private String experience;
            private String username;
            private String head_img;
            private String imgs;
            private String sale;
            private String proce;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public void setBuydesc(String buydesc) {
                this.buydesc = buydesc;
            }

            public void setSequence(String sequence) {
                this.sequence = sequence;
            }

            public void setMin_price(String min_price) {
                this.min_price = min_price;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public void setExperience(String experience) {
                this.experience = experience;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setHead_img(String head_img) {
                this.head_img = head_img;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
            }

            public void setSale(String sale) {
                this.sale = sale;
            }

            public void setProce(String proce) {
                this.proce = proce;
            }

            public String getId() {
                return id;
            }

            public Object getName() {
                return name;
            }

            public String getImg() {
                return img;
            }

            public Object getDesc() {
                return desc;
            }

            public String getBuydesc() {
                return buydesc;
            }

            public String getSequence() {
                return sequence;
            }

            public String getMin_price() {
                return min_price;
            }

            public String getUserid() {
                return userid;
            }

            public String getSummary() {
                return summary;
            }

            public Object getExperience() {
                return experience;
            }

            public String getUsername() {
                return username;
            }

            public String getHead_img() {
                return head_img;
            }

            public String getImgs() {
                return imgs;
            }

            public String getSale() {
                return sale;
            }

            public String getProce() {
                return proce;
            }
        }

        public static class PackageEntity {

            private String playitemsid;
            private String title;
            private String type;
            private String reason;
            private String class_id;
            @SerializedName("class")
            private String classX;
            private String name;
            private String merchant;
            private String is_res;
            private String sale;
            private String proce;
            private String merchantname;

            public void setPlayitemsid(String playitemsid) {
                this.playitemsid = playitemsid;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public void setClass_id(String class_id) {
                this.class_id = class_id;
            }

            public void setClassX(String classX) {
                this.classX = classX;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setMerchant(String merchant) {
                this.merchant = merchant;
            }

            public void setIs_res(String is_res) {
                this.is_res = is_res;
            }

            public void setSale(String sale) {
                this.sale = sale;
            }

            public void setProce(String proce) {
                this.proce = proce;
            }

            public void setMerchantname(String merchantname) {
                this.merchantname = merchantname;
            }

            public String getPlayitemsid() {
                return playitemsid;
            }

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }

            public String getReason() {
                return reason;
            }

            public String getClass_id() {
                return class_id;
            }

            public String getClassX() {
                return classX;
            }

            public String getName() {
                return name;
            }

            public String getMerchant() {
                return merchant;
            }

            public String getIs_res() {
                return is_res;
            }

            public String getSale() {
                return sale;
            }

            public String getProce() {
                return proce;
            }

            public String getMerchantname() {
                return merchantname;
            }
        }
    }
}
