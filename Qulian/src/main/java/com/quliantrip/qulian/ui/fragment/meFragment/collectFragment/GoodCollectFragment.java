package com.quliantrip.qulian.ui.fragment.meFragment.collectFragment;

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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.GoodCollectListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.good.HotGoodBean;
import com.quliantrip.qulian.domain.me.GoodCollectListBean;
import com.quliantrip.qulian.domain.me.PlayCollectListBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailActivity;
import com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.good.GoodOrderFragment;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.TDevice;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 热门商品的收藏
 */
public class GoodCollectFragment extends BasePageCheckFragment {
    private View view;
    private GoodCollectListAdapter goodCollectListAdapter;
    private List<GoodCollectListBean.DataEntity> listGood;


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
        map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
        map.put("cate","");
        return new QuestBean(map, new GoodCollectListBean().setTag(getClass().getName()), HttpConstants.ME_COLLECT_GOOD_LIST);
    }
    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            GoodCollectListBean goodCollectListBean = (GoodCollectListBean) bean;
            if (goodCollectListBean.getCode() == 200){
                listGood = goodCollectListBean.getData();
                initRefreshListView(goodCollectListBean.getData());
            }else {
                ToastUtil.showToast(mContext,goodCollectListBean.getMsg());
            }
        }
    }

    public void setEdit(boolean b) {
        goodCollectListAdapter.setEdit(b);
        goodCollectListAdapter.notifyDataSetChanged();
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
                }
            });
            //条目长按事件
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    return true;
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (goodCollectListAdapter != null)
            goodCollectListAdapter.notifyDataSetChanged();
    }

    private void initRefreshListView(List<GoodCollectListBean.DataEntity> listBean)  {
        // 设置PullToRefu的mode
        refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
        listView = refreshViewList.getRefreshableView();
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 给listView添加一个设置透明背景。
        goodCollectListAdapter = new GoodCollectListAdapter((ArrayList<GoodCollectListBean.DataEntity>) listBean, mContext);
        listView.setAdapter(goodCollectListAdapter);
        //条目单击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这里下拉刷新头是占一条的
                if (TDevice.getNetworkType() != 0) {
                    GoodCollectListBean.DataEntity bean = listGood.get(position - 1);
                    Intent intent = new Intent(mContext, GoodDetailActivity.class);
                    intent.putExtra("goodId", bean.getId());
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
                } else {
                    ToastUtil.showToast(mContext, "请检查网络设置");
                }
            }
        });
        //条目长按事件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
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
                    goodCollectListAdapter.notifyDataSetChanged();
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
//                            goodCollectListAdapter.addItem(new com.quliantrip.qulian.domain.Test(false, "shang"));
                            goodCollectListAdapter.notifyDataSetChanged();
                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                } else {
                    CommonHelp.runOnUIThread(new Runnable() {

                        @Override
                        public void run() {
//                            goodCollectListAdapter.addItem(new com.quliantrip.qulian.domain.Test(false, "xia"));
                            goodCollectListAdapter.notifyDataSetChanged();
                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                }
            }
        });
    }
}

