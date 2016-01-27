package com.quliantrip.qulian.ui.fragment.choicenessFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.PlayMethodConfirmOrderAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.LinkManBean;
import com.quliantrip.qulian.domain.LoginDataBean;
import com.quliantrip.qulian.domain.choice.good.GoodOrderConfirmBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMehtodOrderConfirmBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
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
        map.put("orderid", "59");
        return new QuestBean(map, new PlayMehtodOrderConfirmBean().setTag(getClass().getName()), HttpConstants.PLAY_METHOD_ORDER_CONFIRM);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            PlayMehtodOrderConfirmBean playMehtodOrderConfirmBean = (PlayMehtodOrderConfirmBean) bean;
            if (playMehtodOrderConfirmBean.getCode() == 200){
                List<PlayMehtodOrderConfirmBean.DataEntity> dataEntity = playMehtodOrderConfirmBean.getData();
                listView.setAdapter(new PlayMethodConfirmOrderAdapter((ArrayList<PlayMehtodOrderConfirmBean.DataEntity>) dataEntity));
            }else{
                ToastUtil.showToast(mContext,playMehtodOrderConfirmBean.getMsg());
            }
        }
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
        UIHelper.showPayMethod(mContext, null);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }
}
