package com.quliantrip.qulian.domain.choice.playMethod;

import com.google.gson.annotations.SerializedName;
import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 订单提交
 */
public class PlayMethodOrderSubmitBean extends BaseJson{

    /**
     * code : 200
     * msg : 成功
     * data : [{"playitem":{"playitemsid":"21","title":"哈哈哈哈","proid":"11","name":"dddddddddddd"},"branchname":[{"id":"1","name":"弟弟顶顶顶顶顶","images":null,"address":"弟弟顶顶顶顶顶"},{"id":"2","name":"111111111111","images":null,"address":"11111111111111111"}],"attribute":[{"id":"15","package":"演出票 :大大,景点门票:儿童票"},{"id":"24","package":"演出票 :大大,景点门票:长者票"},{"id":"35","package":"演出票 :大大,景点门票:长者票"},{"id":"36","package":"演出票 :大大,景点门票:成人票"},{"id":"39","package":"演出票 :大大,景点门票:成人票"}],"attrss":[{"sale":"111","num":"34","date":1459440000,"de":"2016-04-01"}]},{"playitem":{"playitemsid":"22","title":"呵呵呵呵","proid":"11","name":"dddddddddddd"},"branchname":[{"id":"1","name":"弟弟顶顶顶顶顶","images":null,"address":"弟弟顶顶顶顶顶"},{"id":"2","name":"111111111111","images":null,"address":"11111111111111111"}],"attribute":[{"id":"15","package":"演出票 :大大,景点门票:儿童票"},{"id":"24","package":"演出票 :大大,景点门票:长者票"},{"id":"35","package":"演出票 :大大,景点门票:长者票"},{"id":"36","package":"演出票 :大大,景点门票:成人票"},{"id":"39","package":"演出票 :大大,景点门票:成人票"}],"attrss":[{"sale":"111","num":"34","date":1459440000,"de":"2016-04-01"}]},{"playitem":{"playitemsid":"23","title":"嘿嘿嘿","proid":"11","name":"dddddddddddd"},"branchname":[{"id":"1","name":"弟弟顶顶顶顶顶","images":null,"address":"弟弟顶顶顶顶顶"},{"id":"2","name":"111111111111","images":null,"address":"11111111111111111"}],"attribute":[{"id":"15","package":"演出票 :大大,景点门票:儿童票"},{"id":"24","package":"演出票 :大大,景点门票:长者票"},{"id":"35","package":"演出票 :大大,景点门票:长者票"},{"id":"36","package":"演出票 :大大,景点门票:成人票"},{"id":"39","package":"演出票 :大大,景点门票:成人票"}],"attrss":[{"sale":"111","num":"34","date":1459440000,"de":"2016-04-01"}]}]
     */

    private int code;
    private String msg;
    /**
     * playitem : {"playitemsid":"21","title":"哈哈哈哈","proid":"11","name":"dddddddddddd"}
     * branchname : [{"id":"1","name":"弟弟顶顶顶顶顶","images":null,"address":"弟弟顶顶顶顶顶"},{"id":"2","name":"111111111111","images":null,"address":"11111111111111111"}]
     * attribute : [{"id":"15","package":"演出票 :大大,景点门票:儿童票"},{"id":"24","package":"演出票 :大大,景点门票:长者票"},{"id":"35","package":"演出票 :大大,景点门票:长者票"},{"id":"36","package":"演出票 :大大,景点门票:成人票"},{"id":"39","package":"演出票 :大大,景点门票:成人票"}]
     * attrss : [{"sale":"111","num":"34","date":1459440000,"de":"2016-04-01"}]
     */

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
        /**
         * playitemsid : 21
         * title : 哈哈哈哈
         * proid : 11
         * name : dddddddddddd
         */

        private PlayitemEntity playitem;
        /**
         * id : 1
         * name : 弟弟顶顶顶顶顶
         * images : null
         * address : 弟弟顶顶顶顶顶
         */

        private List<BranchnameEntity> branchname;
        /**
         * id : 15
         * package : 演出票 :大大,景点门票:儿童票
         */

        private List<AttributeEntity> attribute;
        /**
         * sale : 111
         * num : 34
         * date : 1459440000
         * de : 2016-04-01
         */

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
            private double date;
            private String de;

            public void setSale(String sale) {
                this.sale = sale;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public void setDate(double date) {
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

            public double getDate() {
                return date;
            }

            public String getDe() {
                return de;
            }
        }
    }
}
