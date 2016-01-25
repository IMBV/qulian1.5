package com.quliantrip.qulian.ui.fragment.homeFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.CountryCityListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.CityListBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.view.MyListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Yuly on 2015/12/15.
 * www.quliantrip.com
 */
public class CityChooseFragment extends BasePageCheckFragment {
    //进行数据适配的对象
    private CityListBean cityListBean;
    @Bind(R.id.tv_city_location)
    TextView locationCountry;
    @Bind(R.id.mlv_contry_city_list)
    MyListView countryListView;
    private String homeCityName;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_city_choose, null);
        ButterKnife.bind(this, view);
        Bundle bundle =getArguments();
        homeCityName = bundle.getString("cityName");
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        return new QuestBean(map, new CityListBean().setTag(getClass().getName()), HttpConstants.CHANGE_CITY);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            cityListBean = (CityListBean) bean;
            //全部景区的国家的列表显示
            CountryCityListAdapter countryCityListAdapter =new CountryCityListAdapter((ArrayList<CityListBean.DataEntity>) ((CityListBean) bean).getData());
            countryCityListAdapter.setmContext(mContext);
            countryListView.setAdapter(countryCityListAdapter);
            countryListView.setFocusable(false);
            locationCountry.setText("当前城市：" + homeCityName);
        }
    }
}
