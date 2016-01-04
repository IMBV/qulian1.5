package com.quliantrip.qulian.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.HomeBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Yuly on 2015/12/10.
 * www.quliantrip.com
 */
public class HomeRecommendAdapter extends BasicAdapter<HomeBean.DealListEntity> {

    public HomeRecommendAdapter(ArrayList<HomeBean.DealListEntity> list) {
        super(list);
    }

    public void upDataItem(ArrayList<HomeBean.DealListEntity> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_home_recommend_play, null);
        }
        Holder holder = Holder.getHolder(convertView);
        HomeBean.DealListEntity bean = list.get(position);
        ImageLoader.getInstance().displayImage(bean.getIcon(), holder.pic, ImageLoaderOptions.options_big);
        return convertView;
    }

    static class Holder {
        @Bind(R.id.iv_home_recommend_pic)
        ImageView pic;


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
