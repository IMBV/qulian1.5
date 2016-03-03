package com.quliantrip.qulian.adapter.homeAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.home.SecnicPlayResultBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.util.CommonHelp;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 搜索结果的单品列
 */
public class SearchHotGoodListAdapter extends BasicAdapter<SecnicPlayResultBean.DataEntity.WareEntity> {
    public SearchHotGoodListAdapter(ArrayList<SecnicPlayResultBean.DataEntity.WareEntity> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_good_list_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        final SecnicPlayResultBean.DataEntity.WareEntity bean = list.get(position);
        ImageLoader.getInstance().displayImage(bean.getImgs().split(",")[0] +"?imageView2/1/w/"+ CommonHelp.dip2px(QulianApplication.getContext(), 280)+"/h/"+
                CommonHelp.dip2px(QulianApplication.getContext(),188), holder.img, ImageLoaderOptions.pager_options);
        holder.name.setText(bean.getName());
        //设置是否收藏
        System.out.println(bean.getIs_house());
        if (bean.getIs_house()) {
            holder.isCollect.setVisibility(View.VISIBLE);
        } else {
            holder.isCollect.setVisibility(View.GONE);
        }
        holder.newPrice.setText("￥"+ bean.getProce());
        holder.oldPrice.setText("￥"+bean.getSale());
        holder.locationDiscount.setText(bean.getChinese_name()+" · "+bean.getMeter());
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
