package com.quliantrip.qulian.domain.choice;

import com.quliantrip.qulian.domain.BaseJson;

/**
 * 收藏结果界面
 */
public class CollectResultBean extends BaseJson{

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
        private String products_id;
        private String user_id;
        private String type;
        private String ctime;
        private String id;

        public void setProducts_id(String products_id) {
            this.products_id = products_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProducts_id() {
            return products_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getType() {
            return type;
        }

        public String getCtime() {
            return ctime;
        }

        public String getId() {
            return id;
        }
    }
}
