package com.quliantrip.qulian.ui.fragment.homeFragment;

import android.view.View;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.homeAdapter.SearckConditionHotWordAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.CityListBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.view.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SecnicPlayConditionFragment extends BasePageCheckFragment {
    @Bind(R.id.mgv_secnic_play_condition)
    MyGridView hotWord;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_home_secnic_condition, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("东京");
        list.add("亲子乐园餐");
        list.add("富士山");
        list.add("浅草");
        list.add("东京线路");
        list.add("民俗特色");
        list.add("休闲");
        SearckConditionHotWordAdapter searckConditionHotWordAdapter = new SearckConditionHotWordAdapter(list,mContext);
        hotWord.setAdapter(searckConditionHotWordAdapter);
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ctl", "city");
        map.put("r_type", "1");
        return new QuestBean(map, new CityListBean().setTag(getClass().getName()), HttpConstants.HOST_ADDR_ROOT_NET);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {

    }

}
