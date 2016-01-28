package com.quliantrip.qulian.domain.home;

import com.quliantrip.qulian.domain.BaseJson;

import java.io.Serializable;
import java.util.List;

/**
 * 首页搜索结果
 */
public class SecnicPlayResultBean extends BaseJson {



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

    public static class DataEntity implements Serializable{

        private List<WareEntity> ware;

        private List<RuleEntity> rule;

        private List<ScenicEntity> scenic;

        public void setWare(List<WareEntity> ware) {
            this.ware = ware;
        }

        public void setRule(List<RuleEntity> rule) {
            this.rule = rule;
        }

        public void setScenic(List<ScenicEntity> scenic) {
            this.scenic = scenic;
        }

        public List<WareEntity> getWare() {
            return ware;
        }

        public List<RuleEntity> getRule() {
            return rule;
        }

        public List<ScenicEntity> getScenic() {
            return scenic;
        }

        public static class WareEntity implements Serializable{
            private String id;
            private String name;
            private String img;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getImg() {
                return img;
            }
        }

        public static class RuleEntity implements Serializable{
            private String id;
            private String name;
            private String title;
            private String img;
            private String summary;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getTitle() {
                return title;
            }

            public String getImg() {
                return img;
            }

            public String getSummary() {
                return summary;
            }
        }

        public static class ScenicEntity implements Serializable{
            private String id;
            private String scenic;
            private String escenic;
            private String mimg;

            public void setId(String id) {
                this.id = id;
            }

            public void setScenic(String scenic) {
                this.scenic = scenic;
            }

            public void setEscenic(String escenic) {
                this.escenic = escenic;
            }

            public void setMimg(String mimg) {
                this.mimg = mimg;
            }

            public String getId() {
                return id;
            }

            public String getScenic() {
                return scenic;
            }

            public String getEscenic() {
                return escenic;
            }

            public String getMimg() {
                return mimg;
            }
        }
    }
}
