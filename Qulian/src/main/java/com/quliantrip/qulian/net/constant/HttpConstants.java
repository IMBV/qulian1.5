package com.quliantrip.qulian.net.constant;

/**
 * 定义网络请求的常量
 * Created by yuly on 2015/11/27.
 */
public class HttpConstants {
    /**
     * 线上服务器地址
     */
    public static final String HOST_ADDR_ROOT_NET = "http://www.quliantrip.com/mapi/index.php";
    /**
     * 本地v1服务器
     */
    public static final String HOST_ADDR_ROOT_LOCAL_V1 = "http://192.168.0.155:8089/v1/";
    //手机号查重
    public static final String CHECK_MOBILE_NUMBER = HOST_ADDR_ROOT_LOCAL_V1 + "user/mobile";
    //手机号注册
    public static final String MOBILE_REGISTER = HOST_ADDR_ROOT_LOCAL_V1 + "user/psignup";
    //邮箱注册
    public static final String EMAIL_REGISTER = HOST_ADDR_ROOT_LOCAL_V1 + "user/signup";
    //用户登录
    public static final String USER_LOGON = HOST_ADDR_ROOT_LOCAL_V1 + "user/login";
    //查询联系人
    public static final String ALL_LINKMAN = HOST_ADDR_ROOT_LOCAL_V1 + "user/contacts";
    //修改联系人
    public static final String EDIT_LINKMAN = HOST_ADDR_ROOT_LOCAL_V1 + "user/ucontacts";
    //添加联系人
    public static final String ADD_LINKMAN = HOST_ADDR_ROOT_LOCAL_V1 + "user/acontacts";
    //删除联系人
    public static final String DELETE_LINKMAN = HOST_ADDR_ROOT_LOCAL_V1 + "user/dcontacts";

    //热门商品列表
    public static final String HOT_GOOD_LIST = HOST_ADDR_ROOT_LOCAL_V1 + "product/prolist";
    //切换城市
    public static final String CHANGE_CITY = HOST_ADDR_ROOT_LOCAL_V1 + "product/areas";


    //单品详情
    public static final String GOOD_DETAIL = HOST_ADDR_ROOT_LOCAL_V1 + "product/online";
    //添加单品收藏
    public static final String GOOD_COLLECT = HOST_ADDR_ROOT_LOCAL_V1 + "user/ahouse";
    //取消单品收藏
    public static final String GOOD_CANCEL_COLLECT = HOST_ADDR_ROOT_LOCAL_V1 + "user/dhouse";
    //单品订单借口
    public static final String GOOD_ORDER = HOST_ADDR_ROOT_LOCAL_V1 + "order/orders";
    //单品检验剩余
    public static final String GOOD_ORDER_CHECK = HOST_ADDR_ROOT_LOCAL_V1 + "order/corder";//这个事公共的单凭检验的方法
    //单品提交订单订单
    public static final String GOOD_ORDER_SUBMIT = HOST_ADDR_ROOT_LOCAL_V1 + "order/torder";
    //单品确认订单
    public static final String GOOD_ORDER_CONFIRM = HOST_ADDR_ROOT_LOCAL_V1 + "order/qorder";
    //玩法确认订单提交
    public static final String GOOD_ORDER_CONFIRM_SUBMIT = HOST_ADDR_ROOT_LOCAL_V1 + "order/tpay";


    //玩法列表
    public static final String PLAY_METHOD_LIST = HOST_ADDR_ROOT_LOCAL_V1 + "product/play";
    //玩法详情
    public static final String PLAY_METHOD_DETRAIL = HOST_ADDR_ROOT_LOCAL_V1 + "product/playxq";
    //玩法订单
    public static final String PLAY_METHOD_ORDER = HOST_ADDR_ROOT_LOCAL_V1 + "playorder/order";
    //玩法提交订单
    public static final String PLAY_METHOD_ORDER_SUBMIT = HOST_ADDR_ROOT_LOCAL_V1 + "playorder/torder";
    //玩法确认订单
    public static final String PLAY_METHOD_ORDER_CONFIRM = HOST_ADDR_ROOT_LOCAL_V1 + "playorder/qorder";
    //玩法确认订单提交
    public static final String PLAY_METHOD_ORDER_CONFIRM_SUBMIT = HOST_ADDR_ROOT_LOCAL_V1 + "playorder/tpay";



    //发现
    //优惠券
    public static final String DISCOUNT_LIST = HOST_ADDR_ROOT_LOCAL_V1 + "product/coupon";
    //语音广场
    public static final String VOICE_SQUARE_LIST = HOST_ADDR_ROOT_LOCAL_V1 + "product/spot";

    //首页
    //首页的筛选
    public static final String HOME_SECNICPLAY_CONDITION = HOST_ADDR_ROOT_LOCAL_V1 + "product/search";


}
