package com.quliantrip.qulian.domain.find;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 景点详情的bean
 */
public class SpotDetailBean extends BaseJson {

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
        private AttractionEntity attraction;
        private List<VoicInfoEntity> voicInfo;
        private List<NearPorEntity> nearPor;

        public void setAttraction(AttractionEntity attraction) {
            this.attraction = attraction;
        }

        public void setVoicInfo(List<VoicInfoEntity> voicInfo) {
            this.voicInfo = voicInfo;
        }

        public void setNearPor(List<NearPorEntity> nearPor) {
            this.nearPor = nearPor;
        }

        public AttractionEntity getAttraction() {
            return attraction;
        }

        public List<VoicInfoEntity> getVoicInfo() {
            return voicInfo;
        }

        public List<NearPorEntity> getNearPor() {
            return nearPor;
        }

        public static class AttractionEntity {
            private String id;
            private String scenic;
            private String mimg;
            private String desc;
            private String voice_id;

            public void setId(String id) {
                this.id = id;
            }

            public void setScenic(String scenic) {
                this.scenic = scenic;
            }

            public void setMimg(String mimg) {
                this.mimg = mimg;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public void setVoice_id(String voice_id) {
                this.voice_id = voice_id;
            }

            public String getId() {
                return id;
            }

            public String getScenic() {
                return scenic;
            }

            public String getMimg() {
                return mimg;
            }

            public String getDesc() {
                return desc;
            }

            public String getVoice_id() {
                return voice_id;
            }
        }

        public static class VoicInfoEntity {
            private String id;
            private String name;
            private String upurl;
            private String img_url;
            private String type;
            private Object desc;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setUpurl(String upurl) {
                this.upurl = upurl;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setDesc(Object desc) {
                this.desc = desc;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getUpurl() {
                return upurl;
            }

            public String getImg_url() {
                return img_url;
            }

            public String getType() {
                return type;
            }

            public Object getDesc() {
                return desc;
            }
        }

        public static class NearPorEntity {
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
    }
}
