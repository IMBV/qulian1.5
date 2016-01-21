package com.quliantrip.qulian.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 常用联系人
 */
public class LinkManBean extends BaseJson {

    private int code;
    private String msg;

    private List<LinkMan> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<LinkMan> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<LinkMan> getData() {
        return data;
    }

    public static class LinkMan implements Serializable {
        private String id;
        private String name;
        private String surname;
        private String pyname;
        private String birth_date;
        private String sex;
        private String paper_type;
        private String paper_number;
        private String address;
        private String is_effect;
        private String describe;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public void setPyname(String pyname) {
            this.pyname = pyname;
        }

        public void setBirth_date(String birth_date) {
            this.birth_date = birth_date;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setPaper_type(String paper_type) {
            this.paper_type = paper_type;
        }

        public void setPaper_number(String paper_number) {
            this.paper_number = paper_number;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setIs_effect(String is_effect) {
            this.is_effect = is_effect;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public String getPyname() {
            return pyname;
        }

        public String getBirth_date() {
            return birth_date;
        }

        public String getSex() {
            return sex;
        }

        public String getPaper_type() {
            return paper_type;
        }

        public String getPaper_number() {
            return paper_number;
        }

        public String getAddress() {
            return address;
        }

        public String getIs_effect() {
            return is_effect;
        }

        public String getDescribe() {
            return describe;
        }
    }
}
