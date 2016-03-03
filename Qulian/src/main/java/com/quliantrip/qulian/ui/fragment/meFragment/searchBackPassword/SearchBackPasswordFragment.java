package com.quliantrip.qulian.ui.fragment.meFragment.searchBackPassword;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.RegisterStyleAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 找回密码操作的界面
 */
public class SearchBackPasswordFragment extends Fragment {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (Context) getActivity();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_search_password, null);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        fragments.add(new SearchBackPhoneFragment());
        fragments.add(new SearckBachEmailFragment());
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
}