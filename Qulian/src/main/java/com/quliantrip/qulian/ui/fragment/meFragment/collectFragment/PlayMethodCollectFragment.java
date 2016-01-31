package com.quliantrip.qulian.ui.fragment.meFragment.collectFragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.PlayMethodCollectListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodBean;
import com.quliantrip.qulian.domain.me.PlayCollectListBean;
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

/**
 * 玩法收藏列表
 */
public class PlayMethodCollectFragment extends BasePageCheckFragment {
    private View view;
    private PlayMethodCollectListAdapter test;
    private ArrayList<com.quliantrip.qulian.domain.Test> list;

    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView refreshViewList;
    protected ListView listView;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_me_play_method_order, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key","-14KirwNmSQQCMiuYBEXtJBWLllbs7Ma");
        return new QuestBean(map, new PlayCollectListBean().setTag(getClass().getName()), HttpConstants.ME_COLLECT_PLAY_METHOD_LIST);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            PlayCollectListBean playCollectListBean = (PlayCollectListBean) bean;
            if (playCollectListBean.getCode() == 200){
                initRefreshListView(playCollectListBean.getData());
            }else {
                ToastUtil.showToast(mContext,playCollectListBean.getMsg());
            }
        }
    }



    public void setEdit(boolean b) {
        test.setEdit(b);
        test.notifyDataSetChanged();
        if (b) {
            //条目单击事件
            listView.setOnItemClickListener(null);
            //条目长按事件
            listView.setOnItemLongClickListener(null);
        } else {
            //条目单击事件
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //这里下拉刷新头是占一条的
                    ToastUtil.showToast(mContext, list.get(position - 1).getName());
                }
            });
            //条目长按事件
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    ToastUtil.showToast(mContext, "长按" + list.get(position - 1));
                    return true;
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (test != null)
            test.notifyDataSetChanged();
    }

    private void initRefreshListView(List<PlayCollectListBean.DataEntity> listBean) {
        // 设置PullToRefu的mode
        refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
        listView = refreshViewList.getRefreshableView();
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 给listView添加一个设置透明背景。
        list = new ArrayList<com.quliantrip.qulian.domain.Test>();
        int i;
        for (i = 0; i <= 30; i++)
            list.add(new com.quliantrip.qulian.domain.Test(false, "playMethod" + i));
        test = new PlayMethodCollectListAdapter((ArrayList<PlayCollectListBean.DataEntity>) listBean, mContext);
        listView.setAdapter(test);
        //条目单击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这里下拉刷新头是占一条的
                ToastUtil.showToast(mContext, list.get(position - 1).getName());
            }
        });
        //条目长按事件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showToast(mContext, "玩法长按" + list.get(position - 1).getName());
                return true;
            }
        });

        //这里是为了让滑动时显示出来数据
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int currentItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (Math.abs(getScrollY() - currentItem) > 5 && firstVisibleItem < (totalItemCount - visibleItemCount))
                    test.notifyDataSetChanged();
                currentItem = getScrollY();
            }

            public int getScrollY() {
                View c = listView.getChildAt(0);
                if (c == null) {
                    return 0;
                }
                int firstVisiblePosition = listView.getFirstVisiblePosition();
                int top = c.getTop();
                return -top + firstVisiblePosition * c.getHeight();
            }

        });

        // 进行数据时的适配和是上啦还是下拉的操作
        refreshViewList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                // 根据不同的mode进行操作,mode中有要进行操作的类型的数据
                if (refreshViewList.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                    // 这里的请求数据是在子线程中，
                    CommonHelp.runOnUIThread(new Runnable() {

                        @Override
                        public void run() {
                            test.notifyDataSetChanged();
                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                } else {
                    CommonHelp.runOnUIThread(new Runnable() {

                        @Override
                        public void run() {
                            test.notifyDataSetChanged();
                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                }
            }
        });
    }
}