package com.quliantrip.qulian.adapter.homeAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.home.SecnicPlayResultBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.view.RatioImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 城市分类的适配器对象
 */
public class SearchSecnicListAdapter extends BasicAdapter<SecnicPlayResultBean.DataEntity.ScenicEntity> {

    public SearchSecnicListAdapter(ArrayList<SecnicPlayResultBean.DataEntity.ScenicEntity> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_secnic_voice_list_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        SecnicPlayResultBean.DataEntity.ScenicEntity bean = list.get(position);
        ImageLoader.getInstance().displayImage(bean.getMimg(), holder.img, ImageLoaderOptions.pager_options);
        holder.name.setText(bean.getScenic());
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tiv_scenic_voice_item_img)
        RatioImageView img;
        @Bind(R.id.tv_secnic_voice_name)
        TextView name;
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
