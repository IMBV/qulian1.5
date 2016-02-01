package com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.playMethod;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.PlayMethodOrderDetailListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.me.GoodOrderListBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.CommonHelp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的订单详情页
 */
public class PlayMethodOrderDetailFragment extends BasePageCheckFragment {
    private View view;
    @Bind(R.id.lv_play_method_good_detail_list)
    ListView listView;


    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_me_play_method_detail_order, null);
        ButterKnife.bind(this, view);
        initListView();
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", "-14KirwNmSQQCMiuYBEXtJBWLllbs7Ma");
        return new QuestBean(map, new GoodOrderListBean().setTag(getClass().getName()), HttpConstants.ME_ORDER_GOOD_LIST);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {

    }

    private void initListView() {
        final ArrayList<String> list = new ArrayList<String>();
        int i;
        for (i = 0; i <= 5; i++)
            list.add("asdf" + i);

        final PlayMethodOrderDetailListAdapter playMethodOrderDetailListAdapter = new PlayMethodOrderDetailListAdapter(list);
        listView.setAdapter(playMethodOrderDetailListAdapter);
        listView.setDivider(new ColorDrawable(CommonHelp.getColor(R.color.app_main_bg)));
        listView.setDividerHeight(CommonHelp.dip2px(mContext, 10));
    }

}
