package com.quliantrip.qulian.domain.choice.playMethod;

import com.google.gson.annotations.SerializedName;
import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 玩法详情的实体类
 */
public class PlayMethodDetailBean extends BaseJson{

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

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getImg() {
                return img;
            }

            public String getDesc() {
                return desc;
            }

            public String getBuydesc() {
                return buydesc;
            }

            public String getSequence() {
                return sequence;
            }
        }

        public static class PackageEntity {
            private String playitemsid;
            private String title;
            private String type;
            private String reason;
            private String class_id;
            private String name;
            private String merchant;
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

            public void setName(String name) {
                this.name = name;
            }

            public void setMerchant(String merchant) {
                this.merchant = merchant;
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

            public String getName() {
                return name;
            }

            public String getMerchant() {
                return merchant;
            }

            public String getMerchantname() {
                return merchantname;
            }
        }
    }
}
