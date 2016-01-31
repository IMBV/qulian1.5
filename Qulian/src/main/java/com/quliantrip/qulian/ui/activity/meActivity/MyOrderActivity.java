package com.quliantrip.qulian.ui.activity.meActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.RegisterStyleAdapter;
import com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.good.GoodOrderFragment;
import com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.playMethod.PlayMethodOrderFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Qulian5 on 2016/1/13.
 */
public class MyOrderActivity extends SwipeBackActivity {
    private Context mContext;
    private View view;
    @Bind(R.id.view_pager_register)
    ViewPager viewPager;
    @Bind(R.id.tv_register_style_phone)
    TextView phoneRegister;
    @Bind(R.id.tv_register_style_email)
    TextView emailRegister;
    @Bind(R.id.v_register_phone_indicate_line)
    View phone_line;
    @Bind(R.id.v_register_email_indicate_line)
    View email_line;
    private RegisterStyleAdapter adapter;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        ButterKnife.bind(this);
        mContext = this;
        initListener();
        initData();
    }

    private void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                lightAndScaleTitle();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    protected void initData() {
        fragments.add(new PlayMethodOrderFragment());
        fragments.add(new GoodOrderFragment());
        adapter = new RegisterStyleAdapter(((FragmentActivity) mContext).getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        lightAndScaleTitle();
    }

    private void lightAndScaleTitle() {
        final int currentPage = viewPager.getCurrentItem();
        phoneRegister.setTextColor(getResources().getColor(currentPage == 0 ? R.color.app_main_collor : R.color.app_main_title_text));
        emailRegister.setTextColor(getResources().getColor(currentPage == 1 ? R.color.app_main_collor : R.color.app_main_title_text));
        phone_line.setVisibility(currentPage == 0 ? View.VISIBLE : View.GONE);
        email_line.setVisibility(currentPage == 1 ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.rl_register_style_phone)
    void showPhoneRegister() {
        viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.rl_register_style_email)
    void showEmailRegister() {
        viewPager.setCurrentItem(1);
    }

    @OnClick(R.id.iv_icon_back)
    void finishActivity() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }
}