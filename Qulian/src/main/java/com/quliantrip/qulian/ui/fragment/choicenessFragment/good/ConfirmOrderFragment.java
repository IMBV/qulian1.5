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

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private String orderId;
    private String linkManId;

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
    @Bind(R.id.iv_order_total_prices)
    TextView price;//价格
    @Bind(R.id.et_good_order_beizhu)
    TextView beizhu;//

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
        orderId = getArguments().getString("orderId");
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderid", orderId);
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
        linkManId = bean.getId();
        name.setText(bean.getName());
        pyName.setText(bean.getPyname());
        phone.setText(QulianApplication.getInstance().getLoginUser().getUsername());
        email.setText(QulianApplication.getInstance().getLoginUser().getEmail());
    }

    //跳入到支付界面
    @OnClick(R.id.bt_order_payment)
    void toPay() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderid", orderId);//订单的id
        map.put("memo", beizhu.getText().toString().trim());//备注
        map.put("contactsid", linkManId);//常用联系人的id
        new PacketStringReQuest(HttpConstants.GOOD_ORDER_CONFIRM_SUBMIT,new HintInfoBean().setTag(getClass().getName() + "topay"), map);
//        UIHelper.showPayMethod(mContext, null);
//        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void onEventMainThread(BaseJson bean) {
        //初始化界面的显示
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            GoodOrderConfirmBean goodOrderConfirmBean = (GoodOrderConfirmBean) bean;
            if (goodOrderConfirmBean.getCode() == 200){
                GoodOrderConfirmBean.DataEntity dataEntity = goodOrderConfirmBean.getData();
                orderName.setText(dataEntity.getOrdershop().getName());
                taocan.setText(dataEntity.getAttribute());
                data.setText(sdf.format(new Date(Integer.valueOf(dataEntity.getOrdershop().getDate()) * 1000)));
                time.setText("没有该字段");
                price.setText("￥"+dataEntity.getOrdershop().getPrice());
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
