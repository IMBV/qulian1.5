package com.quliantrip.qulian.ui.fragment.meFragment.collectFragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

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
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
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
    private List<GoodCollectListBean.DataEntity.HouseEntity> listGood;
    private String cate;

    private int currentCheckedCate = 0;//当前选择的类型
    protected ListView listView;//下来刷新的数据、

    @Bind(R.id.hs_my_good_collect_cate_content)
    LinearLayout linearLayout;
    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView refreshViewList;
    @Bind(R.id.rl_pager_empty)
    RelativeLayout empty;//空界面显示的数据

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_me_good_method_order, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
        return new QuestBean(map, new GoodCollectListBean().setTag(getClass().getName()), HttpConstants.ME_COLLECT_GOOD_LIST);
    }

    //所有参数进行
    public void requestDataForAll() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
        if (cate != null)
            map.put("cate", cate);
        new PacketStringReQuest(HttpConstants.ME_COLLECT_GOOD_LIST, new GoodCollectListBean().setTag(getClass().getName() + "second"), map);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            GoodCollectListBean goodCollectListBean = (GoodCollectListBean) bean;
            if (goodCollectListBean.getCode() == 200) {
                listGood = goodCollectListBean.getData().getHouse();
                for (int i = 0; i < goodCollectListBean.getData().getCate().size(); i++) {
                    initRadioButton(goodCollectListBean.getData().getCate().get(i), i);
                }
                linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        linearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        Button button = (Button) linearLayout.getChildAt(currentCheckedCate).findViewById(R.id.rb_hot_good_classfy_item);
                        button.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
                        button.setBackground(CommonHelp.getDrawable(R.drawable.shape_button_corner_press));
                    }
                });
                initRefreshListView(goodCollectListBean.getData().getHouse());
            } else {
                ToastUtil.showToast(mContext, goodCollectListBean.getMsg());
            }
        }

        if (bean != null && (this.getClass().getName() + "second").equals(bean.getTag())) {
            GoodCollectListBean goodCollectListBean = (GoodCollectListBean) bean;
            if (goodCollectListBean.getCode() == 200) {
                listGood = goodCollectListBean.getData().getHouse();
                initRefreshListView(goodCollectListBean.getData().getHouse());
            } else {
                ToastUtil.showToast(mContext, goodCollectListBean.getMsg());
            }
        }
    }

    //添加选着条件按钮
    private void initRadioButton(final GoodCollectListBean.DataEntity.CateEntity cate, final int i) {
        View view = View.inflate(mContext, R.layout.view_hot_good_classfy_item, null);
        final Button button = (Button) view.findViewById(R.id.rb_hot_good_classfy_item);
        button.setText(cate.getName());
        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (currentCheckedCate != i) {
                    button.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
                    button.setBackground(CommonHelp.getDrawable(R.drawable.shape_button_corner_press));
                    Button oldButton = (Button) linearLayout.getChildAt(currentCheckedCate).findViewById(R.id.rb_hot_good_classfy_item);
                    oldButton.setTextColor(CommonHelp.getColor(R.color.app_main_title_text));
                    oldButton.setBackground(CommonHelp.getDrawable(R.drawable.shape_button_corner_normal));
                }
                currentCheckedCate = i;
                GoodCollectFragment.this.cate = cate.getId();
                requestDataForAll();
            }
        });
        linearLayout.addView(view);
    }

    //设置编辑的状态
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

    //移除删除的集合
    public void removeAllDeleteList(List<GoodCollectListBean.DataEntity.HouseEntity> list) {
        goodCollectListAdapter.removeAll((ArrayList<GoodCollectListBean.DataEntity.HouseEntity>) list);
    }

    private void initRefreshListView(List<GoodCollectListBean.DataEntity.HouseEntity> listBean) {
        if (listBean.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            refreshViewList.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            refreshViewList.setVisibility(View.VISIBLE);
            // 设置PullToRresh的mode类型
            refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
            listView = refreshViewList.getRefreshableView();
            listView.setSelector(new ColorDrawable(Color.TRANSPARENT));

            if (goodCollectListAdapter == null) {
                goodCollectListAdapter = new GoodCollectListAdapter((ArrayList<GoodCollectListBean.DataEntity.HouseEntity>) listBean, mContext);
                listView.setAdapter(goodCollectListAdapter);
            } else {
                goodCollectListAdapter.updataData((ArrayList<GoodCollectListBean.DataEntity.HouseEntity>) listBean);
            }

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) refreshViewList.getLayoutParams();
            params.topMargin = CommonHelp.dip2px(mContext, 10);
            refreshViewList.setLayoutParams(params);
            //条目单击事件
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //这里下拉刷新头是占一条的
                    if (TDevice.getNetworkType() != 0) {
                        GoodCollectListBean.DataEntity.HouseEntity bean = listGood.get(position - 1);
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
                    if ((getScrollY() - currentItem) < 0)
                        if (listView.getFirstVisiblePosition() >= 1)
                            listGood.get(listView.getFirstVisiblePosition() - 1).setIsRefresh(true);
                    if ((getScrollY() - currentItem) > 0)
                        if (listView.getLastVisiblePosition() < listView.getCount() - 1)
                            listGood.get(listView.getLastVisiblePosition() - 1).setIsRefresh(true);
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

            //进行下来或删除的操作
            refreshViewList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

                @Override
                public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                    if (refreshViewList.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                        CommonHelp.runOnUIThread(new Runnable() {

                            @Override
                            public void run() {
                                goodCollectListAdapter.notifyDataSetChanged();
                                refreshViewList.onRefreshComplete();
                            }
                        }, 500);
                    } else {
                        CommonHelp.runOnUIThread(new Runnable() {

                            @Override
                            public void run() {
                                goodCollectListAdapter.notifyDataSetChanged();
                                refreshViewList.onRefreshComplete();
                            }
                        }, 500);
                    }
                }
            });
        }
    }
}

