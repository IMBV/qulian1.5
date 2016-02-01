package com.quliantrip.qulian.domain.me;

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
        private Object name;

        private List<PalyEntity> paly;

        public void setId(String id) {
            this.id = id;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public void setName(Object name) {
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

        public Object getName() {
            return name;
        }

        public List<PalyEntity> getPaly() {
            return paly;
        }

        public static class PalyEntity {
            private String title;
            private String type;
            private String is_use;

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setIs_use(String is_use) {
                this.is_use = is_use;
            }

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }

            public String getIs_use() {
                return is_use;
            }
        }
    }
}
