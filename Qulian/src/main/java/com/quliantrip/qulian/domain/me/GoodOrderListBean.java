package com.quliantrip.qulian.domain.me;

import com.google.gson.annotations.SerializedName;
import com.quliantrip.qulian.domain.BaseJson;

import java.util.List;

/**
 * 我的单品的订单
 */
public class GoodOrderListBean extends BaseJson{


    /**
     * code : 200
     * msg : 成功
     * data : [{"id":"73","order_sn":"56a97a89943631.61237090","pay_status":"1","total_price":"11111.0000","order_status":"0","is_use":"1","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"74","order_sn":"56a97a960ce339.78176964","pay_status":"1","total_price":"2222.0000","order_status":"0","is_use":"1","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"76","order_sn":"56a97a9e4eebc8.30786861","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"94","order_sn":"56aacd4dc31014.54294110","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"95","order_sn":"56aace96db3082.70390328","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"96","order_sn":"56aaceb57ed7f2.44997769","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"97","order_sn":"56aacece631d76.39079796","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"98","order_sn":"56aad1f81aae78.95249042","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"99","order_sn":"56aad1f817fef5.43333665","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"100","order_sn":"56aad1fa800894.65362759","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"101","order_sn":"56aad1fa8373a1.81587189","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"102","order_sn":"56aad2030326f8.84761062","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"103","order_sn":"56aad3ed9529b1.43677465","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"104","order_sn":"1601301547314100","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":"恶趣味吃","img":"67,68,69","package":"演出票 :大大,景点门票:儿童票","imgs":"http://7xpt5s.com1.z0.glb.clouddn.com/a990be1db07e27371360ed39fa31e868.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/aa8aa39511b9cda752b70d7ebe4d6bc8.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/d8e3e203729691bcfd90739be39f583e.jpg"},{"id":"106","order_sn":"1601301713264555","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"107","order_sn":"1601301715404451","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"108","order_sn":"1601301719054631","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":null,"name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"113","order_sn":"1601301723224472","pay_status":"0","total_price":"11111.0000","order_status":"0","is_use":"0","sku_id":"15","num":"3","price":"1111.0000","name":null,"img":null,"package":"演出票 :大大,景点门票:儿童票","imgs":""},{"id":"115","order_sn":"1601301930474384","pay_status":"0","total_price":"111.0000","order_status":"0","is_use":"0","sku_id":"107","num":"11","price":"123.0000","name":"恶趣味吃","img":"67,68,69","package":"美食:的撒,日本WIFI:多大的","imgs":"http://7xpt5s.com1.z0.glb.clouddn.com/a990be1db07e27371360ed39fa31e868.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/aa8aa39511b9cda752b70d7ebe4d6bc8.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/d8e3e203729691bcfd90739be39f583e.jpg"},{"id":"116","order_sn":"1601301934464943","pay_status":"0","total_price":"111.0000","order_status":"0","is_use":"0","sku_id":"107","num":"11","price":"123.0000","name":"恶趣味吃","img":"67,68,69","package":"美食:的撒,日本WIFI:多大的","imgs":"http://7xpt5s.com1.z0.glb.clouddn.com/a990be1db07e27371360ed39fa31e868.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/aa8aa39511b9cda752b70d7ebe4d6bc8.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/d8e3e203729691bcfd90739be39f583e.jpg"},{"id":"117","order_sn":"1601302025514284","pay_status":"0","total_price":"111.0000","order_status":"0","is_use":"0","sku_id":"101","num":"222","price":"123.0000","name":"恶趣味吃","img":"67,68,69","package":"交通卡:2,观光巴士:的发生","imgs":"http://7xpt5s.com1.z0.glb.clouddn.com/a990be1db07e27371360ed39fa31e868.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/aa8aa39511b9cda752b70d7ebe4d6bc8.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/d8e3e203729691bcfd90739be39f583e.jpg"},{"id":"118","order_sn":"1601302026074582","pay_status":"0","total_price":"111.0000","order_status":"0","is_use":"0","sku_id":"101","num":"222","price":"123.0000","name":"恶趣味吃","img":"67,68,69","package":"交通卡:2,观光巴士:的发生","imgs":"http://7xpt5s.com1.z0.glb.clouddn.com/a990be1db07e27371360ed39fa31e868.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/aa8aa39511b9cda752b70d7ebe4d6bc8.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/d8e3e203729691bcfd90739be39f583e.jpg"},{"id":"119","order_sn":"1601302028324109","pay_status":"0","total_price":"111.0000","order_status":"0","is_use":"0","sku_id":"107","num":"0","price":"123.0000","name":"恶趣味吃","img":"67,68,69","package":"美食:的撒,日本WIFI:多大的","imgs":"http://7xpt5s.com1.z0.glb.clouddn.com/a990be1db07e27371360ed39fa31e868.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/aa8aa39511b9cda752b70d7ebe4d6bc8.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/d8e3e203729691bcfd90739be39f583e.jpg"},{"id":"120","order_sn":"1601302033054136","pay_status":"0","total_price":"111.0000","order_status":"0","is_use":"0","sku_id":"107","num":"11","price":"123.0000","name":"恶趣味吃","img":"67,68,69","package":"美食:的撒,日本WIFI:多大的","imgs":"http://7xpt5s.com1.z0.glb.clouddn.com/a990be1db07e27371360ed39fa31e868.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/aa8aa39511b9cda752b70d7ebe4d6bc8.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/d8e3e203729691bcfd90739be39f583e.jpg"},{"id":"121","order_sn":"1601311000004313","pay_status":"0","total_price":"111.0000","order_status":"0","is_use":"0","sku_id":"106","num":"0","price":"123.0000","name":"恶趣味吃","img":"67,68,69","package":"交通卡:2,观光巴士:股份的公司股份的三国杀","imgs":"http://7xpt5s.com1.z0.glb.clouddn.com/a990be1db07e27371360ed39fa31e868.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/aa8aa39511b9cda752b70d7ebe4d6bc8.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/d8e3e203729691bcfd90739be39f583e.jpg"},{"id":"122","order_sn":"1601311000494510","pay_status":"0","total_price":"111.0000","order_status":"0","is_use":"0","sku_id":"106","num":"0","price":"123.0000","name":"恶趣味吃","img":"67,68,69","package":"交通卡:2,观光巴士:股份的公司股份的三国杀","imgs":"http://7xpt5s.com1.z0.glb.clouddn.com/a990be1db07e27371360ed39fa31e868.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/aa8aa39511b9cda752b70d7ebe4d6bc8.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/d8e3e203729691bcfd90739be39f583e.jpg"},{"id":"123","order_sn":"1601311002384915","pay_status":"0","total_price":"111.0000","order_status":"0","is_use":"0","sku_id":"106","num":"0","price":"123.0000","name":"恶趣味吃","img":"67,68,69","package":"交通卡:2,观光巴士:股份的公司股份的三国杀","imgs":"http://7xpt5s.com1.z0.glb.clouddn.com/a990be1db07e27371360ed39fa31e868.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/aa8aa39511b9cda752b70d7ebe4d6bc8.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/d8e3e203729691bcfd90739be39f583e.jpg"},{"id":"124","order_sn":"16013110042544","pay_status":"0","total_price":"111.0000","order_status":"0","is_use":"0","sku_id":"106","num":"0","price":"123.0000","name":"恶趣味吃","img":"67,68,69","package":"交通卡:2,观光巴士:股份的公司股份的三国杀","imgs":"http://7xpt5s.com1.z0.glb.clouddn.com/a990be1db07e27371360ed39fa31e868.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/aa8aa39511b9cda752b70d7ebe4d6bc8.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/d8e3e203729691bcfd90739be39f583e.jpg"},{"id":"125","order_sn":"1601311004304845","pay_status":"0","total_price":"111.0000","order_status":"0","is_use":"0","sku_id":"106","num":"0","price":"123.0000","name":"恶趣味吃","img":"67,68,69","package":"交通卡:2,观光巴士:股份的公司股份的三国杀","imgs":"http://7xpt5s.com1.z0.glb.clouddn.com/a990be1db07e27371360ed39fa31e868.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/aa8aa39511b9cda752b70d7ebe4d6bc8.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/d8e3e203729691bcfd90739be39f583e.jpg"},{"id":"126","order_sn":"1601311009254417","pay_status":"0","total_price":"111.0000","order_status":"0","is_use":"0","sku_id":"103","num":"22","price":"123.0000","name":"恶趣味吃","img":"67,68,69","package":"交通卡:,观光巴士:股份的公司股份的三国杀","imgs":"http://7xpt5s.com1.z0.glb.clouddn.com/a990be1db07e27371360ed39fa31e868.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/aa8aa39511b9cda752b70d7ebe4d6bc8.jpg,http://7xpt5s.com1.z0.glb.clouddn.com/d8e3e203729691bcfd90739be39f583e.jpg"}]
     */

    private int code;
    private String msg;
    /**
     * id : 73
     * order_sn : 56a97a89943631.61237090
     * pay_status : 1
     * total_price : 11111.0000
     * order_status : 0
     * is_use : 1
     * sku_id : 15
     * num : 3
     * price : 1111.0000
     * name : null
     * img : null
     * package : 演出票 :大大,景点门票:儿童票
     * imgs :
     */

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
        private String id;
        private String order_sn;
        private String pay_status;
        private String total_price;
        private String order_status;
        private String is_use;
        private String sku_id;
        private String num;
        private String price;
        private String name;
        private String img;
        @SerializedName("package")
        private String packageX;
        private String imgs;

        public void setId(String id) {
            this.id = id;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public void setIs_use(String is_use) {
            this.is_use = is_use;
        }

        public void setSku_id(String sku_id) {
            this.sku_id = sku_id;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getId() {
            return id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public String getPay_status() {
            return pay_status;
        }

        public String getTotal_price() {
            return total_price;
        }

        public String getOrder_status() {
            return order_status;
        }

        public String getIs_use() {
            return is_use;
        }

        public String getSku_id() {
            return sku_id;
        }

        public String getNum() {
            return num;
        }

        public String getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }

        public Object getImg() {
            return img;
        }

        public String getPackageX() {
            return packageX;
        }

        public String getImgs() {
            return imgs;
        }
    }
}
