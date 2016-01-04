package com.quliantrip.qulian.ui.fragment.happinessFragment;

import android.view.View;
import android.widget.TextView;

import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.HomePageBean;
import com.quliantrip.qulian.net.volleyManage.QuestBean;

/**
 * Created by Yuly on 2015/12/7.
 * www.quliantrip.com
 */
public class RecommendRouteFragment extends BasePageCheckFragment {
    private static RecommendRouteFragment recommendRouteFragment = new RecommendRouteFragment();
    private QuestBean questBean;

    public static RecommendRouteFragment getRecommendRouteFragment(QuestBean questBean) {
        return recommendRouteFragment;
    }

    @Override
    protected View getSuccessView() {
        TextView textView = new TextView(mContext);
        textView.setText("推荐路线");
        return textView;
    }

    @Override
    protected QuestBean requestData() {
        return new QuestBean(null, new HomePageBean().setTag(getClass().getName()), "http://192.168.0.193:8080/01.jsp");
    }

    @Override
    public void onEventMainThread(BaseJson bean) {

    }
}
