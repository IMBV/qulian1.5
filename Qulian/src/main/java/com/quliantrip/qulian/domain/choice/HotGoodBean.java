package com.quliantrip.qulian.domain.choice;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * Created by Qulian5 on 2016/1/20.
 * HotGoodCate
 */
public class HotGoodBean extends BaseJson {


    /**
     * code : 200
     * msg : 成功
     * data : {"cate":[{"id":"17","name":"特色餐厅"},{"id":"16","name":"门票"},{"id":"11","name":"WIFI"},{"id":"10","name":"交通"},{"id":"9","name":"购物"},{"id":"8","name":"玩乐"}],"screen":[{"id":"22","name":"商户服务","pid":"0","child":[{"id":"24","name":"英文服务","pid":"22","child":[]},{"id":"25","name":"中文服务","pid":"22","child":[]},{"id":"26","name":"可刷银联卡","pid":"22","child":[]},{"id":"27","name":"无烟坐席","pid":"22","child":[]}]},{"id":"23","name":"可预约时间","pid":"0","child":[{"id":"28","name":"即买即用","pid":"23","child":[]},{"id":"29","name":"今日可用","pid":"23","child":[]},{"id":"30","name":"明日可用","pid":"23","child":[]},{"id":"31","name":"提前2天","pid":"23","child":[]},{"id":"32","name":"提前3天以上","pid":"23","child":[]}]},{"name":"特色主题","child":[{"id":"2","tag_name":"dfdf"},{"id":"3","tag_name":"sdfsd"},{"id":"4","tag_name":"浪漫樱花"}]}],"online":[{"id":"14","name":"dddddddddddd","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","is_house":false},{"id":"15","name":"dddddddddddd","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","is_house":false},{"id":"16","name":"dddddddddddd","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","is_house":false},{"id":"19","name":"dddddddddddd","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","is_house":false},{"id":"20","name":"dddddddddddd","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","is_house":false},{"id":"21","name":"dddddddddddd","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","is_house":false},{"id":"22","name":"dddddddddddd","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","is_house":false},{"id":"33","name":"2222222","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","is_house":false},{"id":"34","name":"dddddddddddd","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","is_house":false},{"id":"29","name":"dddddddddddd","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","is_house":false},{"id":"31","name":"2222222","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","is_house":false},{"id":"32","name":"2222222","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","is_house":false},{"id":"30","name":"收到附近发生的粉红色","img":"http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg","is_house":false},{"id":"36","name":"bussid","img":"bussid","is_house":false},{"id":"37","name":"dddddddddddd","img":"dddddddddddd","is_house":false},{"id":"38","name":"dddddddddddd","img":"dddddddddddd","is_house":false}]}
     */

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
        /**
         * id : 17
         * name : 特色餐厅
         */

        private List<HotGoodCate> cate;
        /**
         * id : 22
         * name : 商户服务
         * pid : 0
         * child : [{"id":"24","name":"英文服务","pid":"22","child":[]},{"id":"25","name":"中文服务","pid":"22","child":[]},{"id":"26","name":"可刷银联卡","pid":"22","child":[]},{"id":"27","name":"无烟坐席","pid":"22","child":[]}]
         */

        private List<ScreenEntity> screen;
        /**
         * id : 14
         * name : dddddddddddd
         * img : http://www.quliantrip.com/public/attachment/201511/27/14/5657f561ee46d_460x280.jpg
         * is_house : false
         */

        private List<OnlineEntity> online;

        public void setCate(List<HotGoodCate> cate) {
            this.cate = cate;
        }

        public void setScreen(List<ScreenEntity> screen) {
            this.screen = screen;
        }

        public void setOnline(List<OnlineEntity> online) {
            this.online = online;
        }

        public List<HotGoodCate> getCate() {
            return cate;
        }

        public List<ScreenEntity> getScreen() {
            return screen;
        }

        public List<OnlineEntity> getOnline() {
            return online;
        }

        public static class HotGoodCate {
            private String id;
            private String name;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }

        public static class ScreenEntity {
            private String id;
            private String name;
            private String pid;
            /**
             * id : 24
             * name : 英文服务
             * pid : 22
             * child : []
             */

            private List<ChildEntity> child;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public void setChild(List<ChildEntity> child) {
                this.child = child;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getPid() {
                return pid;
            }

            public List<ChildEntity> getChild() {
                return child;
            }

            public static class ChildEntity {
                private String id;
                private String name;
                private String pid;
                private String tag_name;
                private List<?> child;

                public String getTag_name() {
                    return tag_name;
                }

                public void setTag_name(String tag_name) {
                    this.tag_name = tag_name;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public void setChild(List<?> child) {
                    this.child = child;
                }

                public String getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }

                public String getPid() {
                    return pid;
                }

                public List<?> getChild() {
                    return child;
                }
            }
        }

        public static class OnlineEntity {
            private String id;
            private String name;
            private String img;
            private boolean is_house;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setIs_house(boolean is_house) {
                this.is_house = is_house;
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

            public boolean isIs_house() {
                return is_house;
            }
        }
    }
}
