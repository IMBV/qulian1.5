package com.quliantrip.qulian.domain.find;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

public class VoiceSquareBean extends BaseJson {

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
        private String area_name;

        private List<ChildEntity> child;

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public void setChild(List<ChildEntity> child) {
            this.child = child;
        }

        public String getArea_name() {
            return area_name;
        }

        public List<ChildEntity> getChild() {
            return child;
        }

        public static class ChildEntity {
            private String id;
            private String scenic;
            private String escenic;
            private String imgs;

            public void setId(String id) {
                this.id = id;
            }

            public void setScenic(String scenic) {
                this.scenic = scenic;
            }

            public void setEscenic(String escenic) {
                this.escenic = escenic;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
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

            public String getImgs() {
                return imgs;
            }
        }
    }
}
