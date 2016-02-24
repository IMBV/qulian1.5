package com.quliantrip.qulian.ui.fragment.meFragment.collectFragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.PlayMethodCollectListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodBean;
import com.quliantrip.qulian.domain.me.PlayCollectListBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.choiceActivity.PlayMethodDetailActivity;
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
    private PlayMethodCollectListAdapter playMethodCollectListAdapter;
    private List<PlayCollectListBean.DataEntity> listPlayMethod;

    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView refreshViewList;
    protected ListView listView;

    @Bind(R.id.rl_pager_empty)
    RelativeLayout empty;//空的显示界面


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
        return new QuestBean(map, new PlayCollectListBean().setTag(getClass().getName()), HttpConstants.ME_COLLECT_PLAY_METHOD_LIST);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            PlayCollectListBean playCollectListBean = (PlayCollectListBean) bean;
            if (playCollectListBean.getCode() == 200){
                listPlayMethod = playCollectListBean.getData();
                initRefreshListView(playCollectListBean.getData());
            }else {
                ToastUtil.showToast(mContext,playCollectListBean.getMsg());
            }
        }
    }

    public void setEdit(boolean b) {
        playMethodCollectListAdapter.setEdit(b);
        playMethodCollectListAdapter.notifyDataSetChanged();
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
        if (playMethodCollectListAdapter != null)
            playMethodCollectListAdapter.notifyDataSetChanged();
    }


    public void removeAllDelect(ArrayList<PlayCollectListBean.DataEntity> list){
        playMethodCollectListAdapter.removeAll(list);
    }

    private void initRefreshListView(List<PlayCollectListBean.DataEntity> listBean) {
        if (listPlayMethod.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            refreshViewList.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            refreshViewList.setVisibility(View.VISIBLE);
            // 设置PullToRefu的mode
            refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
            listView = refreshViewList.getRefreshableView();
            listView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 给listView添加一个设置透明背景。
            playMethodCollectListAdapter = new PlayMethodCollectListAdapter((ArrayList<PlayCollectListBean.DataEntity>) listBean, mContext);
            listView.setAdapter(playMethodCollectListAdapter);
            //条目单击事件
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //这里下拉刷新头是占一条的
                    PlayCollectListBean.DataEntity bean = listPlayMethod.get(position - 1);
                    Intent intent = new Intent(mContext, PlayMethodDetailActivity.class);
                    intent.putExtra("playMethodId", bean.getId());
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
                }
            });

            //条目长按事件
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                ToastUtil.showToast(mContext, "玩法长按" + list.get(position - 1).getName());
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
                    if (Math.abs(getScrollY() - currentItem) > 10 && firstVisibleItem < (totalItemCount - visibleItemCount))
                        playMethodCollectListAdapter.notifyDataSetChanged();
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

            refreshViewList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

                @Override
                public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                    // 根据不同的mode进行操作,mode中有要进行操作的类型的数据
                    if (refreshViewList.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                        // 这里的请求数据是在子线程中，
                        CommonHelp.runOnUIThread(new Runnable() {

                            @Override
                            public void run() {
                                playMethodCollectListAdapter.notifyDataSetChanged();
                                refreshViewList.onRefreshComplete();
                            }
                        }, 500);
                    } else {
                        CommonHelp.runOnUIThread(new Runnable() {

                            @Override
                            public void run() {
                                playMethodCollectListAdapter.notifyDataSetChanged();
                                refreshViewList.onRefreshComplete();
                            }
                        }, 500);
                    }
                }
            });
        }
    }
}