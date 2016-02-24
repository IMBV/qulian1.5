package com.quliantrip.qulian.domain.me;

import com.google.gson.annotations.SerializedName;
import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * Created by Qulian5 on 2016/2/1.
 */
public class PlayMethodOrderDetailBean extends BaseJson{

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
        private String id;
        private String order_code;
        private String sku_id;
        private String date;
        private String num;
        private String price;
        private String service;
        private String is_use;
        private String is_pay;
        private String title;
        @SerializedName("class")
        private String classX;
        private String total_price;
        private String pay_time;
        private String isuse_time;
        private String order_status;
        private String lxname;
        private String tel;
        private String address;
        @SerializedName("package")
        private String packageX;
        private String isorder;

        public void setId(String id) {
            this.id = id;
        }

        public void setOrder_code(String order_code) {
            this.order_code = order_code;
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

        public void setIs_use(String is_use) {
            this.is_use = is_use;
        }

        public void setIs_pay(String is_pay) {
            this.is_pay = is_pay;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setClassX(String classX) {
            this.classX = classX;
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

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
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

        public void setIsorder(String isorder) {
            this.isorder = isorder;
        }

        public String getId() {
            return id;
        }

        public String getOrder_code() {
            return order_code;
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

        public String getIs_use() {
            return is_use;
        }

        public String getIs_pay() {
            return is_pay;
        }

        public String getTitle() {
            return title;
        }

        public String getClassX() {
            return classX;
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

        public String getOrder_status() {
            return order_status;
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

        public String getIsorder() {
            return isorder;
        }
    }
}
