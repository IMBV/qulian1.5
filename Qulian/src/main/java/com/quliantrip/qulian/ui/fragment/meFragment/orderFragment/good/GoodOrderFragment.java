package com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.good;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.GoodOrderListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodBean;
import com.quliantrip.qulian.domain.common.HintInfoBean;
import com.quliantrip.qulian.domain.find.VoiceSquareBean;
import com.quliantrip.qulian.domain.me.GoodOrderListBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;
import com.quliantrip.qulian.view.downPopupwindow.ExpandAllTabView;
import com.quliantrip.qulian.view.downPopupwindow.LeftFilterView;
import com.quliantrip.qulian.view.downPopupwindow.MiddleFilterView;
import com.quliantrip.qulian.view.downPopupwindow.RightFilterView;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的订单详列表页面
 */
public class GoodOrderFragment extends BasePageCheckFragment {
    private View view;
    private String pay_status;
    private String order_status;
    private String is_use;


    //头部的下来刷新页
    @Bind(R.id.expandtab_view)
    ExpandAllTabView expandTabView;
    @Bind(R.id.rl_pager_empty)
    RelativeLayout empty;

    //下来刷新的列表页
    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView refreshViewList;
    protected ListView listView;

    private GoodOrderListAdapter goodOrderListAdapter;
    //    //所有
//    @Bind(R.id.rb_me_order_good_all)
//    RadioButton all;
//    //未付款
//    @Bind(R.id.rb_me_order_good_to_pay)
//    RadioButton toPay;
//    //待确认
//    @Bind(R.id.rb_me_order_good_to_confirmed)
//    RadioButton toConfirmed;
//    //已确认
//    @Bind(R.id.rb_me_order_good_confirmed)
//    RadioButton confirmed;
//    //已使用
//    @Bind(R.id.rb_me_order_good_user)
    RadioButton user;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_me_order_good, null);
        ButterKnife.bind(this, view);
//        setOrderNumberCollor(all, 10);
//        setOrderNumberCollor(toPay, 5);
        return view;
    }


    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        System.out.print(QulianApplication.getInstance().getLoginUser().getAuth_key());
        map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
        map.put("pay_status", "");
        map.put("order_status", "");
        map.put("is_use", "");
        return new QuestBean(map, new GoodOrderListBean().setTag(getClass().getName()), HttpConstants.ME_ORDER_GOOD_LIST);
    }

    public void requestDataForAll() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
        System.out.println(pay_status+order_status+is_use);
        if (pay_status != null)
            map.put("pay_status", pay_status);
        else
            map.put("pay_status", "");
        if (order_status != null)
            map.put("order_status", order_status);
        else
            map.put("order_status", "");
        if (is_use != null)
            map.put("is_use", is_use);
        else
            map.put("is_use", "");
        new PacketStringReQuest(HttpConstants.ME_ORDER_GOOD_LIST, new GoodOrderListBean().setTag(GoodOrderFragment.this.getClass().getName()), map);
    }

    private ArrayList<GoodOrderListBean.DataEntity> dataEntity;
    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            GoodOrderListBean goodOrderListBean = (GoodOrderListBean) bean;
            if (goodOrderListBean.getCode() == 200) {
                if (dataEntity == null)
                    initPlayMethodConditiom();
                dataEntity = (ArrayList<GoodOrderListBean.DataEntity>) goodOrderListBean.getData();
                initRefreshListView(dataEntity);
            } else {
                ToastUtil.showToast(mContext, goodOrderListBean.getMsg());
            }
        }

        //进行删除的操作
        if (bean != null && (this.getClass().getName() + "delOrder").equals(bean.getTag())) {
            HintInfoBean hintInfoBean = (HintInfoBean) bean;
            if (hintInfoBean.getCode() == 200) {
                goodOrderListAdapter.remveItem();
                ToastUtil.showToast(mContext, hintInfoBean.getMsg());
            } else {
                ToastUtil.showToast(mContext, hintInfoBean.getMsg());
            }
        }
    }

    //筛选投的设置
    private LeftFilterView viewLeft;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private MiddleFilterView viewMiddle;
    private RightFilterView viewRight;
    private HashMap<String, String> themeMap = new HashMap<>();
    private HashMap<String, String> timeMap = new HashMap<>();
    private HashMap<String, String> pNumberMap = new HashMap<>();

    List<String> themeString = new ArrayList<String>();
    List<String> timeString = new ArrayList<String>();
    List<String> pNumberString = new ArrayList<String>();

    //初始化筛选条件
    private void initPlayMethodConditiom() {
        ArrayList<String> mTextArray = new ArrayList<String>();
        mTextArray.add("支付状态");
        mTextArray.add("订票状态");
        mTextArray.add("使用状态");

        themeMap.put("未支付", "0");
        themeString.add("未支付");
        themeMap.put("已支付", "1");
        themeString.add("已支付");

        timeMap.put("正常", "0");
        timeString.add("正常");
        timeMap.put("关闭", "1");
        timeString.add("关闭");

        pNumberMap.put("已确定", "1");
        pNumberString.add("已确定");
        pNumberMap.put("已使用", "2");
        pNumberString.add("已使用");

        String[] themeArray = themeString.toArray(new String[themeString.size()]);
        String[] timeArray = timeString.toArray(new String[timeString.size()]);
        String[] pNumberArray = pNumberString.toArray(new String[pNumberString.size()]);

        viewLeft = new LeftFilterView(mContext, themeArray);
        viewMiddle = new MiddleFilterView(mContext, timeArray);
        viewRight = new RightFilterView(mContext, pNumberArray);

        mViewArray.add(viewLeft);
        mViewArray.add(viewMiddle);
        mViewArray.add(viewRight);
        expandTabView.setValue(mTextArray, mViewArray);
        initListener();
    }

    private void initListener() {
        viewLeft.setOnSelectListener(new LeftFilterView.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewLeft, showText);
            }
        });

        viewMiddle.setOnSelectListener(new MiddleFilterView.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewMiddle, showText);
            }
        });

        viewRight.setOnSelectListener(new RightFilterView.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewRight, showText);
            }
        });
    }

    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }

    private void onRefresh(View view, String showText) {

        expandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
            expandTabView.setTitle(showText, position);
        }
        switch (position) {
            case 0:
                String themeid = themeMap.get(showText);
                GoodOrderFragment.this.pay_status = themeid;
                GoodOrderFragment.this.requestDataForAll();
                break;
            case 1:
                String timeid = timeMap.get(showText);
                GoodOrderFragment.this.order_status = timeid;
                GoodOrderFragment.this.requestDataForAll();
                break;
            case 2:
                String pNumberid = pNumberMap.get(showText);
                GoodOrderFragment.this.is_use = pNumberid;
                GoodOrderFragment.this.requestDataForAll();
                break;
        }
    }

    //设置下来刷新
    private void initRefreshListView(final ArrayList<GoodOrderListBean.DataEntity> list) {
        // 设置PullToRefu的mode
        refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
        listView = refreshViewList.getRefreshableView();
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 给listView添加一个设置透明背景。

        if (goodOrderListAdapter == null) {
            goodOrderListAdapter = new GoodOrderListAdapter(list);
            listView.setAdapter(goodOrderListAdapter);
            listView.setDivider(new ColorDrawable(CommonHelp.getColor(R.color.app_main_bg)));
            listView.setDividerHeight(CommonHelp.dip2px(mContext, 10));
        }else{
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

    //设置选择框内的数量的显示为黄色，暂时没有作用
    public void setOrderNumberCollor(RadioButton rb, int number) {
        String s = rb.getText().toString();
        int oldLenght = chineseNums(s);
        String num = "(" + number + ")";
        int endNumber = num.getBytes().length;
        SpannableString styledText = new SpannableString(s + num);
        styledText.setSpan(new TextAppearanceSpan(mContext, R.style.text_end_yellow), oldLenght, oldLenght + endNumber, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        rb.setText(styledText, TextView.BufferType.SPANNABLE);
    }

    //获取支付串的中文的个数
    private static int chineseNums(String str) {
        byte b[] = str.getBytes();
        int byteLength = b.length;
        int strLength = str.length();
        return (byteLength - strLength) / 2;
    }
}
