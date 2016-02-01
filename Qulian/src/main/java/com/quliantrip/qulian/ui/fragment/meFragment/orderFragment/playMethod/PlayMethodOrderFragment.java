package com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.playMethod;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.PlayMethodOrderListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.me.PlayMethodOrderBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.meActivity.MyPlaymethodOrderDetailActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 玩法订单列表页
 */
public class PlayMethodOrderFragment extends BasePageCheckFragment {
    private View view;

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
        map.put("pay_status", "");
        map.put("order_status", "");
        map.put("is_use", "");
        return new QuestBean(map, new PlayMethodOrderBean().setTag(getClass().getName()), HttpConstants.ME_ORDER_PLAY_METHOD_LIST);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            PlayMethodOrderBean playMethodOrderBean = (PlayMethodOrderBean) bean;
            if (playMethodOrderBean.getCode() == 200) {
                initRefreshListView(playMethodOrderBean.getData());
            } else {
                ToastUtil.showToast(mContext, playMethodOrderBean.getMsg());
            }
        }
    }


    private void initRefreshListView(List<PlayMethodOrderBean.DataEntity> list) {
        // 设置PullToRefu的mode
        refreshViewList.setMode(PullToRefreshBase.Mode.BOTH);
        listView = refreshViewList.getRefreshableView();
        listView.setSelector(new ColorDrawable(Color.TRANSPARENT));// 给listView添加一个设置透明背景。

        final PlayMethodOrderListAdapter playMethodOrderListAdapter = new PlayMethodOrderListAdapter((ArrayList<PlayMethodOrderBean.DataEntity>) list);
        listView.setAdapter(playMethodOrderListAdapter);
        listView.setDivider(new ColorDrawable(CommonHelp.getColor(R.color.app_main_bg)));
        listView.setDividerHeight(CommonHelp.dip2px(mContext, 10));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, MyPlaymethodOrderDetailActivity.class);
                ((Activity) mContext).startActivity(intent);
                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
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