package com.quliantrip.qulian.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.CityListBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 城市分类的适配器对象
 */
public class ClassifyCityListAdapter extends BasicAdapter<CityListBean.DataEntity.ChildEntity> {

    public ClassifyCityListAdapter(ArrayList<CityListBean.DataEntity.ChildEntity> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_city_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        CityListBean.DataEntity.ChildEntity bean = list.get(position);
        holder.cityName.setText(bean.getChinese_name());
        holder.englishName.setText(bean.getEnglish_name());
        ImageLoader.getInstance().displayImage(bean.getImg_url(), holder.cityImg, ImageLoaderOptions.pager_options);
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_city)
        TextView cityName;
        @Bind(R.id.tv_city_english_name)
        TextView englishName;
        @Bind(R.id.iv_city_bg_img)
        ImageView cityImg;

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
