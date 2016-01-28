package com.quliantrip.qulian.domain.common;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

//切换城市的借口
public class CityListBean extends BaseJson {

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
        private String chinese_name;
        private String english_name;
        private String id;
        private String img_url;
        private String pid;

        private List<ChildEntity> child;

        public void setChinese_name(String chinese_name) {
            this.chinese_name = chinese_name;
        }

        public void setEnglish_name(String english_name) {
            this.english_name = english_name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public void setChild(List<ChildEntity> child) {
            this.child = child;
        }

        public String getChinese_name() {
            return chinese_name;
        }

        public String getEnglish_name() {
            return english_name;
        }

        public String getId() {
            return id;
        }

        public String getImg_url() {
            return img_url;
        }

        public String getPid() {
            return pid;
        }

        public List<ChildEntity> getChild() {
            return child;
        }

        public static class ChildEntity {
            private String chinese_name;
            private String english_name;
            private String id;
            private String img_url;
            private String pid;
            private List<?> child;

            public void setChinese_name(String chinese_name) {
                this.chinese_name = chinese_name;
            }

            public void setEnglish_name(String english_name) {
                this.english_name = english_name;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public void setChild(List<?> child) {
                this.child = child;
            }

            public String getChinese_name() {
                return chinese_name;
            }

            public String getEnglish_name() {
                return english_name;
            }

            public String getId() {
                return id;
            }

            public String getImg_url() {
                return img_url;
            }

            public String getPid() {
                return pid;
            }

            public List<?> getChild() {
                return child;
            }
        }
    }
}
