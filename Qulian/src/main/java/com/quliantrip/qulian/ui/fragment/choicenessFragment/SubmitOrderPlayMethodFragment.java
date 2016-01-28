package com.quliantrip.qulian.ui.fragment.choicenessFragment;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.playMethod.PlayMethodOrderGoodlistAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.playMethod.OrderSubmitResultBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodOrderSubmitBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 玩法的订单
 */
public class SubmitOrderPlayMethodFragment extends BasePageCheckFragment {
    @Bind(R.id.lv_play_method_good_check_list)
    ListView listView;


    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_choice_play_method_order_check, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        return new QuestBean(map, new PlayMethodOrderSubmitBean().setTag(getClass().getName()), HttpConstants.PLAY_METHOD_ORDER);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            PlayMethodOrderSubmitBean playMethodOrderSubmitBean = (PlayMethodOrderSubmitBean) bean;
            if (playMethodOrderSubmitBean.getCode() == 200) {
                listView.setAdapter(new PlayMethodOrderGoodlistAdapter((ArrayList<PlayMethodOrderSubmitBean.DataEntity>) playMethodOrderSubmitBean.getData(), mContext));
                listView.setDivider(new ColorDrawable(CommonHelp.getColor(R.color.app_main_bg)));
                listView.setDividerHeight(CommonHelp.dip2px(mContext, 10));
            } else {
                ToastUtil.showToast(mContext, playMethodOrderSubmitBean.getMsg());
            }
        }
//        if (bean != null && (this.getClass().getName() + "check").equals(bean.getTag())) {
//            GoodOrderSubmitCheckBean goodOrserSubmitCheckBean = (GoodOrderSubmitCheckBean) bean;
//            if (goodOrserSubmitCheckBean.getCode() == 200) {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("proid", "11");
//                map.put("date", "2/12/2015");
//                map.put("key", "-14KirwNmSQQCMiuYBEXtJBWLllbs7Ma");
//                map.put("total_price", "11111");
//                map.put("num", "3");
//                map.put("type", "1");
//                map.put("price", "1111");
//                map.put("service", "11:30");
//                map.put("sku_id", "15");
//                new PacketStringReQuest(HttpConstants.GOOD_ORDER_SUBMIT, new GoodOrderSubmitBean().setTag(getClass().getName() + "submit"), map);
//            } else {
//                ToastUtil.showToast(mContext, goodOrserSubmitCheckBean.getMsg());
//            }
//        }
//        if (bean != null && (this.getClass().getName() + "submit").equals(bean.getTag())) {
//            GoodOrderSubmitBean goodOrderSubmitBean = (GoodOrderSubmitBean) bean;
//            if (goodOrderSubmitBean.getCode() == 200) {
//                Bundle bundle =  new Bundle();
//                bundle.putString("orderId",goodOrderSubmitBean.getData().getId()+"");
//                UIHelper.showOrderConfirm(mContext, bundle);
//                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
//            } else {
//                ToastUtil.showToast(mContext, goodOrderSubmitBean.getMsg());
//            }
//        }
    }

    //提交订单
    @OnClick(R.id.bt_order_submi_topay)
    void toConfirmOrder() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("proid", "1");
        map.put("key","2/12/2015");
        map.put("total_price","12");
        map.put("type","15");
        map.put("map","15");
        new PacketStringReQuest(HttpConstants.PLAY_METHOD_ORDER_SUBMIT, new OrderSubmitResultBean().setTag(getClass().getName() + "check"), map);
//        Bundle bundle = new Bundle();
//        bundle.putString("orderId", "53");
//        UIHelper.showPlayMethodOrderConfirm(mContext, bundle);
//        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }


}