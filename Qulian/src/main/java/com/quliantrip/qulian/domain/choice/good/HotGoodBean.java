package com.quliantrip.qulian.domain.choice.good;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 热门商品的数据的bean对象
 */
public class HotGoodBean extends BaseJson {
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
        private ProductInfoEntity productInfo;

        private List<HotGoodCate> cate;
        private List<ScreenEntity> screen;

        private List<GoodBean> online;

        public ProductInfoEntity getProductInfo() {
            return productInfo;
        }

        public void setProductInfo(ProductInfoEntity productInfo) {
            this.productInfo = productInfo;
        }

        public void setCate(List<HotGoodCate> cate) {
            this.cate = cate;
        }

        public void setScreen(List<ScreenEntity> screen) {
            this.screen = screen;
        }

        public void setOnline(List<GoodBean> online) {
            this.online = online;
        }

        public List<HotGoodCate> getCate() {
            return cate;
        }

        public List<ScreenEntity> getScreen() {
            return screen;
        }

        public List<GoodBean> getOnline() {
            return online;
        }

        public static class ProductInfoEntity {
            private String id;
            private String name;
            private String sale;
            private String proce;
            private String imgs;
            private String chinese_name;
            private String meter;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setSale(String sale) {
                this.sale = sale;
            }

            public void setProce(String proce) {
                this.proce = proce;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
            }

            public void setChinese_name(String chinese_name) {
                this.chinese_name = chinese_name;
            }

            public void setMeter(String meter) {
                this.meter = meter;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getSale() {
                return sale;
            }

            public String getProce() {
                return proce;
            }

            public String getImgs() {
                return imgs;
            }

            public String getChinese_name() {
                return chinese_name;
            }

            public String getMeter() {
                return meter;
            }
        }

        public static class HotGoodCate {
            public HotGoodCate(String id, String name) {
                this.id = id;
                this.name = name;
            }

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
    }
}
