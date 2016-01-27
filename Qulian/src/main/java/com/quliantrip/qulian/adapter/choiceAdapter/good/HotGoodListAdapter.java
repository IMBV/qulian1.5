package com.quliantrip.qulian.adapter.choiceAdapter.good;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.choice.good.HotGoodBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Qulian5 on 2016/1/21.
 */
public class HotGoodListAdapter extends BasicAdapter<HotGoodBean.DataEntity.OnlineEntity> {
    public HotGoodListAdapter(ArrayList<HotGoodBean.DataEntity.OnlineEntity> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_good_list_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        final HotGoodBean.DataEntity.OnlineEntity bean = list.get(position);
        ImageLoader.getInstance().displayImage(bean.getImg(), holder.img, ImageLoaderOptions.pager_options);
        if (bean.isIs_house()) {
            holder.isCollect.setVisibility(View.VISIBLE);
        } else {
            holder.isCollect.setVisibility(View.GONE);
        }
        holder.name.setText(bean.getName());
        return convertView;
    }

    static class Holder {
        @Bind(R.id.iv_good_list_icon)
        ImageView img;
        @Bind(R.id.good_is_collect)
        TextView isCollect;
        @Bind(R.id.iv_hot_good_name)
        TextView name;
        @Bind(R.id.tv_good_location_dicount)
        TextView locationDiscount;
        @Bind(R.id.tv_hot_good_currentprice)
        TextView newPrice;
        @Bind(R.id.tv_hot_good_old_price)
        TextView oldPrice;

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
