package com.quliantrip.qulian.domain.home;

import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * s
 */
public class HomeShowBean extends BaseJson {
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
        private List<BannerEntity> banner;
        private List<MenuEntity> menu;
        private List<PlayEntity> play;

        public void setProductInfo(ProductInfoEntity productInfo) {
            this.productInfo = productInfo;
        }

        public void setBanner(List<BannerEntity> banner) {
            this.banner = banner;
        }

        public void setMenu(List<MenuEntity> menu) {
            this.menu = menu;
        }

        public void setPlay(List<PlayEntity> play) {
            this.play = play;
        }

        public ProductInfoEntity getProductInfo() {
            return productInfo;
        }

        public List<BannerEntity> getBanner() {
            return banner;
        }

        public List<MenuEntity> getMenu() {
            return menu;
        }

        public List<PlayEntity> getPlay() {
            return play;
        }

        public static class ProductInfoEntity {
            private String id;
            private String name;
            private String img;
            private String desc;
            private String xpoint;
            private String ypoint;
            private String imgs;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public void setXpoint(String xpoint) {
                this.xpoint = xpoint;
            }

            public void setYpoint(String ypoint) {
                this.ypoint = ypoint;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
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

            public String getDesc() {
                return desc;
            }

            public String getXpoint() {
                return xpoint;
            }

            public String getYpoint() {
                return ypoint;
            }

            public String getImgs() {
                return imgs;
            }
        }

        public static class BannerEntity {
            private String id;
            private String name;
            private String image;
            private String cate_id;
            private String pro_id;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public void setPro_id(String pro_id) {
                this.pro_id = pro_id;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getImage() {
                return image;
            }

            public String getCate_id() {
                return cate_id;
            }

            public String getPro_id() {
                return pro_id;
            }
        }

        public static class MenuEntity {
            private String id;
            private String name;
            private String image;
            private String cate_id;
            private String pro_id;
            private String iteam;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public void setPro_id(String pro_id) {
                this.pro_id = pro_id;
            }

            public void setIteam(String iteam) {
                this.iteam = iteam;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getImage() {
                return image;
            }

            public String getCate_id() {
                return cate_id;
            }

            public String getPro_id() {
                return pro_id;
            }

            public String getIteam() {
                return iteam;
            }
        }

        public static class PlayEntity {
            private String id;
            private String name;
            private String img;
            private String title;
            private String buynum;
            private String imgs;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setBuynum(String buynum) {
                this.buynum = buynum;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
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

            public String getTitle() {
                return title;
            }

            public String getBuynum() {
                return buynum;
            }

            public String getImgs() {
                return imgs;
            }
        }
    }
}
