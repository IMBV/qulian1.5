package com.quliantrip.qulian.ui.fragment.choicenessFragment.playMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.playMethod.PlayMethodConfirmOrderAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMehtodOrderConfirmBean;
import com.quliantrip.qulian.domain.common.HintInfoBean;
import com.quliantrip.qulian.domain.me.LinkManBean;
import com.quliantrip.qulian.domain.me.LoginDataBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.PayCheckstandFragment;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 玩法确认订单
 */
public class PlayMethodConfirmOrderFragment extends BasePageCheckFragment {
    private View view;
    private String orderId;
    private String linkManId;

    @Bind(R.id.lv_confirm_good_list)
    ListView listView;

    @Bind(R.id.tv_link_nam_name)
    TextView name;
    @Bind(R.id.tv_link_nam_pinying_name)
    TextView pyName;
    @Bind(R.id.tv_link_man_phone)
    TextView phone;
    @Bind(R.id.tv_link_nam_email)
    TextView email;

    //备注
    @Bind(R.id.et_order_beizhu_text)
    EditText beizhu;

    //显示价格
    @Bind(R.id.iv_order_total_prices)
    TextView totalPrice;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_confirm_play_method_order, null);
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
        orderId = getArguments().getString("orderId");
        map.put("orderid",orderId);
        return new QuestBean(map, new PlayMehtodOrderConfirmBean().setTag(getClass().getName()), HttpConstants.PLAY_METHOD_ORDER_CONFIRM);
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
        String beizhuString = beizhu.getText().toString().trim();
        if(linkManId  == null || TextUtils.isEmpty(linkManId)){
            ToastUtil.showToast(mContext,"请选着联系人");
            return;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderid", orderId);//订单的id
        map.put("memo", beizhuString);//备注
        map.put("contactsid", linkManId);//常用联系人的id
        new PacketStringReQuest(HttpConstants.PLAY_METHOD_ORDER_CONFIRM_SUBMIT,new HintInfoBean().setTag(getClass().getName() + "topay"), map);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        //初始化界面的展示
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            PlayMehtodOrderConfirmBean playMehtodOrderConfirmBean = (PlayMehtodOrderConfirmBean) bean;
            if (playMehtodOrderConfirmBean.getCode() == 200){
                List<PlayMehtodOrderConfirmBean.DataEntity.PlayorderEntity> dataEntity = playMehtodOrderConfirmBean.getData().getPlayorder();
                listView.setAdapter(new PlayMethodConfirmOrderAdapter((ArrayList<PlayMehtodOrderConfirmBean.DataEntity.PlayorderEntity>) dataEntity));
                totalPrice.setText(playMehtodOrderConfirmBean.getData().getTotal_price());
            }else{
                ToastUtil.showToast(mContext,playMehtodOrderConfirmBean.getMsg());
            }
        }

        //确认订单后提交成功后悔跳去的支付界面
        if (bean != null && (this.getClass().getName() + "topay").equals(bean.getTag())) {
            HintInfoBean hintInfoBean = (HintInfoBean) bean;
            if (hintInfoBean.getCode() == 200){
                Intent intent = new Intent(mContext,PayCheckstandFragment.class);
                intent.putExtra("totalPrice",totalPrice.getText().toString());
                intent.putExtra("orderId",getArguments().getString("orderSn"));
                mContext.startActivity(intent);
                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
            }else{
                ToastUtil.showToast(mContext,hintInfoBean.getMsg());
            }
        }
    }
}
