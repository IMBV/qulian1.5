package com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.playMethod;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.PlayMethodOrderDetailListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.me.PlayMethodOrderDetailBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的玩法订单详情页
 */
public class PlayMethodOrderDetailFragment extends BasePageCheckFragment {
    private View view;
    private String orderId;

    @Bind(R.id.lv_play_method_good_detail_list)
    ListView listView;

    private PlayMethodOrderDetailListAdapter playMethodOrderDetailListAdapter;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_me_play_method_detail_order, null);
        ButterKnife.bind(this, view);
        orderId = getArguments().getString("orderId");
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderid", orderId);
        return new QuestBean(map, new PlayMethodOrderDetailBean().setTag(getClass().getName()), HttpConstants.ME_ORDER_PLAY_METHOD_DETAIL);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            PlayMethodOrderDetailBean playMethodOrderDetailBean = (PlayMethodOrderDetailBean) bean;
            if (playMethodOrderDetailBean.getCode() == 200) {
                initListView(playMethodOrderDetailBean.getData());

            } else {
                ToastUtil.showToast(mContext, playMethodOrderDetailBean.getMsg());
            }
        }
    }

    private void initListView(List<PlayMethodOrderDetailBean.DataEntity> list) {
        playMethodOrderDetailListAdapter = new PlayMethodOrderDetailListAdapter((ArrayList<PlayMethodOrderDetailBean.DataEntity>) list);
        listView.setAdapter(playMethodOrderDetailListAdapter);
        listView.setDivider(new ColorDrawable(CommonHelp.getColor(R.color.app_main_bg)));
        listView.setDividerHeight(CommonHelp.dip2px(mContext, 10));
    }

    //点击联系玩法订单联系达人界面
    @OnClick(R.id.tv_play_method_order_detail_link_daren)
    void linkpalymethodDaren() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        View view = View.inflate(mContext, R.layout.layout_find_into_qun_grounp, null);
        dialog = builder.create();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();

    }
}
