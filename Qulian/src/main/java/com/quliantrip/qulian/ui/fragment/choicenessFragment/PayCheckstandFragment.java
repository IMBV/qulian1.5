package com.quliantrip.qulian.ui.fragment.choicenessFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.WeiXinRePay;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.dialog.LoadingDialog;
import com.tencent.mm.sdk.constants.ConstantsAPI;
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

public class PayCheckstandFragment extends SwipeBackActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pay_check_stand);
        mContext = this;
        orderId = getIntent().getStringExtra("orderId");
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        regToWx();
        //设置支付的金额
        totalPrice = getIntent().getStringExtra("totalPrice");
        if (!TextUtils.isEmpty(totalPrice)) {
            payMoney.setText(totalPrice);
        }
    }

    @OnClick(R.id.rl_weixin_way)
    void weixinPay() {
        changePayWay(R.id.iv_pay_method_weixin);
    }

    @OnClick(R.id.rl_zhifubao_way)
    void zhifubaoPay() {
        changePayWay(R.id.iv_pay_method_zhifubao);
    }

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
                Map<String, String> map = new HashMap<String, String>();
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
    void back(){
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

    private void regToWx(){
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
            cancelDialog();
        } catch (Exception e) {
            Toast.makeText(mContext, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//        api.handleIntent(intent, this);
//    }
//
//    @Override
//    public void onReq(BaseReq req) {
//
//    }

//    @Override
//    public void onResp(BaseResp resp) {
//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("支付结果");
//            builder.setMessage("支付结果" + String.valueOf(resp.errCode));
//            builder.show();
//        }
//        ToastUtil.showToast(mContext,"jieguo");
//    }

    private LoadingDialog progressDialog;

    /**
     * 显示提示框
     *
     * @param title
     */
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
