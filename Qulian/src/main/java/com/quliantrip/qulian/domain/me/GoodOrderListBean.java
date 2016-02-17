package com.quliantrip.qulian.domain.me;

import com.google.gson.annotations.SerializedName;
import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 我的单品的订单
 */
public class GoodOrderListBean extends BaseJson{

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

        /**
         * id : 73
         * order_sn : 56a97a89943631.61237090
         * pay_status : 1
         * total_price : 11111.0000
         * order_status : 0
         * is_use : 1
         * after_sale : 1
         * sku_id : 15
         * num : 3
         * price : 1111.0000
         * name : null
         * img : null
         * package : 景点门票:儿童票
         * imgs :
         */

        private String id;
        private String order_sn;
        private String pay_status;
        private String total_price;
        private String order_status;
        private String is_use;
        private String after_sale;
        private String sku_id;
        private String num;
        private String price;
        private String name;
        private String img;
        @SerializedName("package")
        private String packageX;
        private String imgs;

        public void setId(String id) {
            this.id = id;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public void setIs_use(String is_use) {
            this.is_use = is_use;
        }

        public void setAfter_sale(String after_sale) {
            this.after_sale = after_sale;
        }

        public void setSku_id(String sku_id) {
            this.sku_id = sku_id;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getId() {
            return id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public String getPay_status() {
            return pay_status;
        }

        public String getTotal_price() {
            return total_price;
        }

        public String getOrder_status() {
            return order_status;
        }

        public String getIs_use() {
            return is_use;
        }

        public String getAfter_sale() {
            return after_sale;
        }

        public String getSku_id() {
            return sku_id;
        }

        public String getNum() {
            return num;
        }

        public String getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }

        public String getPackageX() {
            return packageX;
        }

        public String getImgs() {
            return imgs;
        }
    }
}
