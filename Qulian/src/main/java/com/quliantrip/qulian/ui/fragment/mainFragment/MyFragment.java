package com.quliantrip.qulian.ui.fragment.mainFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;
import com.quliantrip.qulian.view.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yuly on 2015/11/9.
 */

public class MyFragment extends BaseFragment {
    //用户的头像
    @Bind(R.id.tv_me_userImage)
    CircleImageView userImage;
    //用户的名称
    @Bind(R.id.tv_me_username)
    TextView userName;

    private Bundle bundle = new Bundle();
    private View view;

    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.fragment_main_me, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 2) {
        }
    }

    //加载显示数据
    @Override
    public void initDate() {

    }

    //设置
    @OnClick(R.id.iv_me_seeting)
    void showMySetting() {
        UIHelper.showMeSetting(mContext);
    }

    //登录界面
    @OnClick(R.id.ll_user_loading)
    void showMylogin() {
        UIHelper.showMyLogin(mContext);
    }

    //我的收藏
    @OnClick(R.id.ll_me_collect)
    void showCollect() {
        ToastUtil.showToast(mContext, "我的收藏");
    }

    //我的积分
    @OnClick(R.id.ll_me_integral)
    void showIntegral() {
        UIHelper.showIntegral(mContext,null);
    }

    //我的订单
    @OnClick(R.id.ll_me_orderList)
    void showOrderList() {
        ToastUtil.showToast(mContext, "我的订单");
    }

    //常用联系人
    @OnClick(R.id.ll_me_offen_lianxiren)
    void showOffLianxiren() {
        ToastUtil.showToast(mContext, "常用联系人");
    }

    //我的优惠券
    @OnClick(R.id.ll_me_youhuijuan)
    void showYouhuijuan() {
        ToastUtil.showToast(mContext, "我的优惠券");
    }

    //我的优惠券
    @OnClick(R.id.ll_me_fankui)
    void showFankui() {
        ToastUtil.showToast(mContext, "意见反馈");
    }
}
