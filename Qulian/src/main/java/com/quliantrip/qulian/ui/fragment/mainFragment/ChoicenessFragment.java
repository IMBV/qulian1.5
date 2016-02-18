package com.quliantrip.qulian.ui.fragment.mainFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.MainChoiceAdapter;
import com.quliantrip.qulian.ui.activity.HomeActivity.SecnicPlayConditionActivity;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.good.HotGoodsFragment;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.playMethod.RecommendRouteFragment;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.UIHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 精选界面
 */
public class ChoicenessFragment extends Fragment {
    protected Context mContext;
    private Fragment currentFragment = new RecommendRouteFragment();
    private FragmentManager mFragmentManager;
    private View view;
    private String cityId;

    @Bind(R.id.tv_choiceness_title_loaction)
    TextView cityName;

    @Bind(R.id.vp_happiness_container)
    ViewPager viewPager;
    @Bind(R.id.tv_recommend_route_text)
    TextView routeText;
    @Bind(R.id.tv_hot_goods_text)
    TextView goodText;
    @Bind(R.id.v_hot_recommend_route_line)
    View routeLine;
    @Bind(R.id.v_hot_goods_line)
    View hotGoodLine;
    private MainChoiceAdapter adapter;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private RecommendRouteFragment recommendRouteFragment;
    private HotGoodsFragment hotGoodsFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (Context) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_main_choiceness, null);
        ButterKnife.bind(this, view);
        recommendRouteFragment = new RecommendRouteFragment();
        hotGoodsFragment = new HotGoodsFragment();
        cityName.setText(CommonHelp.getStringSp(mContext, "globalCityName", CommonHelp.getString(R.string.change_city_tacit_name)));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isShowHotGood) {
            if (hotGoodClassfyId != null)
                hotGoodsFragment.changeClassify(hotGoodClassfyId);
            viewPager.setCurrentItem(1);
        }
        isShowHotGood = false;
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
        fragments.add(recommendRouteFragment);
        fragments.add(hotGoodsFragment);
        adapter = new MainChoiceAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        lightAndScaleTitle();
    }


    private void lightAndScaleTitle() {
        final int currentPage = viewPager.getCurrentItem();
        routeText.setTextColor(getResources().getColor(currentPage == 0 ? R.color.app_main_collor : R.color.app_main_title_text));
        goodText.setTextColor(getResources().getColor(currentPage == 1 ? R.color.app_main_collor : R.color.app_main_title_text));
        routeLine.setVisibility(currentPage == 0 ? View.VISIBLE : View.GONE);
        hotGoodLine.setVisibility(currentPage == 1 ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.rl_recommend_route)
    void showRecommendRoute() {
        viewPager.setCurrentItem(0);
        hidePopwindow();
    }

    @OnClick(R.id.rl_hot_goods)
    void showHotGoods() {
        viewPager.setCurrentItem(1);
        hidePopwindow();
    }

    //这里是进行数据展示的界面
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            String cityNameString = CommonHelp.getStringSp(mContext, "globalCityName", CommonHelp.getString(R.string.change_city_tacit_name));
            if (!cityName.getText().toString().trim().equals(cityNameString)) {
                cityName.setText(cityNameString);
                if (hotGoodsFragment != null)
                    hotGoodsFragment.requestDataForAll();
                if (recommendRouteFragment != null)
                    recommendRouteFragment.requestDataForAll();
            }
        } else {
            String cityNameString = CommonHelp.getStringSp(mContext, "globalCityName", CommonHelp.getString(R.string.change_city_tacit_name));
            if (!cityName.getText().toString().trim().equals(cityNameString)) {
                cityName.setText(cityNameString);
                hotGoodsFragment.requestDataForAll();
                recommendRouteFragment.requestDataForAll();
                if (hotGoodsFragment != null)
                    hotGoodsFragment.requestDataForAll();
                if (recommendRouteFragment != null)
                    recommendRouteFragment.requestDataForAll();
            }
        }
    }

    //隐藏精选的popupwindow
    public void hidePopwindow() {
        if (hotGoodsFragment != null) {
            hotGoodsFragment.hidePopupWindow();
        }
        if (recommendRouteFragment != null) {
            recommendRouteFragment.hidePopupWindow();
        }
    }

    //点击切换城市
    @OnClick(R.id.rl_city_choose)
    void chooseSity() {
        Bundle bundle = new Bundle();
        String s = cityName.getText().toString().trim();
        bundle.putString("cityName", s);
        UIHelper.showCityChoose(this, 20, bundle);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 20) {
            CommonHelp.saveStringSp(mContext, "globalCityId", data.getStringExtra("cityId"));
            CommonHelp.saveStringSp(mContext, "globalCityName", data.getStringExtra("cityName"));
            CommonHelp.saveStringSp(mContext, "cityImg", data.getStringExtra("cityImg"));
            if (!cityName.getText().toString().trim().equals(data.getStringExtra("cityName"))) {
                cityName.setText(data.getStringExtra("cityName"));
                if (hotGoodsFragment != null)
                    hotGoodsFragment.requestDataForAll();
                if (recommendRouteFragment != null)
                    recommendRouteFragment.requestDataForAll();
            }
        }
    }

    //切换为热门单品列表
    public void changeHotGoodFragment() {
        if (hotGoodsFragment == null) {
            hotGoodsFragment = new HotGoodsFragment();
        }
        hotGoodsFragment.changeClassify(hotGoodClassfyId);
        viewPager.setCurrentItem(1);
    }

    //切换当没有显示精选Fragment没有添加时调用
    public void changeNoHotGoodFragment() {
        if (hotGoodsFragment == null) {
            hotGoodsFragment = new HotGoodsFragment();
        }
        isShowHotGood = true;
        hotGoodClassfyId = "53";
    }

    private boolean isShowHotGood = false;
    private String hotGoodClassfyId = null;


    //切换为玩法显示列表
    public void changePlayMethodFragment() {
        if (recommendRouteFragment == null) {
            recommendRouteFragment = new RecommendRouteFragment();
        }
        viewPager.setCurrentItem(0);
    }

    //搜索界面
    @OnClick(R.id.iv_find_earch)
    void gotoFindSecnicPaly() {
        Intent intent = new Intent(mContext, SecnicPlayConditionActivity.class);
        mContext.startActivity(intent);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }
}