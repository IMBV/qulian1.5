package com.quliantrip.qulian.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.ViewPageFragmentAdapter;
import com.quliantrip.qulian.lib.PagerSlidingTabStrip;

/**
 * 带有导航条的基类
 * <p/>
 * 基类会根据不同的catalog展示相应的数据
 * <p/>
 * 要显示的数据类别
 *
 * @return
 */
public abstract class BaseViewPagerFragment extends Fragment {

    protected PagerSlidingTabStrip mTabStrip; // ViewPager顶部的导航条
    protected ViewPager mViewPager; // 展示内容用的滚动布局ViewPager
    protected ViewPageFragmentAdapter mTabsAdapter; // 封装了数据集合的ViewPager适配器

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 填充并返回一个公共的包含导航条和ViewPager的界面Ｙ
        return inflater.inflate(R.layout.base_viewpage_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // 导航条, 可以跟随ViewPapger左右滑动.可以设置自定义的导航条内容
        mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.pager_tabstrip);

        // 可以滑动的ViewPager
        mViewPager = (ViewPager) view.findViewById(R.id.pager);

        // 封装adapter, 注意这里是继承的FragmentStatePagerAdapter, 并且传入的是getChildFragmentManager()
        // 此处封装了PagerSlidingTabStrip, ViewPager, 在Adapter内部进行一系列的初始化.
        mTabsAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(), mTabStrip, mViewPager);

        // 设置ViewPager左右两边保留页面的个数, 这里为空实现, 子类可以重写此方法进行设置
        setScreenPageLimit();

        // 通过ViewPageFragmentAdapter设置Tab选项及内容, 抽象方法, 由子类重写进行实现.
        onSetupTabAdapter(mTabsAdapter);
    }

    //这里是进行设置距两个tab的宽度的方法
    public void setTabStripWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)mTabStrip.getLayoutParams();
        layoutParams.setMargins(0,0,width/2,0);
        mTabStrip.setLayoutParams(layoutParams);
    }

    //设置viewpage加载的数量
    protected void setScreenPageLimit() {

    }

    protected abstract void onSetupTabAdapter(ViewPageFragmentAdapter adapter);

}