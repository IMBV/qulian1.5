package com.quliantrip.qulian.ui.fragment.happinessFragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.HomeBean;
import com.quliantrip.qulian.domain.HomePageBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.mainAcivity.MainActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yuly on 2015/12/7.
 * www.quliantrip.com
 */
public class RecommendRouteFragment extends BasePageCheckFragment {

    @Bind(R.id.ll_route_sift)
    LinearLayout siftCondition;
    @Bind(R.id.tv_recommend_route_text)
    TextView siftText;
    @Bind(R.id.iv_recommend_route_img)
    ImageView siftImg;

    private static RecommendRouteFragment recommendRouteFragment = new RecommendRouteFragment();

    public static RecommendRouteFragment getRecommendRouteFragment(QuestBean questBean) {
        return recommendRouteFragment;
    }

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_choiceness_recommend_route, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ctl", "index");
        map.put("act", "app");
        map.put("r_type", "1");
        return new QuestBean(map, new HomeBean().setTag(getClass().getName()), HttpConstants.HOST_ADDR_ROOT_NET);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {

    }

    @OnClick(R.id.ll_route_sift) void showSiftRoute(){
        ToastUtil.showToast(mContext, "我被点击了。。。");
        setSelector(true);
    }
    public void setSelector(boolean b){
        if (b){
            siftText.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
            siftImg.setImageResource(R.mipmap.nav_shaixuan_press);
        }else {

        }

    }
}
