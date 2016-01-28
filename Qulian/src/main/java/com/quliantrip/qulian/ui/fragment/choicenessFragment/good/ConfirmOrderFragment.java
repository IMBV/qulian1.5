package com.quliantrip.qulian.ui.fragment.choicenessFragment.good;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.common.HintInfoBean;
import com.quliantrip.qulian.domain.me.LinkManBean;
import com.quliantrip.qulian.domain.me.LoginDataBean;
import com.quliantrip.qulian.domain.choice.good.GoodOrderConfirmBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 确认订单
 */
public class ConfirmOrderFragment extends BasePageCheckFragment {
    private View view;

    @Bind(R.id.tv_link_nam_name)
    TextView name;
    @Bind(R.id.tv_link_nam_pinying_name)
    TextView pyName;
    @Bind(R.id.tv_link_man_phone)
    TextView phone;
    @Bind(R.id.tv_link_nam_email)
    TextView email;
    @Bind(R.id.tv_order_confirm_name)
    TextView orderName;
    @Bind(R.id.tv_order_confirm_taocan)
    TextView taocan;
    @Bind(R.id.tv_order_confirm_data)
    TextView data;
    @Bind(R.id.tv_order_confirm_time)
    TextView time;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_confirm_order, null);
        ButterKnife.bind(this, view);
        LoginDataBean loginDataBean = QulianApplication.getInstance().getLoginUser();
        name.setText(loginDataBean.getUsername());
        phone.setText(loginDataBean.getMobile());
        email.setText(loginDataBean.getEmail());
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "11");
        return new QuestBean(map, new GoodOrderConfirmBean().setTag(getClass().getName()), HttpConstants.GOOD_ORDER_CONFIRM);
    }

    @OnClick(R.id.ll_checked_linkman)
    void intoCheckedLinkman() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isItemOnclick", true);
        UIHelper.showMyCommonInfo(mContext, this, 241, bundle);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        LinkManBean.LinkMan bean = (LinkManBean.LinkMan) data.getSerializableExtra("linkMan");
        name.setText(bean.getName());
        pyName.setText(bean.getPyname());
        phone.setText(QulianApplication.getInstance().getLoginUser().getUsername());
        email.setText(QulianApplication.getInstance().getLoginUser().getEmail());
    }

    //跳入到支付界面
    @OnClick(R.id.bt_order_payment)
    void toPay() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderid", "76");//订单的id
        map.put("memo", "备注备注备注");//备注
        map.put("contactsid", "3");//常用联系人的id
        new PacketStringReQuest(HttpConstants.GOOD_ORDER_CONFIRM_SUBMIT,
                new HintInfoBean().setTag(getClass().getName() + "topay"), map);
//        UIHelper.showPayMethod(mContext, null);
//        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }


    @Override
    public void onEventMainThread(BaseJson bean) {
        //初始化界面的显示
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            GoodOrderConfirmBean goodOrderConfirmBean = (GoodOrderConfirmBean) bean;
            if (goodOrderConfirmBean.getCode() == 200){
                GoodOrderConfirmBean.DataEntity dataEntity = goodOrderConfirmBean.getData();
                name.setText(dataEntity.getOrdershop().getName());
                taocan.setText(dataEntity.getAttribute());
                data.setText(dataEntity.getOrdershop().getDate());
                time.setText("没有该字段");
            }else{
                ToastUtil.showToast(mContext,goodOrderConfirmBean.getMsg());
            }
        }

        //确认订单后提交成功后悔跳去的支付界面
        if (bean != null && (this.getClass().getName() + "topay").equals(bean.getTag())) {
            HintInfoBean hintInfoBean = (HintInfoBean) bean;
            if (hintInfoBean.getCode() == 200){
                UIHelper.showPayMethod(mContext, null);
                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
            }else{
                ToastUtil.showToast(mContext,hintInfoBean.getMsg());
            }
        }
    }
}
