package com.quliantrip.qulian.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Yuly on 2015/12/17.
 * www.quliantrip.com
 */
public class TuanBean extends BaseJson {



    private String act;
    private int area_id;
    private int cate_id;
    private int city_id;
    private String city_name;
    private String ctl;
    private String info;
    /**
     * data_total : 67
     * page : 1
     * page_size : 10
     * page_total : 7
     */

    private PageEntity page;
    private String page_title;
    private int quan_id;
    @SerializedName("return")
    private int returnX;
    private String sess_id;
    private int status;
    /**
     * bcate_type : [{"cate_id":0,"id":0,"name":"全部分类"}]
     * icon_img :
     * iconcolor :
     * iconfont :
     * id : 0
     * name : 全部分类
     */

    private List<BcateListEntity> bcate_list;
    /**
     * auto_order : 0
     * begin_time : 0
     * begin_time_format :
     * brief : 关西机场至大阪市区只需38分钟，与门市价2,860日元相比，节省30%以上！
     * buy_count : 6
     * buyin_app : 0
     * current_price : 98
     * deal_score : 0
     * distance : 0
     * end_time : 0
     * end_time_format :
     * icon : http://www.quliantrip.com/public/attachment/201512/17/11/56722c849b624_360x0.jpg
     * id : 219
     * is_lottery : 0
     * is_refund : 0
     * is_today : 0
     * name : 关西机场↔难波特急Rapit往返列车票
     * origin_price : 152
     * sub_name : 关西机场↔难波特急Rapit往返列车票
     * xpoint : 34.43438105787
     * ypoint : 135.24822235107
     */

    private List<ItemEntity> item;
    /**
     * code : default
     * name : 默认
     */

    private List<NavsEntity> navs;
    /**
     * id : 0
     * name : 全部
     * quan_sub : [{"id":0,"name":"全部","pid":0}]
     */

    private List<QuanListEntity> quan_list;

    public void setAct(String act) {
        this.act = act;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setCtl(String ctl) {
        this.ctl = ctl;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }

    public void setPage_title(String page_title) {
        this.page_title = page_title;
    }

    public void setQuan_id(int quan_id) {
        this.quan_id = quan_id;
    }

    public void setReturnX(int returnX) {
        this.returnX = returnX;
    }

    public void setSess_id(String sess_id) {
        this.sess_id = sess_id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setBcate_list(List<BcateListEntity> bcate_list) {
        this.bcate_list = bcate_list;
    }

    public void setItem(List<ItemEntity> item) {
        this.item = item;
    }

    public void setNavs(List<NavsEntity> navs) {
        this.navs = navs;
    }

    public void setQuan_list(List<QuanListEntity> quan_list) {
        this.quan_list = quan_list;
    }

    public String getAct() {
        return act;
    }

    public int getArea_id() {
        return area_id;
    }

    public int getCate_id() {
        return cate_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getCtl() {
        return ctl;
    }

    public String getInfo() {
        return info;
    }

    public PageEntity getPage() {
        return page;
    }

    public String getPage_title() {
        return page_title;
    }

    public int getQuan_id() {
        return quan_id;
    }

    public int getReturnX() {
        return returnX;
    }

    public String getSess_id() {
        return sess_id;
    }

    public int getStatus() {
        return status;
    }

    public List<BcateListEntity> getBcate_list() {
        return bcate_list;
    }

    public List<ItemEntity> getItem() {
        return item;
    }

    public List<NavsEntity> getNavs() {
        return navs;
    }

    public List<QuanListEntity> getQuan_list() {
        return quan_list;
    }

    public static class PageEntity {
        private String data_total;
        private int page;
        private int page_size;
        private int page_total;

        public void setData_total(String data_total) {
            this.data_total = data_total;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }

        public void setPage_total(int page_total) {
            this.page_total = page_total;
        }

        public String getData_total() {
            return data_total;
        }

        public int getPage() {
            return page;
        }

        public int getPage_size() {
            return page_size;
        }

        public int getPage_total() {
            return page_total;
        }
    }

    public static class BcateListEntity {
        private String icon_img;
        private String iconcolor;
        private String iconfont;
        private int id;
        private String name;
        /**
         * cate_id : 0
         * id : 0
         * name : 全部分类
         */

        private List<BcateTypeEntity> bcate_type;

        public void setIcon_img(String icon_img) {
            this.icon_img = icon_img;
        }

        public void setIconcolor(String iconcolor) {
            this.iconcolor = iconcolor;
        }

        public void setIconfont(String iconfont) {
            this.iconfont = iconfont;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBcate_type(List<BcateTypeEntity> bcate_type) {
            this.bcate_type = bcate_type;
        }

        public String getIcon_img() {
            return icon_img;
        }

        public String getIconcolor() {
            return iconcolor;
        }

        public String getIconfont() {
            return iconfont;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<BcateTypeEntity> getBcate_type() {
            return bcate_type;
        }

        public static class BcateTypeEntity {
            private int cate_id;
            private int id;
            private String name;

            public void setCate_id(int cate_id) {
                this.cate_id = cate_id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCate_id() {
                return cate_id;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }
    }

    public static class ItemEntity extends BaseJson{
        private String auto_order;
        private String begin_time;
        private String begin_time_format;
        private String brief;
        private String buy_count;
        private int buyin_app;
        private int current_price;
        private int deal_score;
        private int distance;
        private String end_time;
        private String end_time_format;
        private String icon;
        private String id;
        private String is_lottery;
        private String is_refund;
        private int is_today;
        private String name;
        private int origin_price;
        private String sub_name;
        private double xpoint;
        private double ypoint;

        public void setAuto_order(String auto_order) {
            this.auto_order = auto_order;
        }

        public void setBegin_time(String begin_time) {
            this.begin_time = begin_time;
        }

        public void setBegin_time_format(String begin_time_format) {
            this.begin_time_format = begin_time_format;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public void setBuy_count(String buy_count) {
            this.buy_count = buy_count;
        }

        public void setBuyin_app(int buyin_app) {
            this.buyin_app = buyin_app;
        }

        public void setCurrent_price(int current_price) {
            this.current_price = current_price;
        }

        public void setDeal_score(int deal_score) {
            this.deal_score = deal_score;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public void setEnd_time_format(String end_time_format) {
            this.end_time_format = end_time_format;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setIs_lottery(String is_lottery) {
            this.is_lottery = is_lottery;
        }

        public void setIs_refund(String is_refund) {
            this.is_refund = is_refund;
        }

        public void setIs_today(int is_today) {
            this.is_today = is_today;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setOrigin_price(int origin_price) {
            this.origin_price = origin_price;
        }

        public void setSub_name(String sub_name) {
            this.sub_name = sub_name;
        }

        public void setXpoint(double xpoint) {
            this.xpoint = xpoint;
        }

        public void setYpoint(double ypoint) {
            this.ypoint = ypoint;
        }

        public String getAuto_order() {
            return auto_order;
        }

        public String getBegin_time() {
            return begin_time;
        }

        public String getBegin_time_format() {
            return begin_time_format;
        }

        public String getBrief() {
            return brief;
        }

        public String getBuy_count() {
            return buy_count;
        }

        public int getBuyin_app() {
            return buyin_app;
        }

        public int getCurrent_price() {
            return current_price;
        }

        public int getDeal_score() {
            return deal_score;
        }

        public int getDistance() {
            return distance;
        }

        public String getEnd_time() {
            return end_time;
        }

        public String getEnd_time_format() {
            return end_time_format;
        }

        public String getIcon() {
            return icon;
        }

        public String getId() {
            return id;
        }

        public String getIs_lottery() {
            return is_lottery;
        }

        public String getIs_refund() {
            return is_refund;
        }

        public int getIs_today() {
            return is_today;
        }

        public String getName() {
            return name;
        }

        public int getOrigin_price() {
            return origin_price;
        }

        public String getSub_name() {
            return sub_name;
        }

        public double getXpoint() {
            return xpoint;
        }

        public double getYpoint() {
            return ypoint;
        }
    }

    public static class NavsEntity {
        private String code;
        private String name;

        public void setCode(String code) {
            this.code = code;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    public static class QuanListEntity {
        private int id;
        private String name;
        /**
         * id : 0
         * name : 全部
         * pid : 0
         */

        private List<QuanSubEntity> quan_sub;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setQuan_sub(List<QuanSubEntity> quan_sub) {
            this.quan_sub = quan_sub;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<QuanSubEntity> getQuan_sub() {
            return quan_sub;
        }

        public static class QuanSubEntity {
            private int id;
            private String name;
            private int pid;

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public int getPid() {
                return pid;
            }
        }
    }
}
