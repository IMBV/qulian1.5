package com.quliantrip.qulian.ui.fragment.choicenessFragment;

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
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 支付收银台
 */
public class PayCheckstandFragment extends BaseFragment {
    @Bind(R.id.tv_pay_money_amount)
    TextView payMoney;
    private View view;

    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.fragment_pay_check_stand, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        regToWx();
        return view;
    }

    @Override
    public void initDate() {
        String totalPrice = getArguments().getString("totalPrice");
        if(!TextUtils.isEmpty(totalPrice)){
            payMoney.setText(totalPrice);
        }
    }

    private int mCurrentPayWay = 0;
    private int oldCheckId = R.id.iv_pay_method_weixin;

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
                showDialog_cancel("支付宝支付中");
                Map<String, String> map = new HashMap<String, String>();
//                +"&"+"key="+ QulianApplication.getInstance().getLoginUser().getAuth_key()
//                        +"&order_sn="+
                new PacketStringReQuest("http://v2.quliantrip.com/index.php?r=site/payorder", new WeiXinRePay().setTag(getClass().getName()));
                break;
            case 1:
                showDialog_cancel("支付宝支付中");
                break;
        }
    }

    public void onEventMainThread(BaseJson bean) {
        //获取进入界面的数据
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            WeiXinRePay weiXinRePay = (WeiXinRePay) bean;
            toWeixinPay(weiXinRePay);
            cancelDialog();
        }
    }

    //注册微信支付平台
    private static final String WENXI_APP_ID = "wxfc835c6ebff9d032";
    private IWXAPI api;

    private void regToWx() {
        api = WXAPIFactory.createWXAPI(mContext, WENXI_APP_ID, false);
        api.registerApp(WENXI_APP_ID);
    }

    private void toWeixinPay(WeiXinRePay weiXinRePay) {
        try {
            boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
            Toast.makeText(mContext, String.valueOf(isPaySupported), Toast.LENGTH_SHORT).show();
            PayReq req = new PayReq();
            req.appId = weiXinRePay.getAppid();
            req.partnerId = weiXinRePay.getPartnerid();
            req.prepayId = weiXinRePay.getPrepayid();
            req.nonceStr = weiXinRePay.getNoncestr();
            req.timeStamp = weiXinRePay.getTimestamp();
            req.packageValue = weiXinRePay.getPackageX();
            req.sign = weiXinRePay.getSign();
            Toast.makeText(mContext, "正常调起支付", Toast.LENGTH_SHORT).show();
            api.sendReq(req);
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
            ((ImageView) view.findViewById(oldCheckId)).setImageResource(R.mipmap.cnb_wode_nor);
            ((ImageView) view.findViewById(newCheckId)).setImageResource(R.mipmap.cnb_wode_pre);
        }
        oldCheckId = id;
    }

}
