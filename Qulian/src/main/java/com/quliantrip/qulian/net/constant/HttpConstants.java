package com.quliantrip.qulian.net.constant;

/**
 * 定义网络请求的常量
 */
public class HttpConstants {

    /**
     * 线上服务器地址
     */

    public static final String HOST_ADDR_ROOT_NET = "http://www.quliantrip.com/mapi/index.php";
    /**
     *线上数据服务器http://rest.v2.quliantrip.com/v1/
     * 线下数据http://192.168.0.155:8089/v1/
     */
    public static final String HOST_ADDR_ROOT_LOCAL_V1 = "http://rest.v2.quliantrip.com/v1/";

    //热门商品列表
    public static final String   HOT_GOOD_LIST = HOST_ADDR_ROOT_LOCAL_V1 + "product/prolist";
    //切换城市
    public static final String CHANGE_CITY = HOST_ADDR_ROOT_LOCAL_V1 + "product/areas";

    //首页
    //首页的筛选
    public static final String HOME_SECNICPLAY_CONDITION = HOST_ADDR_ROOT_LOCAL_V1 + "product/search";
    //首页信息
    public static final String HOME_MAIN = HOST_ADDR_ROOT_LOCAL_V1 + "product/links";

    //单品详情
    public static final String GOOD_DETAIL = HOST_ADDR_ROOT_LOCAL_V1 + "product/online";
    //添加单品收藏
    public static final String GOOD_COLLECT = HOST_ADDR_ROOT_LOCAL_V1 + "user/ahouse";
    //取消单品收藏
    public static final String GOOD_CANCEL_COLLECT = HOST_ADDR_ROOT_LOCAL_V1 + "user/dhouse";
    //单品订单借口
    public static final String GOOD_ORDER = HOST_ADDR_ROOT_LOCAL_V1 + "order/orders";
    //单品检验剩余
    public static final String GOOD_ORDER_CHECK = HOST_ADDR_ROOT_LOCAL_V1 + "order/corder";//这个是公共的单凭检验的方法
    //单品提交订单
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
    //玩法确认订单
    public static final String PLAY_METHOD_ORDER_CONFIRM_SUBMIT = HOST_ADDR_ROOT_LOCAL_V1 + "playorder/tpay";

    //发现
    //优惠券
    public static final String DISCOUNT_LIST = HOST_ADDR_ROOT_LOCAL_V1 + "product/coupon";
    //语音广场
    public static final String VOICE_SQUARE_LIST = HOST_ADDR_ROOT_LOCAL_V1 + "product/spot";
    //语音广场详情
    public static final String VOICE_SQUARE_DETAIL = HOST_ADDR_ROOT_LOCAL_V1 + "product/spotxq";

    //我的
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

    //个人中心的收藏列表页面
    //我的收藏_GOOD
    public static final String ME_COLLECT_GOOD_LIST = HOST_ADDR_ROOT_LOCAL_V1 + "user/house";
    //我的收藏_PLAY_METHOD
    public static final String ME_COLLECT_PLAY_METHOD_LIST = HOST_ADDR_ROOT_LOCAL_V1 + "user/housewf";

    //我的订单列表_GOOD
    public static final String ME_ORDER_GOOD_LIST = HOST_ADDR_ROOT_LOCAL_V1 + "userorder/order";
    //商品取消订单
    public static final String MY_ORDER_GOOD_CANCEL = HOST_ADDR_ROOT_LOCAL_V1 + "userorder/delorder";
    //订单订单_GOOD
    public static final String ME_ORDER_GOOD_DETAIL = HOST_ADDR_ROOT_LOCAL_V1 + "userorder/orderxq";


    //我的订单列表_PLAY_METHOD
    public static final String ME_ORDER_PLAY_METHOD_LIST = HOST_ADDR_ROOT_LOCAL_V1 + "userorder/playorder";
    //订单详情_PLAY_METHOD
    public static final String ME_ORDER_PLAY_METHOD_DETAIL = HOST_ADDR_ROOT_LOCAL_V1 + "userorder/playorderxq";

    //用户登录后修改密码
    public static final String MY_USER_CHANG_PASSWORD = HOST_ADDR_ROOT_LOCAL_V1 + "user/respass";

    //用户意见反馈
    public static final String MY_USER_FEED_BACK = HOST_ADDR_ROOT_LOCAL_V1 + "user/feedback";




}
