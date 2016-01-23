package com.quliantrip.qulian.domain.find;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 优惠券
 */
public class DiscountBean extends BaseJson{



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
        private String image;

        public void setId(String id) {
            this.id = id;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getId() {
            return id;
        }

        public String getImage() {
            return image;
        }
    }
}
