package com.quliantrip.qulian.ui.fragment.meFragment;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.me.UserInfoBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.ui.activity.SimpleBackActivity;
import com.quliantrip.qulian.ui.activity.mainAcivity.MainActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;
import com.quliantrip.qulian.view.ClearEditText;
import com.tencent.connect.UserInfo;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tencent.connect.common.Constants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class LoginFragment extends BaseFragment {
    private static final String WEIXIN_APP_ID = "WX API KEY";
    private IWXAPI mWeixinAPI;

    @Bind(R.id.ct_userinfo_name)
    ClearEditText name;
    @Bind(R.id.ct_userinfo_password)
    ClearEditText password;
    @Bind(R.id.bt_user_login)
    Button button;

    private Tencent mTencent; //qq主操作对象

    private IUiListener loginListener; //授权登录监听器
    private IUiListener userInfoListener; //获取用户信息监听器

    private String scope; //获取信息的范围参数
    private UserInfo userInfo; //qq用户信息


    @Override
    public View initView() {
        EventBus.getDefault().register(this);
        View view = View.inflate(mContext, R.layout.fragment_me_login, null);
        ButterKnife.bind(this, view);
        button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_sub_bg));
        initListener();
        initData();
        return view;
    }
    private void initListener() {
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(name.getText())) {
                    button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_sub_bg));
                    button.setTextColor(CommonHelp.getColor(R.color.login_bg_color));
                } else {
                    button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_collor));
                    button.setTextColor(CommonHelp.getColor(R.color.colorPrimary));
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onEventMainThread(BaseJson bean) {
        cancelDialog();
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            UserInfoBean userInfoBean = (UserInfoBean) bean;
            if (userInfoBean.getCode() == 200) {
                QulianApplication.getInstance().saveUserInfo(userInfoBean.getData());
                Intent intent = new Intent(mContext, MainActivity.class);
                ((SimpleBackActivity) mContext).setResult(((SimpleBackActivity) mContext).RESULT_OK, intent);
                ((SimpleBackActivity) mContext).finish();
                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
            }
            ToastUtil.showToast(mContext, userInfoBean.getMsg());
        }
    }

    @Override
    public void initDate() {
        //这里可以使用保存密码
    }

    @OnClick(R.id.bt_user_login)
    void nameLogin() {
        String inName = name.getText().toString().trim();
        String inPassword = password.getText().toString().trim();
        if (TextUtils.isEmpty(inName)) {
            ToastUtil.showToast(mContext, "请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(inPassword)) {
            ToastUtil.showToast(mContext, "请输入密码");
            return;
        }

        showDialog("正在登录...");
        Map<String, String> map = new HashMap<String, String>();
        map.put("LoginForm[username]", inName);
        map.put("LoginForm[password]", inPassword);
        new PacketStringReQuest(HttpConstants.USER_LOGON, new UserInfoBean().setTag(getClass().getName()), map, null);
    }

    //用户注册
    @OnClick(R.id.tv_btn_register_account)
    void registerAccount() {
        UIHelper.showRegister(mContext, null);
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    //找回密码
    @OnClick(R.id.tv_me_search_back_password)
    void searchPassword(){
        UIHelper.showSearchBackPassword(mContext, null);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    /**
     * 下面是关于第三方登录的数据
     */
    @OnClick(R.id.iv_third_qq)
    void qqLogin() {
        showDialog_cancel("qq登录中");
        //如果session无效，就开始登录
        if (!mTencent.isSessionValid()) {
            //开始qq授权登录
            mTencent.login((SimpleBackActivity) mContext, "all", loginListener);
        }
    }

    @OnClick(R.id.iv_third_weixi)
    void weixinLogin() {
        showDialog_cancel("微信登录中");
        if (mWeixinAPI == null) {
            mWeixinAPI = WXAPIFactory.createWXAPI(mContext, WEIXIN_APP_ID, false);
        }

        if (!mWeixinAPI.isWXAppInstalled()) {
            //提醒用户没有按照微信
            ToastUtil.showToast(mContext, "你没有安装微信");
            return;
        }

        if (!mWeixinAPI.isWXAppSupportAPI()) {
            ToastUtil.showToast(mContext, "请先更新微信应用");
            return;
        }
        mWeixinAPI.registerApp(WEIXIN_APP_ID);

        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";//"snsapi_base"
        req.state = "req.state";
        mWeixinAPI.sendReq(req);
    }

    @Override
    public void onDestroy() {
        if (mTencent != null) {
            //注销登录
            mTencent.logout(mContext);
        }
        super.onDestroy();
    }

    //qq登录信息的初始化
    private void initData() {
        //初始化qq主操作对象
        mTencent = Tencent.createInstance("1104937113", mContext);
        //要所有权限，不然会再次申请增量权限，这里不要设置成get_user_info,add_t
        scope = "all";

        loginListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                ToastUtil.showToast(mContext, "数据错误");
            }

            /**
             * 返回json数据样例
             *
             * {"ret":0,"pay_token":"D3D678728DC580FBCDE15722B72E7365",
             * "pf":"desktop_m_qq-10000144-android-2002-",
             * "query_authority_cost":448,
             * "authority_cost":-136792089,
             * "openid":"015A22DED93BD15E0E6B0DDB3E59DE2D",
             * "expires_in":7776000,
             * "pfkey":"6068ea1c4a716d4141bca0ddb3df1bb9",
             * "msg":"",
             * "access_token":"A2455F491478233529D0106D2CE6EB45",
             * "login_cost":499}
             */
            @Override
            public void onComplete(Object value) {
                ToastUtil.showToast(mContext, "登录成功010");
                System.out.println("有数据返回..");
                if (value == null) {
                    return;
                }

                try {
                    JSONObject jo = (JSONObject) value;
                    int ret = jo.getInt("ret");
                    System.out.println("json=" + String.valueOf(jo));

                    if (ret == 0) {
                        ToastUtil.showToast(mContext, "登录成功");
                        String openID = jo.getString("openid");
                        String accessToken = jo.getString("access_token");
                        String expires = jo.getString("expires_in");
                        mTencent.setOpenId(openID);
                        mTencent.setAccessToken(accessToken, expires);
                    }

                } catch (Exception e) {
                }
            }

            @Override
            public void onCancel() {
                ToastUtil.showToast(mContext, "你取消了登录");

            }
        };

        userInfoListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
            }

            /**
             * 返回用户信息样例
             *
             * {"is_yellow_year_vip":"0","ret":0,
             * "figureurl_qq_1":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/40",
             * "figureurl_qq_2":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
             * "nickname":"攀爬←蜗牛","yellow_vip_level":"0","is_lost":0,"msg":"",
             * "city":"黄冈","
             * figureurl_1":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/50",
             * "vip":"0","level":"0",
             * "figureurl_2":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
             * "province":"湖北",
             * "is_yellow_vip":"0","gender":"男",
             * "figureurl":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/30"}
             */
            @Override
            public void onComplete(Object arg0) {
                ToastUtil.showToast(mContext, "登录成功020");
                if (arg0 == null) {
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) arg0;
                    int ret = jo.getInt("ret");
                    System.out.println("json=" + String.valueOf(jo));
                    String nickName = jo.getString("nickname");
                    String gender = jo.getString("gender");

                    ToastUtil.showToast(mContext, "你好，" + nickName);

                } catch (Exception e) {
                }
            }

            @Override
            public void onCancel() {

            }
        };
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, loginListener);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
