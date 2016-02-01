package com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.good;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.GoodOrderListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.me.GoodOrderListBean;
import com.quliantrip.qulian.domain.me.PlayCollectListBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的订单详列表页面
 */
public class GoodOrderFragment extends BasePageCheckFragment {
    private View view;
    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView refreshViewList;
    protected ListView listView;

    //所有
    @Bind(R.id.rb_me_order_good_all)
    RadioButton all;
    //未付款
    @Bind(R.id.rb_me_order_good_to_pay)
    RadioButton toPay;
    //待确认
    @Bind(R.id.rb_me_order_good_to_confirmed)
    RadioButton toConfirmed;
    //已确认
    @Bind(R.id.rb_me_order_good_confirmed)
    RadioButton confirmed;
    //已使用
    @Bind(R.id.rb_me_order_good_user)
    RadioButton user;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_me_order_good, null);
        ButterKnife.bind(this, view);
        setOrderNumberCollor(all, 10);
        setOrderNumberCollor(toPay, 5);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
        map.put("pay_status","");
        map.put("order_status","");
        map.put("is_use","");
        return new QuestBean(map, new GoodOrderListBean().setTag(getClass().getName()), HttpConstants.ME_ORDER_GOOD_LIST);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            GoodOrderListBean GoodOrderListBean = (GoodOrderListBean) bean;
            if (GoodOrderListBean.getCode() == 200) {
                initRefreshListView((ArrayList<com.quliantrip.qulian.domain.me.GoodOrderListBean.DataEntity>) GoodOrderListBean.getData());
            } else {
                ToastUtil.showToast(mContext, GoodOrderListBean.getMsg());
            }
        }
    }

    private static int chineseNums(String str) {
        byte b[] = str.getBytes();
        int byteLength = b.length;
        int strLength = str.length();
        return (byteLength - strLength) / 2;
    }

    //设置选择框内的数量的显示为黄色
    public void setOrderNumberCollor(RadioButton rb, int number) {
        String s = rb.getText().toString();
        int oldLenght = chineseNums(s);
        String num = "(" + number + ")";
        int endNumber = num.getBytes().length;
        SpannableString styledText = new SpannableString(s + num);
        styledText.setSpan(new TextAppearanceSpan(mContext, R.style.text_end_yellow), oldLenght, oldLenght + endNumber, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        rb.setText(styledText, TextView.BufferType.SPANNABLE);
    }

    private void initRefreshListView(ArrayList<GoodOrderListBean.DataEntity> list) {
        // 设置PullToRefu的mode
        refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
        listView = refreshViewList.getRefreshableView();
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 给listView添加一个设置透明背景。

        final GoodOrderListAdapter goodOrderListAdapter = new GoodOrderListAdapter(list);
        listView.setAdapter(goodOrderListAdapter);
        listView.setDivider(new ColorDrawable(CommonHelp.getColor(R.color.app_main_bg)));
        listView.setDividerHeight(CommonHelp.dip2px(mContext, 10));

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
