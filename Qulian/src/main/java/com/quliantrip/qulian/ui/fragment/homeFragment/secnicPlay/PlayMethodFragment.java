package com.quliantrip.qulian.ui.fragment.homeFragment.secnicPlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.homeAdapter.SearchPlayMethodListAdapter;
import com.quliantrip.qulian.domain.home.SecnicPlayResultBean;
import com.quliantrip.qulian.ui.activity.choiceActivity.PlayMethodDetailActivity;
import com.quliantrip.qulian.util.CommonHelp;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PlayMethodFragment extends Fragment {
    private Context mContext;
    private List<SecnicPlayResultBean.DataEntity.RuleEntity> rule;
    private View view;
    @Bind(R.id.lv_home_play_method_fragment_list)
    ListView listView;
    @Bind(R.id.rl_pager_empty)
    RelativeLayout empty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rule = ((SecnicPlayResultBean.DataEntity) getArguments().getSerializable("data")).getRule();
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_home_play_method, null);
        ButterKnife.bind(this, view);
        iniiListView();
        return view;
    }

    private void iniiListView() {
        if (rule.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            SearchPlayMethodListAdapter searchPlayMethodListAdapter = new SearchPlayMethodListAdapter((ArrayList<SecnicPlayResultBean.DataEntity.RuleEntity>) rule);
            listView.setAdapter(searchPlayMethodListAdapter);
            listView.setDivider(new ColorDrawable(Color.WHITE));
            listView.setDividerHeight(CommonHelp.dip2px(mContext, 10));
            //条目点击事件
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SecnicPlayResultBean.DataEntity.RuleEntity bean = rule.get(position);
                    Intent intent = new Intent(mContext, PlayMethodDetailActivity.class);
                    intent.putExtra("playMethodId", bean.getId());
                    intent.putExtra("isCollect", false);
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
                }
            });
        }
    }
}
