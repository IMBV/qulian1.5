package com.quliantrip.qulian.domain.me;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 玩法收藏列表
 */
public class PlayCollectListBean extends BaseJson{

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
        private String products_id;
        private String user_id;
        private String type;
        private String ctime;
        private String is_del;
        private Object name;
        private boolean ischeck = false;

        public boolean ischeck() {
            return ischeck;
        }

        public void setIscheck(boolean ischeck) {
            this.ischeck = ischeck;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public String getId() {
            return id;
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

        public String getIs_del() {
            return is_del;
        }

        public Object getName() {
            return name;
        }
    }
}
