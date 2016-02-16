package com.quliantrip.qulian.domain.choice.good;

import com.quliantrip.qulian.domain.BaseJson;

/**
 * 确认商品订单
 */
public class GoodOrderConfirmBean extends BaseJson {

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
            private String date;
            private String price;
            private String num;
            private String service;
            private String name;
            private String total_price;
            private String de;

            public void setDate(String date) {
                this.date = date;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public void setService(String service) {
                this.service = service;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public void setDe(String de) {
                this.de = de;
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

            public String getService() {
                return service;
            }

            public String getName() {
                return name;
            }

            public String getTotal_price() {
                return total_price;
            }

            public String getDe() {
                return de;
            }
        }
    }
}
