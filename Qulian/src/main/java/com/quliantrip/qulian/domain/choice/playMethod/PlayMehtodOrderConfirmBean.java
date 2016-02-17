package com.quliantrip.qulian.domain.choice.playMethod;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 玩法确认订单页
 */
public class PlayMehtodOrderConfirmBean extends BaseJson {

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
        private String total_price;
        private List<PlayorderEntity> playorder;

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public void setPlayorder(List<PlayorderEntity> playorder) {
            this.playorder = playorder;
        }

        public String getTotal_price() {
            return total_price;
        }

        public List<PlayorderEntity> getPlayorder() {
            return playorder;
        }

        public static class PlayorderEntity {
            /**
             * sku_id : 142
             * date : 1455638400
             * price : 169.0000
             * num : 2
             * ctime : 1455604743
             * title : 海贼王海贼王海贼王海贼王海贼王
             * product_id : 33
             * name : 东京塔海贼王主题乐园电子票
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
}
