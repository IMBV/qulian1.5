package com.quliantrip.qulian.domain.me;

import com.google.gson.annotations.SerializedName;
import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 我的玩法订单
 */
public class PlayMethodOrderBean extends BaseJson{

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
        private String order_sn;
        private String name;

        private List<PalyEntity> paly;

        public void setId(String id) {
            this.id = id;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPaly(List<PalyEntity> paly) {
            this.paly = paly;
        }

        public String getId() {
            return id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public String getName() {
            return name;
        }

        public List<PalyEntity> getPaly() {
            return paly;
        }

        public static class PalyEntity {

            private String title;
            private String type;
            @SerializedName("class")
            private String classX;
            private String product_id;
            private String id;
            private String is_use;
            private String is_pay;
            private String order_status;
            private String icon_img;
            private String isorder;


            public String getIcon_img() {
                return icon_img;
            }

            public void setIcon_img(String icon_img) {
                this.icon_img = icon_img;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setClassX(String classX) {
                this.classX = classX;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setIs_use(String is_use) {
                this.is_use = is_use;
            }

            public void setIs_pay(String is_pay) {
                this.is_pay = is_pay;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public void setIsorder(String isorder) {
                this.isorder = isorder;
            }

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }

            public String getClassX() {
                return classX;
            }

            public String getProduct_id() {
                return product_id;
            }

            public String getId() {
                return id;
            }

            public String getIs_use() {
                return is_use;
            }

            public String getIs_pay() {
                return is_pay;
            }

            public String getOrder_status() {
                return order_status;
            }

            public String getIsorder() {
                return isorder;
            }
        }
    }
}
