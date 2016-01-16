package com.quliantrip.qulian.ui.fragment.meFragment.discount.content;

import android.os.Bundle;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.ViewPageFragmentAdapter;
import com.quliantrip.qulian.base.BaseViewPagerFragment;
import com.quliantrip.qulian.ui.fragment.findFragment.content.DiscountFragment;
import com.quliantrip.qulian.ui.fragment.findFragment.content.PrintSquareFragment;
import com.quliantrip.qulian.ui.fragment.findFragment.content.VoiceSquareFragment;
import com.quliantrip.qulian.ui.fragment.findFragment.content.YellowBookFragment;

/**
 *
 */
public class MyDiscountContentFragment extends BaseViewPagerFragment {
    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
        String[] title = getResources().getStringArray(R.array.my_discount_content_tab);
        // 可使用
        adapter.addTab(title[0], "yes_user", CanUserDiscountFragment.class,
                getBundle());
        //已使用
        adapter.addTab(title[1], "old_user", YetUserDiscountFragment.class,
                getBundle());
        //已过期
        adapter.addTab(title[2], "no_user", PastDueDiscountFragment.class,
                getBundle());
        //此方法使用来进行显示哪个条目
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
