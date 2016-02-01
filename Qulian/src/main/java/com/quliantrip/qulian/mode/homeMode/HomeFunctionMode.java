package com.quliantrip.qulian.mode.homeMode;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.home.HomeShowBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.mode.BaseMode;
import com.quliantrip.qulian.mode.homeMode.FunctionModeFragment.FunctionModeFragmentOne;
import com.quliantrip.qulian.mode.homeMode.FunctionModeFragment.FunctionModeFragmentSecond;
import com.quliantrip.qulian.ui.activity.mainAcivity.MainActivity;
import com.quliantrip.qulian.util.CommonHelp;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页的功能导航的模块
 * Created by Yuly on 2015/12/4.
 * www.quliantrip.com
 */
public class HomeFunctionMode extends BaseMode<List<HomeShowBean.DataEntity.MenuEntity>> {

    private View view;
    @Bind(R.id.vp_home_function)
    ViewPager guide;
    @Bind(R.id.ll_point_gray)
    LinearLayout point_gray;
    @Bind(R.id.v_point_deep_gray)
    View deep_gray;
    private int mPointWidth;
    private ArrayList<Fragment> list;
    //这里是进行模块切换的调用
    private MainActivity mMainActivity;

    public HomeFunctionMode(MainActivity activity) {
        view = View.inflate(QulianApplication.getContext(), R.layout.mode_home_function, null);
        this.mMainActivity = activity;
    }

    @Override
    public View getModelView() {
        ButterKnife.bind(this, view);
        initDrawe();
        guide.setAdapter(new MypageAdapter(mMainActivity.getSupportFragmentManager()));
        guide.setOnPageChangeListener(new MyPageChangedListtener());
        return view;
    }

    @Override
    public void setData(final List<HomeShowBean.DataEntity.MenuEntity> list) {

    }

    private void initDrawe() {
        list = new ArrayList<Fragment>();
        list.add(new FunctionModeFragmentOne());
        list.add(new FunctionModeFragmentSecond());
        for (int i = 0; i < list.size(); i++) {
            View view = new View(QulianApplication.getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CommonHelp.dip2px(QulianApplication.getContext(), 6),
                    CommonHelp.dip2px(QulianApplication.getContext(), 6));
            if (i > 0)
                params.leftMargin = CommonHelp.dip2px(QulianApplication.getContext(), 10);
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.shape_point_gray);
            point_gray.addView(view);
        }
        point_gray.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        point_gray.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        mPointWidth = point_gray.getChildAt(1).getLeft() - point_gray.getChildAt(0).getLeft();
                    }
                });
    }

    private class MypageAdapter extends FragmentPagerAdapter {
        public MypageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == ((Fragment) obj).getView();
        }
    }

    //小点滑动滑动的操作
    private class MyPageChangedListtener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            int len = (int) (mPointWidth * positionOffset + position
                    * mPointWidth);
            RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) deep_gray.getLayoutParams();
            params.leftMargin = len;
            deep_gray.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    }

}