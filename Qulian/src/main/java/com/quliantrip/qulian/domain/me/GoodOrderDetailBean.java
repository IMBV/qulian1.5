package com.quliantrip.qulian.domain.me;

import com.google.gson.annotations.SerializedName;
import com.quliantrip.qulian.domain.BaseJson;

/**
 * 我的订单详情页面
 */
public class GoodOrderDetailBean extends BaseJson{
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
        private String id;
        private String order_sn;
        private String product_id;
        private String sku_id;
        private String date;
        private String num;
        private String price;
        private String service;
        private String total_price;
        private String pay_time;
        private String isuse_time;
        private String name;
        private String img;
        private String lxname;
        private String tel;
        private String address;
        private String isorder;
        @SerializedName("package")
        private String packageX;
        private String imgs;

        public String getIsorder() {
            return isorder;
        }

        public void setIsorder(String isorder) {
            this.isorder = isorder;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public void setSku_id(String sku_id) {
            this.sku_id = sku_id;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setService(String service) {
            this.service = service;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public void setIsuse_time(String isuse_time) {
            this.isuse_time = isuse_time;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setLxname(String lxname) {
            this.lxname = lxname;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getProduct_id() {
            return product_id;
        }

        public String getSku_id() {
            return sku_id;
        }

        public String getDate() {
            return date;
        }

        public String getNum() {
            return num;
        }

        public String getPrice() {
            return price;
        }

        public String getService() {
            return service;
        }

        public String getTotal_price() {
            return total_price;
        }

        public String getPay_time() {
            return pay_time;
        }

        public String getIsuse_time() {
            return isuse_time;
        }

        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }

        public String getLxname() {
            return lxname;
        }

        public String getTel() {
            return tel;
        }

        public String getAddress() {
            return address;
        }

        public String getPackageX() {
            return packageX;
        }

        public String getImgs() {
            return imgs;
        }
    }
}
