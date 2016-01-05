package com.quliantrip.qulian.ui.fragment.backSimpleFragment;

import android.os.Bundle;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.ViewPageFragmentAdapter;
import com.quliantrip.qulian.base.BaseViewPagerFragment;
import com.quliantrip.qulian.ui.fragment.orderFragment.ShowFram;

/**
 * Created by Yuly on 2015/12/16.
 * www.quliantrip.com
 */
public class OrderFragment extends BaseViewPagerFragment {


    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
        String[] title = getResources().getStringArray(
                R.array.order_viewpage_arrays);
        // 资讯
        adapter.addTab(title[0], "news", ShowFram.class,
                getBundle());
        // 热点
        adapter.addTab(title[1], "news_week", ShowFram.class,
                getBundle());
        // 博客
        adapter.addTab(title[2], "latest_blog", ShowFram.class,
                getBundle());
        // 推荐
        adapter.addTab(title[3], "recommend_blog", ShowFram.class,
                getBundle());
        mViewPager.setCurrentItem(getArguments().getInt("orderState"));
    }
    @Override
    protected void setScreenPageLimit() {
        mViewPager.setOffscreenPageLimit(3);
    }

    private Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("key", "测试 " );
        return bundle;
    }

}
