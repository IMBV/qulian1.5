package com.quliantrip.qulian.ui.fragment.homeFragment;

import android.os.Bundle;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.ViewPageFragmentAdapter;
import com.quliantrip.qulian.base.BaseViewPagerFragment;
import com.quliantrip.qulian.domain.home.SecnicPlayResultBean;
import com.quliantrip.qulian.ui.fragment.homeFragment.secnicPlay.LocalPlayFragment;
import com.quliantrip.qulian.ui.fragment.homeFragment.secnicPlay.PlayMethodFragment;
import com.quliantrip.qulian.ui.fragment.homeFragment.secnicPlay.SecnicFragment;

/**
 * 搜索结果页面
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
        String[] title = getResources().getStringArray(R.array.home_secnic_play_local);
        SecnicPlayResultBean.DataEntity dataEntity = (SecnicPlayResultBean.DataEntity) getArguments().getSerializable("resultData");
        //当地游
        adapter.addTab(title[2], "local_play", LocalPlayFragment.class, getBundle(dataEntity));
        // 玩法
        adapter.addTab(title[1], "play_method", PlayMethodFragment.class, getBundle(dataEntity));
        // 景点
        adapter.addTab(title[0], "secnic", SecnicFragment.class, getBundle(dataEntity));
        //设置选中的条目
        mViewPager.setCurrentItem(1);
    }

    @Override
    protected void setScreenPageLimit() {
        mViewPager.setOffscreenPageLimit(3);
    }

    //这里是fragment是给fragment设置argument的参数
    private Bundle getBundle(SecnicPlayResultBean.DataEntity dataEntity) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", dataEntity);
        return bundle;
    }
}
