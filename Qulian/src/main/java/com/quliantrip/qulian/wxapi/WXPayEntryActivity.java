package com.quliantrip.qulian.wxapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.WeiXinRePay;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.dialog.LoadingDialog;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 支付收银台
 */
public class WXPayEntryActivity extends SwipeBackActivity implements IWXAPIEventHandler {
    private Context mContext;

    //注册微信支付平台
    private static final String WENXI_APP_ID = "wxfc835c6ebff9d032";
    private IWXAPI api;

    private String orderId;//商品的订单编号
    private String totalPrice;//商品的价格
    private int mCurrentPayWay = 0;//当前的支付方式的 1代表着微信支付 2代表着支付宝支付
    private int oldCheckId = R.id.iv_pay_method_weixin;//支付方式以前选中的id

    //付款的钱的数量
    @Bind(R.id.tv_pay_money_amount)
    TextView payMoney;

    //支付宝支付的常量
    // 商户PID
    public static final String PARTNER = "";
    // 商户收款账号
    public static final String SELLER = "";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "";
    private static final int SDK_PAY_FLAG = 1;

    //接收支付宝支付结果
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
//                    PayResult payResult = new PayResult((String) msg.obj);
//                    /**
//                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
//                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
//                     * docType=1) 建议商户依赖异步通知
//                     */
//                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//
//                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // 判断resultStatus 为非"9000"则代表可能支付失败
//                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
//                        if (TextUtils.equals(resultStatus, "8000")) {
//                            Toast.makeText(mContext, "支付结果确认中", Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                            Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
                    break;
                }
                default:
                    break;
            }
        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pay_check_stand);
        mContext = this;
        orderId = getIntent().getStringExtra("orderId");
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        //设置支付的金额
        totalPrice = getIntent().getStringExtra("totalPrice");
        if (!TextUtils.isEmpty(totalPrice)) {
            payMoney.setText(totalPrice);
        }

        regToWx();
    }

    //进行切换支付方式
    @OnClick(R.id.rl_weixin_way)
    void weixinPay() {
        changePayWay(R.id.iv_pay_method_weixin);
    }

    @OnClick(R.id.rl_zhifubao_way)
    void zhifubaoPay() {
        changePayWay(R.id.iv_pay_method_zhifubao);
    }

    //进行支付的操作
    @OnClick(R.id.bt_commment_pay_money)
    void commentPay() {
        switch (mCurrentPayWay) {
            case 0:
                if (api == null) {
                    api = WXAPIFactory.createWXAPI(mContext, WENXI_APP_ID, false);
                }
                if (!api.isWXAppInstalled()) {
                    //提醒用户没有按照微信
                    ToastUtil.showToast(mContext, "你没有安装微信");
                    return;
                }
                if (!api.isWXAppSupportAPI()) {
                    ToastUtil.showToast(mContext, "请先更新微信应用");
                    return;
                }
                showDialog_cancel("微信支付中");

                //使用get的方式进行请求数据
                new PacketStringReQuest("http://v2.quliantrip.com/index.php?r=site/payorder"
                        + "&key=" + QulianApplication.getInstance().getLoginUser().getAuth_key()
                        + "&order_sn=" + orderId
                        + "&pay_type=wx", new WeiXinRePay().setTag(getClass().getName()));
                break;
            case 1:
                showDialog_cancel("支付宝支付中");
                break;
        }
    }

    @OnClick(R.id.iv_simple_back)
    void back() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }

    public void onEventMainThread(BaseJson bean) {
        //获取进入界面的数据
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            WeiXinRePay weiXinRePay = (WeiXinRePay) bean;
            toWeixinPay(weiXinRePay);
        }
    }

    private void regToWx() {
        api = WXAPIFactory.createWXAPI(mContext, WENXI_APP_ID, false);
        api.registerApp(WENXI_APP_ID);
    }

    private void toWeixinPay(WeiXinRePay weiXinRePay) {
        try {
            PayReq req = new PayReq();
            req.appId = weiXinRePay.getAppid();
            req.partnerId = weiXinRePay.getPartnerid();
            req.prepayId = weiXinRePay.getPrepayid();
            req.nonceStr = weiXinRePay.getNoncestr();
            req.timeStamp = weiXinRePay.getTimestamp();
            req.packageValue = weiXinRePay.getPackageX();
            req.sign = weiXinRePay.getSign();
            api.sendReq(req);
        } catch (Exception e) {
            Toast.makeText(mContext, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelDialog();
    }

    //进行选择图片的切换
    private void changePayWay(int id) {
        int oldPayWay = mCurrentPayWay;
        int newCheckId = oldCheckId;
        switch (id) {
            case R.id.iv_pay_method_weixin:
                mCurrentPayWay = 0;
                newCheckId = R.id.iv_pay_method_weixin;
                break;
            case R.id.iv_pay_method_zhifubao:
                mCurrentPayWay = 1;
                newCheckId = R.id.iv_pay_method_zhifubao;
                break;
        }
        if (oldPayWay != mCurrentPayWay) {
            ((ImageView) findViewById(oldCheckId)).setImageResource(R.mipmap.cnb_wode_nor);
            ((ImageView) findViewById(newCheckId)).setImageResource(R.mipmap.cnb_wode_pre);
        }
        oldCheckId = id;
    }

    @OnClick(R.id.rl_pay_stand_youhuiquan)
    void getYouhuiquan() {
        ToastUtil.showToast(mContext, "暂不支持，稍后开通");
    }

    //接收微信支付结果
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        int result = 0;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.errcode_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                break;
            default:
                result = R.string.errcode_unknown;
                break;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示提示框
     *
     * @param title
     */
    private LoadingDialog progressDialog;

    public void showDialog(String title) {
        cancelDialog();
        progressDialog = new LoadingDialog(mContext, title);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public void showDialog_cancel(String title) {
        cancelDialog();
        progressDialog = new LoadingDialog(mContext, title);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
    }

    public void cancelDialog() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }
}
