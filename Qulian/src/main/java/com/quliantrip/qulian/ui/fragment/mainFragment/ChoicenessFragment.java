

package com.quliantrip.qulian.ui.fragment.mainFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.HotGoodsFragment;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.RecommendRouteFragment;
import com.quliantrip.qulian.util.CommonHelp;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yuly on 2015/11/9.
 */
public class ChoicenessFragment extends Fragment {
    protected Context mContext;
    private Fragment currentFragment = new RecommendRouteFragment();
    private FragmentManager mFragmentManager;
    private View view;

    @Bind(R.id.tv_recommend_route_text)
    TextView routeText;
    @Bind(R.id.tv_hot_goods_text)
    TextView goodText;
    @Bind(R.id.v_hot_recommend_route_line)
    View routeLine;
    @Bind(R.id.v_hot_goods_line)
    View hotGoodLine;

    private RecommendRouteFragment recommendRouteFragment;
    private HotGoodsFragment hotGoodsFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (Context) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_main_choiceness, null);
        ButterKnife.bind(this, view);
        mFragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
        recommendRouteFragment = new RecommendRouteFragment();
        hotGoodsFragment = new HotGoodsFragment();
        mFragmentManager.beginTransaction().add(R.id.fl_happiness_container, recommendRouteFragment).commit();
        setTextColor(true);
        return view;
    }

    @OnClick(R.id.rl_recommend_route)
    void showRecommendRoute() {
        gotoSubFragmennt(recommendRouteFragment);
        setTextColor(true);
    }

    @OnClick(R.id.rl_hot_goods)
    void showHotGoods() {
        gotoSubFragmennt(hotGoodsFragment);
        setTextColor(false);
    }

    //改变主题样式的颜色
    private void setTextColor(Boolean starte) {
        if (starte) {
            routeText.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
            goodText.setTextColor(CommonHelp.getColor(R.color.app_main_title_text));
            routeLine.setVisibility(View.VISIBLE);
            hotGoodLine.setVisibility(View.GONE);
        } else {
            routeText.setTextColor(CommonHelp.getColor(R.color.app_main_title_text));
            goodText.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
            routeLine.setVisibility(View.GONE);
            hotGoodLine.setVisibility(View.VISIBLE);
        }
        hidePopwindow();
    }

    //切换要显示的fragment的样式
    private void gotoSubFragmennt(Fragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            if (fragment.isAdded()) {
                transaction.show(fragment);
            } else {
                transaction.add(R.id.fl_happiness_container, fragment);
            }
            transaction.hide(currentFragment);
            transaction.commit();
            currentFragment = fragment;
        }
    }

    //隐藏精选的popupwindow
    public void hidePopwindow(){
        if (hotGoodsFragment != null){
            hotGoodsFragment.hidePopupWindow();
        }
        if (recommendRouteFragment !=null){
            recommendRouteFragment.hidePopupWindow();
        }
    }
}