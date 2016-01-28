package com.quliantrip.qulian.domain.choice.playMethod;

import com.google.gson.annotations.SerializedName;
import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 订单提交
 */
public class PlayMethodOrderSubmitBean extends BaseJson{

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
        private PlayitemEntity playitem;
        private List<BranchnameEntity> branchname;

        private List<AttributeEntity> attribute;

        private List<AttrssEntity> attrss;

        public void setPlayitem(PlayitemEntity playitem) {
            this.playitem = playitem;
        }

        public void setBranchname(List<BranchnameEntity> branchname) {
            this.branchname = branchname;
        }

        public void setAttribute(List<AttributeEntity> attribute) {
            this.attribute = attribute;
        }

        public void setAttrss(List<AttrssEntity> attrss) {
            this.attrss = attrss;
        }

        public PlayitemEntity getPlayitem() {
            return playitem;
        }

        public List<BranchnameEntity> getBranchname() {
            return branchname;
        }

        public List<AttributeEntity> getAttribute() {
            return attribute;
        }

        public List<AttrssEntity> getAttrss() {
            return attrss;
        }

        public static class PlayitemEntity {
            private String playitemsid;
            private String title;
            private String proid;
            private String name;

            public void setPlayitemsid(String playitemsid) {
                this.playitemsid = playitemsid;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setProid(String proid) {
                this.proid = proid;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPlayitemsid() {
                return playitemsid;
            }

            public String getTitle() {
                return title;
            }

            public String getProid() {
                return proid;
            }

            public String getName() {
                return name;
            }
        }

        public static class BranchnameEntity {
            private String id;
            private String name;
            private Object images;
            private String address;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setImages(Object images) {
                this.images = images;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public Object getImages() {
                return images;
            }

            public String getAddress() {
                return address;
            }
        }

        public static class AttributeEntity {
            private String id;
            @SerializedName("package")
            private String packageX;

            public void setId(String id) {
                this.id = id;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getId() {
                return id;
            }

            public String getPackageX() {
                return packageX;
            }
        }

        public static class AttrssEntity {
            private String sale;
            private String num;
            private String date;
            private String de;

            public void setSale(String sale) {
                this.sale = sale;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setDe(String de) {
                this.de = de;
            }

            public String getSale() {
                return sale;
            }

            public String getNum() {
                return num;
            }

            public String getDate() {
                return date;
            }

            public String getDe() {
                return de;
            }
        }
    }
}
