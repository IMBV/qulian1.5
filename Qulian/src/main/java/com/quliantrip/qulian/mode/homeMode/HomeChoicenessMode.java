package com.quliantrip.qulian.mode.homeMode;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.HomeBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.mode.BaseMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页商店信息的模块
 * Created by Yuly on 2015/12/11.
 * www.quliantrip.com
 */
public class HomeChoicenessMode extends BaseMode<HomeBean.QualityGoodsEntity> {
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
    public void setData(HomeBean.QualityGoodsEntity bean) {
//        goodId = bean.getId();
//        ImageLoader.getInstance().displayImage(bean.getIcon(), icon, ImageLoaderOptions.options);
//        name.setText(bean.getName());
//        price.setText("￥" + bean.getCurrent_price());
//        saleNumber.setText("已售:" + bean.getBuy_count());
    }

    public void setContext(Context context) {
        mContext = context;
    }

    @OnClick(R.id.ll_quality_goods)
    void initDetail() {
//        Intent intent = new Intent(mContext, GoodDetailActivity.class);
//        intent.putExtra("goodId", HttpConstants.WEBVIEW_ROOT + "?ctl=deal&data_id=" + goodId);
//        mContext.startActivity(intent);

    }
}
