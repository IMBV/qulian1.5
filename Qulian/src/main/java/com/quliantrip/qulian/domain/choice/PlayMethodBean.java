package com.quliantrip.qulian.domain.choice;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * Created by Qulian5 on 2016/1/22.
 */
public class PlayMethodBean extends BaseJson{

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


        private List<ScreenEntity> screen;


        private List<PlayEntity> play;

        public void setScreen(List<ScreenEntity> screen) {
            this.screen = screen;
        }

        public void setPlay(List<PlayEntity> play) {
            this.play = play;
        }

        public List<ScreenEntity> getScreen() {
            return screen;
        }

        public List<PlayEntity> getPlay() {
            return play;
        }

        public static class ScreenEntity {
            private String name;


            private List<ChildEntity> child;

            public void setName(String name) {
                this.name = name;
            }

            public void setChild(List<ChildEntity> child) {
                this.child = child;
            }

            public String getName() {
                return name;
            }

            public List<ChildEntity> getChild() {
                return child;
            }

            public static class ChildEntity {
                private String id;
                private String tag_name;
                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setTag_name(String tag_name) {
                    this.tag_name = tag_name;
                }

                public String getId() {
                    return id;
                }

                public String getTag_name() {
                    return tag_name;
                }
            }
        }

        public static class PlayEntity {
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
    }
}
