package com.quliantrip.qulian.ui.fragment.mainFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.HomeRecommendAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.home.HomeShowBean;
import com.quliantrip.qulian.mode.homeMode.HomeChoicenessMode;
import com.quliantrip.qulian.mode.homeMode.HomeFunctionMode;
import com.quliantrip.qulian.mode.homeMode.HomeSlideImageMode;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.scanner.activity.CaptureActivity;
import com.quliantrip.qulian.scanner.activity.OpenWifiActivity;
import com.quliantrip.qulian.ui.activity.HomeActivity.SecnicPlayConditionActivity;
import com.quliantrip.qulian.ui.activity.choiceActivity.PlayMethodDetailActivity;
import com.quliantrip.qulian.ui.activity.mainAcivity.MainActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.EvaluateUtil;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;
import com.quliantrip.qulian.view.MyListView;
import com.quliantrip.qulian.view.ObservableScroll.ObservableScrollView;
import com.quliantrip.qulian.view.ObservableScroll.ScrollViewListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页界面
 */
public class HomeFragment extends BasePageCheckFragment implements ScrollViewListener {
    private String cityId = CommonHelp.getString(R.string.change_city_tacit_id);
    @Bind(R.id.iv_home_title_location)
    TextView homeTitle;//首页的城市
    @Bind(R.id.iv_home_title_back)
    ImageView titleBackgroud;//标题的背景
    @Bind(R.id.sv_scroll)
    ObservableScrollView scroll;
    @Bind(R.id.iv_home_title_arrow)
    ImageView arrow;
    @Bind(R.id.iv_home_title_wifi)
    ImageView connectWifi;

    @Bind(R.id.ll_model_container)
    LinearLayout modelContainer;
    @Bind(R.id.iv_home_recommend)
    MyListView listView;
    //定义的model
    private HomeSlideImageMode homeSlideImageMode;
    HomeFunctionMode homeFunctionMode;
    HomeChoicenessMode homeChoicenessMode;
    private MainActivity activity;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_main_home, null);
        ButterKnife.bind(this, view);
        ViewHelper.setAlpha(titleBackgroud, 0.0f);
        scroll.setScrollViewListener(this);
        activity = (MainActivity) getActivity();
        initModel();
        listView.setFocusable(false);
        homeTitle.setText(CommonHelp.getStringSp(mContext, "globalCityName", CommonHelp.getString(R.string.change_city_tacit_name)));
        return view;
    }

    //添加model到linearlayout中
    private void initModel() {
        homeSlideImageMode = new HomeSlideImageMode();
        modelContainer.addView(homeSlideImageMode.getModelView());
        homeFunctionMode = new HomeFunctionMode(activity);
        modelContainer.addView(homeFunctionMode.getModelView());
        homeChoicenessMode = new HomeChoicenessMode();
        homeChoicenessMode.setContext(mContext);
    }

    //这里是进行数据展示的界面
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            String cityNameString = CommonHelp.getStringSp(mContext, "globalCityName", CommonHelp.getString(R.string.change_city_tacit_name));
            if (!cityNameString.equals(homeTitle.getText().toString().trim())) {
                homeTitle.setText(cityNameString);
                requestDataForAll();
            }
        } else {
            String cityNameString = CommonHelp.getStringSp(mContext, "globalCityName", CommonHelp.getString(R.string.change_city_tacit_name));
            if (!cityNameString.equals(homeTitle.getText().toString().trim())) {
                homeTitle.setText(cityNameString);
                requestDataForAll();
            }
        }
    }

    @Override
    protected QuestBean requestData() {
        //打印获取坐标
//        System.out.println(CommonHelp.getStringSp(mContext, "geographic", CommonHelp.getString(R.string.change_city_tacit_name)));
        cityId = CommonHelp.getStringSp(mContext, "globalCityId", CommonHelp.getString(R.string.change_city_tacit_id));
        Map<String, String> map = new HashMap<String, String>();
        map.put("city", cityId);
        return new QuestBean(map, new HomeShowBean().setTag(getClass().getName()), HttpConstants.HOME_MAIN);
    }

    //所有参数进行
    public void requestDataForAll() {
        Map<String, String> map = new HashMap<String, String>();
        cityId = CommonHelp.getStringSp(mContext, "globalCityId", CommonHelp.getString(R.string.change_city_tacit_id));
        if (cityId != null)
            map.put("city", cityId);
        new PacketStringReQuest(HttpConstants.HOME_MAIN, new HomeShowBean().setTag(getClass().getName()), map);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && HomeFragment.this.getClass().getName().equals(bean.getTag())) {
            HomeShowBean homeShowBean = (HomeShowBean) bean;
            //banner
            homeSlideImageMode.setData(homeShowBean.getData().getBanner());
            //功能引导
            homeFunctionMode.setData(homeShowBean.getData().getMenu());
            //附近推荐单品
            if (homeShowBean.getData().getProductInfo() != null) {
                modelContainer.removeView(homeChoicenessMode.getModelView());
                modelContainer.addView(homeChoicenessMode.getModelView());
                homeChoicenessMode.setData(((HomeShowBean) bean).getData().getProductInfo());
            } else {
                modelContainer.removeView(homeChoicenessMode.getModelView());
            }
            //初始化listView的数据对象
            initListIView(homeShowBean.getData().getPlay());
        }
    }

    private HomeRecommendAdapter homeRecommendAdapter;

    private void initListIView(final List<HomeShowBean.DataEntity.PlayEntity> dealList) {
        if (dealList.size() > 0) {
            if (homeRecommendAdapter == null) {
                homeRecommendAdapter = new HomeRecommendAdapter((ArrayList<HomeShowBean.DataEntity.PlayEntity>) dealList);
            } else {
                homeRecommendAdapter.upDataItem((ArrayList<HomeShowBean.DataEntity.PlayEntity>) dealList);
            }
            listView.setAdapter(homeRecommendAdapter);
            //条目数据适配
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HomeShowBean.DataEntity.PlayEntity bean = dealList.get(position);
                    Intent intent = new Intent(mContext, PlayMethodDetailActivity.class);
                    intent.putExtra("playMethodId", bean.getId());
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
                }
            });
        } else {
            homeRecommendAdapter.clearAllData();
        }
    }

    private boolean isShang = true;

    //srcollView的滑动监听事件
    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        float percent = (float) y / (float) CommonHelp.dip2px(mContext, 231);
        ViewHelper.setAlpha(titleBackgroud, EvaluateUtil.evaluateFloat(percent, 0.0f, 1.0f));
        if (EvaluateUtil.evaluateFloat(percent, 0.0f, 1.0f) >= 0.5f && isShang) {
            homeTitle.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
            arrow.setImageResource(R.mipmap.up_jiantou_c);
            connectWifi.setImageResource(R.mipmap.scan_code_c);
            isShang = !isShang;
        }
        if (EvaluateUtil.evaluateFloat(percent, 0.0f, 1.0f) <= 0.5f && !isShang) {
            homeTitle.setTextColor(CommonHelp.getColor(R.color.colorPrimary));
            arrow.setImageResource(R.mipmap.up_jiantou);
            connectWifi.setImageResource(R.mipmap.scan_code);
            isShang = !isShang;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 0) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            if (scanResult.startsWith("SSID")) {
                data.setClass(mContext, OpenWifiActivity.class);
                startActivity(data);
            } else {
                ToastUtil.showToast(mContext, "请扫描正确的二维码");
            }
        } else if (requestCode == 1) {
            CommonHelp.saveStringSp(mContext, "globalCityId", data.getStringExtra("cityId"));
            CommonHelp.saveStringSp(mContext, "globalCityName", data.getStringExtra("cityName"));
            CommonHelp.saveStringSp(mContext, "cityImg", data.getStringExtra("cityImg"));

            if (!homeTitle.getText().toString().trim().equals(data.getStringExtra("cityName"))) {
                homeTitle.setText(data.getStringExtra("cityName"));
                requestDataForAll();
            }
        }
    }

    //点击切换城市
    @OnClick(R.id.rl_city_choose)
    void chooseSity() {
        Bundle bundle = new Bundle();
        String s = homeTitle.getText().toString().trim();
        bundle.putString("cityName", s);
        UIHelper.showCityChoose(this, 1, bundle);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    //连接wifi
    @OnClick(R.id.iv_home_title_wifi)
    void connectWifi() {
        Intent openCameraIntent = new Intent(mContext, CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    //点击景点、玩法、当地游的筛选
    @OnClick(R.id.iv_home_secnic_play_search)
    void gotoSecnicPaly() {
        Intent intent = new Intent(mContext, SecnicPlayConditionActivity.class);
        mContext.startActivity(intent);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }
}