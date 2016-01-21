package com.quliantrip.qulian.domain.choice;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 单品详情
 */
public class GoodDetailBean extends BaseJson{


    /**
     * code : 200
     * msg : 获取成功
     * data : {"online":{"id":"31","name":"2222222","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","desc":"<p>222222222222222222222<\/p>","purnotes":"<p>222222222222222222222222222<\/p>","pricedesc":"<p>22222222222222222222<\/p>"},"branch":[{"id":3,"name":{"id":"3","name":"11111111111111","images":"","contact":"  水水水水水水水水水水水水水水水水"}}],"num":0}
     */

    private int code;
    private String msg;
    /**
     * online : {"id":"31","name":"2222222","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","desc":"<p>222222222222222222222<\/p>","purnotes":"<p>222222222222222222222222222<\/p>","pricedesc":"<p>22222222222222222222<\/p>"}
     * branch : [{"id":3,"name":{"id":"3","name":"11111111111111","images":"","contact":"  水水水水水水水水水水水水水水水水"}}]
     * num : 0
     */

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
        /**
         * id : 31
         * name : 2222222
         * img : http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg
         * desc : <p>222222222222222222222</p>
         * purnotes : <p>222222222222222222222222222</p>
         * pricedesc : <p>22222222222222222222</p>
         */

        private OnlineEntity online;
        private int num;
        /**
         * id : 3
         * name : {"id":"3","name":"11111111111111","images":"","contact":"  水水水水水水水水水水水水水水水水"}
         */

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
        }

        public static class BranchEntity {
            private int id;
            /**
             * id : 3
             * name : 11111111111111
             * images :
             * contact :   水水水水水水水水水水水水水水水水
             */

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
                private String contact;

                public void setId(String id) {
                    this.id = id;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setImages(String images) {
                    this.images = images;
                }

                public void setContact(String contact) {
                    this.contact = contact;
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

                public String getContact() {
                    return contact;
                }
            }
        }
    }
}
