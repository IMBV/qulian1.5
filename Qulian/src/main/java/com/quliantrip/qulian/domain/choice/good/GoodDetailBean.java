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
            private String featured;
            private String usenstruct;
            private String buynum;
            private String sale;
            private String proce;
            private double agio;
            private String houseid;
            private boolean is_house;
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

            public void setFeatured(String featured) {
                this.featured = featured;
            }

            public void setUsenstruct(String usenstruct) {
                this.usenstruct = usenstruct;
            }

            public void setBuynum(String buynum) {
                this.buynum = buynum;
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

            public void setHouseid(String houseid) {
                this.houseid = houseid;
            }

            public void setIs_house(boolean is_house) {
                this.is_house = is_house;
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

            public String getFeatured() {
                return featured;
            }

            public String getUsenstruct() {
                return usenstruct;
            }

            public String getBuynum() {
                return buynum;
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

            public String getHouseid() {
                return houseid;
            }

            public boolean isIs_house() {
                return is_house;
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
                private String images;
                private String address;

                public void setId(String id) {
                    this.id = id;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setImages(String images) {
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

                public String getImages() {
                    return images;
                }

                public String getAddress() {
                    return address;
                }
            }
        }
    }
}
