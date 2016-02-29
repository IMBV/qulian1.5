package com.quliantrip.qulian.domain.choice.playMethod;

import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.home.HomeShowBean;

import java.util.List;

/**
 * 玩法的列表
 */
public class PlayMethodBean extends BaseJson {

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
        private List<PlayBean> play;

        public void setScreen(List<ScreenEntity> screen) {
            this.screen = screen;
        }

        public void setPlay(List<PlayBean> play) {
            this.play = play;
        }

        public List<ScreenEntity> getScreen() {
            return screen;
        }

        public List<PlayBean> getPlay() {
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
    }
}
