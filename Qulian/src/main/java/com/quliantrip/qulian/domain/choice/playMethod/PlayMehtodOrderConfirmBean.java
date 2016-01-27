package com.quliantrip.qulian.domain.choice.playMethod;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * Created by Qulian5 on 2016/1/27.
 */
public class PlayMehtodOrderConfirmBean extends BaseJson {

    /**
     * code : 200
     * msg : 成功
     * data : [{"ordershop":{"sku_id":"15","date":"2/12/2015","price":"1111","num":"3","ctime":"1453859406","title":"哈哈哈哈","product_id":"11","name":"dddddddddddd"},"attribute":"演出票 :大大,景点门票:儿童票"},{"ordershop":{"sku_id":"15","date":"2/12/2015","price":"1111","num":"3","ctime":"1453859406","title":"呵呵呵呵","product_id":"11","name":"dddddddddddd"},"attribute":"演出票 :大大,景点门票:儿童票"}]
     */

    private int code;
    private String msg;
    /**
     * ordershop : {"sku_id":"15","date":"2/12/2015","price":"1111","num":"3","ctime":"1453859406","title":"哈哈哈哈","product_id":"11","name":"dddddddddddd"}
     * attribute : 演出票 :大大,景点门票:儿童票
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
         * sku_id : 15
         * date : 2/12/2015
         * price : 1111
         * num : 3
         * ctime : 1453859406
         * title : 哈哈哈哈
         * product_id : 11
         * name : dddddddddddd
         */

        private OrdershopEntity ordershop;
        private String attribute;

        public void setOrdershop(OrdershopEntity ordershop) {
            this.ordershop = ordershop;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public OrdershopEntity getOrdershop() {
            return ordershop;
        }

        public String getAttribute() {
            return attribute;
        }

        public static class OrdershopEntity {
            private String sku_id;
            private String date;
            private String price;
            private String num;
            private String ctime;
            private String title;
            private String product_id;
            private String name;

            public void setSku_id(String sku_id) {
                this.sku_id = sku_id;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSku_id() {
                return sku_id;
            }

            public String getDate() {
                return date;
            }

            public String getPrice() {
                return price;
            }

            public String getNum() {
                return num;
            }

            public String getCtime() {
                return ctime;
            }

            public String getTitle() {
                return title;
            }

            public String getProduct_id() {
                return product_id;
            }

            public String getName() {
                return name;
            }
        }
    }
}
