package com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.good;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.GoodOrderListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.common.HintInfoBean;
import com.quliantrip.qulian.domain.me.GoodOrderListBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的订单详列表页面
 */
public class GoodOrderFragment extends BasePageCheckFragment {
    private View view;
    //请求筛选的参数
    private String type;

    //下来刷新的列表页
    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView refreshViewList;
    protected ListView listView;
    @Bind(R.id.rl_pager_empty)
    RelativeLayout empty;//为空时显示的界面

    private GoodOrderListAdapter goodOrderListAdapter;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_me_order_good, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
        return new QuestBean(map, new GoodOrderListBean().setTag(getClass().getName()), HttpConstants.ME_ORDER_GOOD_LIST);
    }

    //所有带参数带有数据的请求
    public void requestDataForAll() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
        if (type != null)
            map.put("type", type);
        new PacketStringReQuest(HttpConstants.ME_ORDER_GOOD_LIST, new GoodOrderListBean().setTag(GoodOrderFragment.this.getClass().getName()), map);
    }
    private ArrayList<GoodOrderListBean.DataEntity> dataEntity;

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            GoodOrderListBean goodOrderListBean = (GoodOrderListBean) bean;
            if (goodOrderListBean.getCode() == 200) {

                dataEntity = (ArrayList<GoodOrderListBean.DataEntity>) goodOrderListBean.getData();
                initRefreshListView(dataEntity);
            } else {
                ToastUtil.showToast(mContext, goodOrderListBean.getMsg());
            }
        }

        //进行取消订单的操作
        if (bean != null && (this.getClass().getName() + "delOrder").equals(bean.getTag())) {
            HintInfoBean hintInfoBean = (HintInfoBean) bean;
            if (hintInfoBean.getCode() == 200) {
                ToastUtil.showToast(mContext, hintInfoBean.getMsg());
                requestDataForAll();
            } else {
                ToastUtil.showToast(mContext, hintInfoBean.getMsg());
            }
        }
    }

    //设置下来刷新
    private void initRefreshListView(final ArrayList<GoodOrderListBean.DataEntity> list) {
        if (list.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            refreshViewList.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            refreshViewList.setVisibility(View.VISIBLE);
            //设置数据的下来刷新的mode
            refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
            listView = refreshViewList.getRefreshableView();
            listView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 给listView添加一个设置透明背景。

            if (goodOrderListAdapter == null) {
                goodOrderListAdapter = new GoodOrderListAdapter(list, mContext);
                listView.setAdapter(goodOrderListAdapter);
                listView.setDivider(new ColorDrawable(CommonHelp.getColor(R.color.app_main_bg)));
                listView.setDividerHeight(CommonHelp.dip2px(mContext, 10));
            } else {
                goodOrderListAdapter.updataList(list);
            }

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    bundle.putString("orderId", list.get(position - 1).getId());
                    UIHelper.showMyOrderDetail(mContext, bundle);
                }
            });
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) refreshViewList.getLayoutParams();
            params.topMargin = CommonHelp.dip2px(mContext, 10);
            refreshViewList.setLayoutParams(params);

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
                                goodOrderListAdapter.notifyDataSetChanged();
                                refreshViewList.onRefreshComplete();
                            }
                        }, 500);
                    } else {
                        CommonHelp.runOnUIThread(new Runnable() {

                            @Override
                            public void run() {
                                goodOrderListAdapter.notifyDataSetChanged();
                                refreshViewList.onRefreshComplete();
                            }
                        }, 500);
                    }
                }
            });
        }
    }

    //进行点击后筛选的数据适配,全部
    @OnClick(R.id.rb_me_order_good_all)
    void allOrder() {
        this.type = "all";
        requestDataForAll();
    }

    //未付款
    @OnClick(R.id.rb_me_order_good_to_pay)
    void toPayOrder() {
        this.type = "npay";
        requestDataForAll();
    }

    //待确认
    @OnClick(R.id.rb_me_order_good_to_confirmed)
    void toConfirmedOrder() {
        this.type = "check";
        requestDataForAll();
    }

    //已确认
    @OnClick(R.id.rb_me_order_good_confirmed)
    void confirmedOrder() {
        this.type = "nuse";
        requestDataForAll();
    }

    //已使用
    @OnClick(R.id.rb_me_order_good_user)
    void userOrder() {
        this.type = "end";
        requestDataForAll();
    }
}

//    //设置选择框内的数量的显示为黄色，暂时没有作用
//    public void setOrderNumberCollor(RadioButton rb, int number) {
//        String s = rb.getText().toString();
//        int oldLenght = chineseNums(s);
//        String num = "(" + number + ")";
//        int endNumber = num.getBytes().length;
//        SpannableString styledText = new SpannableString(s + num);
//        styledText.setSpan(new TextAppearanceSpan(mContext, R.style.text_end_yellow), oldLenght, oldLenght + endNumber, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        rb.setText(styledText, TextView.BufferType.SPANNABLE);
//    }
//
//    //获取支付串的中文的个数
//    private static int chineseNums(String str) {
//        byte b[] = str.getBytes();
//        int byteLength = b.length;
//        int strLength = str.length();
//        return (byteLength - strLength) / 2;
//    }
