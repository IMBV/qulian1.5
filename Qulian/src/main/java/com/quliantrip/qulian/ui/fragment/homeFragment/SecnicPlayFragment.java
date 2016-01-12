package com.quliantrip.qulian.ui.fragment.homeFragment;

import android.os.Bundle;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.ViewPageFragmentAdapter;
import com.quliantrip.qulian.base.BaseViewPagerFragment;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.ui.fragment.homeFragment.secnicPlay.LocalPlayFragment;
import com.quliantrip.qulian.ui.fragment.homeFragment.secnicPlay.PlayMethodFragment;
import com.quliantrip.qulian.ui.fragment.homeFragment.secnicPlay.SecnicFragment;
import com.quliantrip.qulian.util.ToastUtil;

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
        //测试传递的数据
        ToastUtil.showToast(QulianApplication.getContext(),getArguments().getString("keyWord"));
        // 景点
        adapter.addTab(title[0], "secnic", SecnicFragment.class,
                getBundle());
        // 玩法
        adapter.addTab(title[1], "play_method", PlayMethodFragment.class,
                getBundle());
        //当地游
        adapter.addTab(title[2], "local_play", LocalPlayFragment.class,
                getBundle());
//        mViewPager.setCurrentItem(getArguments().getInt("orderState"));
    }

    @Override
    protected void setScreenPageLimit() {
        mViewPager.setOffscreenPageLimit(3);
    }

    //这里是fragment是给fragment设置argument的参数
    private Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("sadfsadf","asdfsadfsdf");
        return bundle;
    }
}
