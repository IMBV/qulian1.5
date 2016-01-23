package com.quliantrip.qulian.ui.activity.mainAcivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.util.CommonHelp;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GuideActivity extends FragmentActivity {
    private Context mContext;
    private SystemBarTintManager mTintManager;
    @Bind(R.id.vp_guide_list)
    ViewPager guide;
    private static final int[] imageids = new int[]{R.mipmap.guide01,
            R.mipmap.guide02, R.mipmap.guide03, R.mipmap.guide04};
    private List<ImageView> imageViews;
    @Bind(R.id.ll_point_gray)
    LinearLayout point_gray;
    private int mPointWidth;
    @Bind(R.id.v_point_red)
    View point_red;
    @Bind(R.id.bt_enter)
    Button enterButton;
    @Bind(R.id.rl_guide_oval)
    RelativeLayout allOpint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        applyKitKatTranslucency();
        ButterKnife.bind(this);
        mContext = this;
        initDrawe();
        guide.setAdapter(new MypageAdapter());
        guide.setOnPageChangeListener(new MyPageChangedListtener());
    }

    @OnClick(R.id.bt_enter)
    void enter() {
        enterMain();
        CommonHelp.saveSp(mContext, "guideFirst", true);
        finish();
    }

    private void enterMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void initDrawe() {
        imageViews = new ArrayList<ImageView>();
        for (int i = 0; i < imageids.length; i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setBackgroundResource(imageids[i]);
            imageViews.add(imageView);
        }
        for (int i = 0; i < imageids.length; i++) {
            View view = new View(getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    CommonHelp.dip2px(getApplicationContext(), 7),
                    CommonHelp.dip2px(getApplicationContext(), 7));
            if (i > 0)
                params.leftMargin = CommonHelp.dip2px(getApplicationContext(),7);
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.shape_guide_point_gray);
            point_gray.addView(view);
        }
        point_gray.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        point_gray.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        mPointWidth = point_gray.getChildAt(1).getLeft()
                                - point_gray.getChildAt(0).getLeft();
                    }
                });

    }

    private class MypageAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    private class MyPageChangedListtener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            int len = (int) (mPointWidth * positionOffset + position
                    * mPointWidth);
            RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) point_red
                    .getLayoutParams();
            params.leftMargin = len;
            point_red.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {
            if (position == imageViews.size() - 1) {
                enterButton.setVisibility(View.VISIBLE);
                allOpint.setVisibility(View.GONE);
            } else {
                enterButton.setVisibility(View.GONE);
                allOpint.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    }

    private void applyKitKatTranslucency() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setNavigationBarTintEnabled(true);
            mTintManager.setTintColor(android.R.color.white);
//            mTintManager.setNavigationBarTintColor(android.R.color.white);
//            mTintManager.setStatusBarTintColor(android.R.color.white);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
