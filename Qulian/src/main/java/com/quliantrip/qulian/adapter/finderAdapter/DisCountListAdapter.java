package com.quliantrip.qulian.adapter.finderAdapter;

import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.find.DiscountBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.view.RatioImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 优惠券的适配器
 */
public class DisCountListAdapter extends BasicAdapter<DiscountBean.DataEntity> {

    public DisCountListAdapter(ArrayList<DiscountBean.DataEntity> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_find_discount_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        DiscountBean.DataEntity bean = list.get(position);
        ImageLoader.getInstance().displayImage(bean.getImage().trim(), holder.imageView, ImageLoaderOptions.pager_options_big);
        return convertView;
    }

    static class Holder {
        @Bind(R.id.riv_find_discount_img)
        RatioImageView imageView;

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
