package com.quliantrip.qulian.ui.fragment.findFragment.content;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.finderAdapter.DisCountListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.find.DiscountBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.findActivity.ImageScaleActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主页优惠券展示显示
 */
public class DiscountFragment extends BasePageCheckFragment {
    private View view;
    //下拉刷新
    @Bind(R.id.pull_refresh_list)
    PullToRefreshListView refreshViewList;
    protected ListView listView;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.view_pulltorefresh_listview,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        return new QuestBean(map, new DiscountBean().setTag(getClass().getName()), HttpConstants.DISCOUNT_LIST);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            DiscountBean discountBean = (DiscountBean) bean;
            if (discountBean.getCode() == 200){
                initListView(discountBean.getData());
            }else{
                ToastUtil.showToast(mContext,discountBean.getMsg());
            }
        }
    }

    //添加显示列表对象
    private DisCountListAdapter discountListAdapter;

    private void initListView(final List<DiscountBean.DataEntity> listDiscount) {
        refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
        listView = refreshViewList.getRefreshableView();
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));

        discountListAdapter = new DisCountListAdapter((ArrayList<DiscountBean.DataEntity>) listDiscount);
        listView.setAdapter(discountListAdapter);


        listView.setDivider(new ColorDrawable(CommonHelp.getColor(R.color.colorPrimary)));
        listView.setDividerHeight(CommonHelp.dip2px(mContext, 10));

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) refreshViewList.getLayoutParams();
        params.topMargin = CommonHelp.dip2px(mContext, 10);
        refreshViewList.setLayoutParams(params);

        //条目点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DiscountBean.DataEntity bean = listDiscount.get(position - 1);
                        Intent intent = new Intent(mContext, ImageScaleActivity.class);
                        final ArrayList<String> listArray = new ArrayList<String>();
                        listArray.add(bean.getImage());
                        intent.putStringArrayListExtra("screenList", listArray);
                        intent.putExtra("position", position);
                        mContext.startActivity(intent);
                    }
                });
            }
        });

        // 下拉刷新或下拉加载的操作
        refreshViewList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                if (refreshViewList.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {

                    CommonHelp.runOnUIThread(new Runnable() {

                        @Override
                        public void run() {

                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                } else {
                    CommonHelp.runOnUIThread(new Runnable() {

                        @Override
                        public void run() {
                            refreshViewList.onRefreshComplete();
                        }
                    }, 500);
                }
            }
        });
    }
}
