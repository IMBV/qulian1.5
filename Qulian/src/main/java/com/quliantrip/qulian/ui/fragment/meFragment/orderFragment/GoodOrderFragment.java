package com.quliantrip.qulian.ui.fragment.meFragment.orderFragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.GoodOrderListAdapter;
import com.quliantrip.qulian.util.CommonHelp;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *我的订单详列表页面
 */
public class GoodOrderFragment extends Fragment {
    private Context mContext;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_order_good, null);
        ButterKnife.bind(this, view);
        setOrderNumberCollor(all, 10);
        setOrderNumberCollor(toPay, 5);
        initRefreshListView();
        return view;
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

    private void initRefreshListView() {
        // 设置PullToRefu的mode
        refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
        listView = refreshViewList.getRefreshableView();
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 给listView添加一个设置透明背景。
        final ArrayList<String> list = new ArrayList<String>();
        int i;
        for (i = 0; i <= 30; i++)
            list.add("asdf" + i);
        final GoodOrderListAdapter test = new GoodOrderListAdapter(list);
        listView.setAdapter(test);
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
                            test.addItem("shang");
                            test.notifyDataSetChanged();
                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                } else {
                    CommonHelp.runOnUIThread(new Runnable() {

                        @Override
                        public void run() {
                            test.addItem("xia");
                            test.notifyDataSetChanged();
                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                }
            }
        });
    }
}
