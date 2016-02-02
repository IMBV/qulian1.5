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
        private String title;
        @SerializedName("package")
        private String packageX;

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

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
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

        public String getTitle() {
            return title;
        }

        public String getPackageX() {
            return packageX;
        }
    }
}
