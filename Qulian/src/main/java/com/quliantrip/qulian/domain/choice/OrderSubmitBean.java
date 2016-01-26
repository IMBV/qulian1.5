package com.quliantrip.qulian.domain.choice;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 提交订单
 */
public class OrderSubmitBean extends BaseJson {

    private int code;
    private DataEntity data;
    private String msg;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public static class DataEntity {
        private OnlineEntity online;
        private List<AttributeEntity> attribute;
        private List<BranchnameEntity> branchname;

        public void setOnline(OnlineEntity online) {
            this.online = online;
        }

        public void setAttribute(List<AttributeEntity> attribute) {
            this.attribute = attribute;
        }

        public void setBranchname(List<BranchnameEntity> branchname) {
            this.branchname = branchname;
        }

        public OnlineEntity getOnline() {
            return online;
        }

        public List<AttributeEntity> getAttribute() {
            return attribute;
        }

        public List<BranchnameEntity> getBranchname() {
            return branchname;
        }

        public static class OnlineEntity {
            private String id;
            private String img;
            private String name;

            public void setId(String id) {
                this.id = id;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public String getImg() {
                return img;
            }

            public String getName() {
                return name;
            }
        }

        public static class AttributeEntity {
            private String attrid;
            private String name;
            /**
             * attrvalid : 44
             * value : 股份的公司股份的三国杀
             */

            private List<ValsEntity> vals;

            public void setAttrid(String attrid) {
                this.attrid = attrid;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setVals(List<ValsEntity> vals) {
                this.vals = vals;
            }

            public String getAttrid() {
                return attrid;
            }

            public String getName() {
                return name;
            }

            public List<ValsEntity> getVals() {
                return vals;
            }

            public static class ValsEntity {
                private String attrvalid;
                private String value;

                public void setAttrvalid(String attrvalid) {
                    this.attrvalid = attrvalid;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getAttrvalid() {
                    return attrvalid;
                }

                public String getValue() {
                    return value;
                }
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
    }
}
