package com.quliantrip.qulian.ui.fragment.homeFragment.secnicPlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.homeAdapter.SearchHotGoodListAdapter;
import com.quliantrip.qulian.domain.home.SecnicPlayResultBean;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 单品搜索结果
 */
public class LocalPlayFragment extends Fragment {
    private Context mContext;
    private View view;
    @Bind(R.id.lv_home_local_play_fragment_list)
    ListView listView;
    @Bind(R.id.rl_pager_empty)
    RelativeLayout empty;
    List<SecnicPlayResultBean.DataEntity.WareEntity> ware;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ware = ((SecnicPlayResultBean.DataEntity) getArguments().getSerializable("data")).getWare();
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_home_local_play, null);
        ButterKnife.bind(this, view);
        initListView();
        return view;
    }

    private void initListView() {
        if (ware.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            SearchHotGoodListAdapter searchHotGoodListAdapter = new SearchHotGoodListAdapter((ArrayList<SecnicPlayResultBean.DataEntity.WareEntity>) ware);
            listView.setAdapter(searchHotGoodListAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SecnicPlayResultBean.DataEntity.WareEntity bean = ware.get(position);
                    Intent intent = new Intent(mContext, GoodDetailActivity.class);
                    intent.putExtra("goodId", bean.getId());
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
                }
            });
        }
    }


}
