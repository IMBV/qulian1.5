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
import butterknife.OnClick;

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
        list.add("米其林");
        list.add("河豚");
        list.add("帝王蟹");
        list.add("迪士尼");
        list.add("居酒屋");
        list.add("烤肉");
        list.add("晴空塔");
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

    @OnClick(R.id.tv_search_condition)
    void clearHistory(){
        
    }
}
