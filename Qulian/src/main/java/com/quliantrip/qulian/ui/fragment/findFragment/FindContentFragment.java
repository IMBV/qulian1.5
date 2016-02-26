package com.quliantrip.qulian.ui.fragment.findFragment;

import android.os.Bundle;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.ViewPageFragmentAdapter;
import com.quliantrip.qulian.base.BaseViewPagerFragment;
import com.quliantrip.qulian.ui.fragment.findFragment.content.DiscountFragment;
import com.quliantrip.qulian.ui.fragment.findFragment.content.PrintSquareFragment;
import com.quliantrip.qulian.ui.fragment.findFragment.content.VoiceSquareFragment;
import com.quliantrip.qulian.ui.fragment.findFragment.content.YellowBookFragment;

/**
 * 发现的模块页面
 */
public class FindContentFragment extends BaseViewPagerFragment {
    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
        String[] title = getResources().getStringArray(R.array.find_content_tab);
        // 优惠券
        adapter.addTab(title[0], "descount", DiscountFragment.class,
                getBundle());
        // 语音广场
        adapter.addTab(title[1], "voice_square", VoiceSquareFragment.class,
                getBundle());
//        //晒图广场
//        adapter.addTab(title[2], "print_square", PrintSquareFragment.class,
//                getBundle());
//        //趣连小黄书
//        adapter.addTab(title[3], "yellow_book", YellowBookFragment.class,
//                getBundle());
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTabStripWidth(getActivity());
    }
}
