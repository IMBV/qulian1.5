package com.quliantrip.qulian.ui.fragment.homeFragment;

import android.os.Bundle;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.ViewPageFragmentAdapter;
import com.quliantrip.qulian.base.BaseViewPagerFragment;
import com.quliantrip.qulian.ui.fragment.homeFragment.secnicPlay.LocalPlayFragment;
import com.quliantrip.qulian.ui.fragment.homeFragment.secnicPlay.PlayMethodFragment;
import com.quliantrip.qulian.ui.fragment.homeFragment.secnicPlay.SecnicFragment;

/**
 * Created by Qulian5 on 2016/1/5.
 */
public class SecnicPlayFragment extends BaseViewPagerFragment {

    private static SecnicPlayFragment secnicPlayFragment = new SecnicPlayFragment();

    public static SecnicPlayFragment getInstance() {
        if (secnicPlayFragment == null)
            secnicPlayFragment = new SecnicPlayFragment();
        return secnicPlayFragment;
    }

    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
        String[] title = getResources().getStringArray(
                R.array.home_secnic_play_local);
        // 景点
        adapter.addTab(title[0], "news", SecnicFragment.class,
                getBundle());
        // 玩法
        adapter.addTab(title[1], "news_week", PlayMethodFragment.class,
                getBundle());
        //当地游
        adapter.addTab(title[2], "latest_blog", LocalPlayFragment.class,
                getBundle());
//        mViewPager.setCurrentItem(getArguments().getInt("orderState"));
    }

    @Override
    protected void setScreenPageLimit() {
        mViewPager.setOffscreenPageLimit(3);
    }

    private Bundle getBundle() {
        Bundle bundle = new Bundle();
        return bundle;
    }
}
