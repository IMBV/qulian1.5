package com.quliantrip.qulian.domain.choice.good;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 单品详情
 */
public class GoodDetailBean extends BaseJson {
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

        private OnlineEntity online;
        private int num;

        private List<BranchEntity> branch;

        public void setOnline(OnlineEntity online) {
            this.online = online;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void setBranch(List<BranchEntity> branch) {
            this.branch = branch;
        }

        public OnlineEntity getOnline() {
            return online;
        }

        public int getNum() {
            return num;
        }

        public List<BranchEntity> getBranch() {
            return branch;
        }

        public static class OnlineEntity {
            private String id;
            private String name;
            private String img;
            private String desc;
            private String purnotes;
            private String pricedesc;
            private String is_res;
            private String imgs;

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

            public void setPurnotes(String purnotes) {
                this.purnotes = purnotes;
            }

            public void setPricedesc(String pricedesc) {
                this.pricedesc = pricedesc;
            }

            public void setIs_res(String is_res) {
                this.is_res = is_res;
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

            public String getImg() {
                return img;
            }

            public String getDesc() {
                return desc;
            }

            public String getPurnotes() {
                return purnotes;
            }

            public String getPricedesc() {
                return pricedesc;
            }

            public String getIs_res() {
                return is_res;
            }

            public String getImgs() {
                return imgs;
            }
        }

        public static class BranchEntity {

            private int id;
            private NameEntity name;

            public void setId(int id) {
                this.id = id;
            }

            public void setName(NameEntity name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public NameEntity getName() {
                return name;
            }

            public static class NameEntity {
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
}
