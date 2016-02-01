package com.quliantrip.qulian.mode.homeMode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.home.HomeShowBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.mode.BaseMode;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页商店信息的模块
 * Created by Yuly on 2015/12/11.
 * www.quliantrip.com
 */
public class HomeChoicenessMode extends BaseMode<HomeShowBean.DataEntity.ProductInfoEntity> {
    private View view;
    @Bind(R.id.iv_home_quality_icon)
    ImageView icon;
    @Bind(R.id.iv_home_quality_name)
    TextView name;

    private String goodId;
    private Context mContext;

    public HomeChoicenessMode() {
        view = View.inflate(QulianApplication.getContext(), R.layout.mode_home_choiceness, null);
    }

    @Override
    public View getModelView() {
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setData(HomeShowBean.DataEntity.ProductInfoEntity bean) {
        ImageLoader.getInstance().displayImage(bean.getImgs().split(",")[0], icon, ImageLoaderOptions.options);
        name.setText(bean.getName());
        goodId = bean.getId();
    }

    public void setContext(Context context) {
        mContext = context;
    }

    @OnClick(R.id.ll_quality_goods)
    void initDetail() {
        Intent intent = new Intent(mContext, GoodDetailActivity.class);
        intent.putExtra("goodId", goodId);
        intent.putExtra("isCollect", false);
        mContext.startActivity(intent);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }
}
