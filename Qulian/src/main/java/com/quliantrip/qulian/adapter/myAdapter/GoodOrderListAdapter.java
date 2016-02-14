package com.quliantrip.qulian.adapter.myAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.me.GoodOrderListBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 玩法订单列表页
 */
public class GoodOrderListAdapter extends BasicAdapter<GoodOrderListBean.DataEntity> {
    public GoodOrderListAdapter(ArrayList<GoodOrderListBean.DataEntity> list) {
        super(list);
    }

    public void addItem(GoodOrderListBean.DataEntity s) {
        list.add(s);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_my_good_order_list_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        final GoodOrderListBean.DataEntity bean = list.get(position);

        //添加数据
        if (bean.getImgs() != null)
            ImageLoader.getInstance().displayImage(bean.getImgs().split(",")[0], holder.img, ImageLoaderOptions.pager_options);
        holder.name.setText(bean.getName());
        holder.ePrice.setText("￥" + bean.getPrice());
        holder.price.setText((Double.valueOf(bean.getPrice()) * Double.valueOf(bean.getNum())) + "");
        holder.taocan.setText(bean.getPackageX());
        holder.num.setText("×" + bean.getNum());
        holder.orderNumber.setText(bean.getOrder_sn());
        //设置点击事件
        convertView.findViewById(R.id.bt_good_order_see_consume).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(QulianApplication.getContext(), "稍后添加查看优惠券的功能");
            }
        });
        convertView.findViewById(R.id.bt_good_order_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(QulianApplication.getContext(), "稍后取消订单的功能");
            }
        });
        convertView.findViewById(R.id.bt_good_order_link_daren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(QulianApplication.getContext(), "稍后联系达人的功能");
            }
        });

        return convertView;
    }

    static class Holder {
        @Bind(R.id.iv_good_detail_img)
        ImageView img;
        @Bind(R.id.tv_good_detail_name)
        TextView name;
        @Bind(R.id.tv_good_order_price)
        TextView price;
        @Bind(R.id.tv_good_order_taocan)
        TextView taocan;
        @Bind(R.id.tv_good_order_every_price)
        TextView ePrice;
        @Bind(R.id.tv_good_order_num)
        TextView num;
        @Bind(R.id.tv_order_number_info)
        TextView orderNumber;

        public Holder(View convertView) {
            super();
            ButterKnife.bind(this, convertView);
        }

        public static Holder getHolder(View convertView) {
            Holder holder = (Holder) convertView.getTag();
            if (holder == null) {
                holder = new Holder(convertView);
                convertView.setTag(holder);
            }
            return holder;
        }
    }
}
