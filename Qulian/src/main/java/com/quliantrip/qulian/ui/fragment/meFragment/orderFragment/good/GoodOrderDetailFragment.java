package com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.good;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.me.GoodOrderDetailBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 订单详情页
 */
public class GoodOrderDetailFragment extends BasePageCheckFragment {
    private View view;
    private String orderId;

    @Bind(R.id.tv_order_number_info)
    TextView orderNumber;//订单编号
    @Bind(R.id.iv_good_detail_img)
    ImageView img;
    @Bind(R.id.tv_good_detail_name)
    TextView name;
    @Bind(R.id.tv_good_order_buy_number)
    TextView buyNumber;
    @Bind(R.id.tv_good_order_evert_price)
    TextView price;

    @Bind(R.id.tv_good_order_taocan_name)
    TextView taocanName;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_me_order_good_detail, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        orderId = getArguments().getString("orderId");
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderid", orderId);
        return new QuestBean(map, new GoodOrderDetailBean().setTag(getClass().getName()), HttpConstants.ME_ORDER_GOOD_DETAIL);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            GoodOrderDetailBean goodOrderDetailBean = (GoodOrderDetailBean) bean;
            if (goodOrderDetailBean.getCode() == 200) {
                ImageLoader.getInstance().displayImage(goodOrderDetailBean.getData().getImgs().split(",")[0], img, ImageLoaderOptions.pager_options);
                orderNumber.setText(goodOrderDetailBean.getData().getOrder_sn());
                name.setText(goodOrderDetailBean.getData().getName());
                buyNumber.setText("数量：" + goodOrderDetailBean.getData().getNum());
                price.setText("单价：" + goodOrderDetailBean.getData().getPrice());
                taocanName.setText(goodOrderDetailBean.getData().getPackageX());
            } else {
                ToastUtil.showToast(mContext, goodOrderDetailBean.getMsg());
            }
        }
    }
}
