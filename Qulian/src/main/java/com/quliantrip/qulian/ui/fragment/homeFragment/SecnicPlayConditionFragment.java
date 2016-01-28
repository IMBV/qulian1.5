package com.quliantrip.qulian.ui.fragment.homeFragment;

import android.text.TextUtils;
import android.view.View;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.homeAdapter.SearchConditionHistoryAdapter;
import com.quliantrip.qulian.adapter.homeAdapter.SearckConditionHotWordAdapter;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.view.MyGridView;
import com.quliantrip.qulian.view.MyListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SecnicPlayConditionFragment extends BaseFragment {
    private View view;
    @Bind(R.id.mgv_secnic_play_condition)
    MyGridView hotWord;
    @Bind(R.id.mlv_secnic_play_hosity_list)
    MyListView myListView;//历史记录

    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.fragment_home_secnic_condition, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initDate() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("东京");
        list.add("亲子乐园餐");
        list.add("富士山");
        list.add("浅草");
        list.add("东京线路");
        list.add("民俗特色");
        list.add("休闲");
        SearckConditionHotWordAdapter searckConditionHotWordAdapter = new SearckConditionHotWordAdapter(list, mContext);
        hotWord.setAdapter(searckConditionHotWordAdapter);

        ArrayList<String> listHistory = new ArrayList<>();
        String hotWordListString = CommonHelp.getStringSp(mContext, "hotWordString", "");
        if (!TextUtils.isEmpty(hotWordListString)) {
            String[] stringList = hotWordListString.split("::");
            for (String s:stringList) {
                listHistory.add(s);
            }
        }
        myListView.setAdapter(new SearchConditionHistoryAdapter(listHistory,mContext));
    }

}
