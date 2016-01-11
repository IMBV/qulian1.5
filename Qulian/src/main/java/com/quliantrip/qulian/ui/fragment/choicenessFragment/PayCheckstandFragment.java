package com.quliantrip.qulian.ui.fragment.choicenessFragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.util.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Qulian5 on 2016/1/8.
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
        return view;
    }

    @Override
    public void initDate() {

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
                ToastUtil.showToast(mContext, "微信支付");
                break;
            case 1:
                ToastUtil.showToast(mContext, "支付宝支付");
                break;
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
        System.out.println(oldPayWay+"asdf0"+mCurrentPayWay);
        oldCheckId = id;
    }
}
