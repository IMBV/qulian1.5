package com.quliantrip.qulian.adapter.choiceAdapter.good;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.choice.good.HotGoodBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.util.CommonHelp;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 单品玩法列表也的展示
 */
public class HotGoodListAdapter extends BasicAdapter<HotGoodBean.DataEntity.OnlineEntity> {
    public HotGoodListAdapter(ArrayList<HotGoodBean.DataEntity.OnlineEntity> list) {
        super(list);
    }

    public void updateListView(ArrayList<HotGoodBean.DataEntity.OnlineEntity> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_good_list_item, null);
        }
        final Holder holder = Holder.getHolder(convertView);
        final HotGoodBean.DataEntity.OnlineEntity bean = list.get(position);
        ImageLoader.getInstance().displayImage(bean.getImg().split(",")[0] + "?imageView2/1/w/" + CommonHelp.dip2px(QulianApplication.getContext(), 280) + "/h/" +
                CommonHelp.dip2px(QulianApplication.getContext(), 188), holder.img, ImageLoaderOptions.pager_options);

        if (bean.isIs_house()) {
            holder.isCollect.setVisibility(View.VISIBLE);
        } else {
            holder.isCollect.setVisibility(View.GONE);
        }

        holder.name.setText(bean.getName());
        holder.newPrice.setText("￥" + bean.getProce());
        holder.locationDiscount.setText(bean.getChinese_name() + " · " + bean.getMeter());
        holder.oldPrice.setText("￥" + bean.getSale());

        if (holder.oldPriceLine.getWidth() != holder.oldPrice.getWidth() + (holder.oldPrice.getWidth() * 10) * 2)
            holder.oldPrice.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    holder.oldPrice.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    int oldWidth = holder.oldPrice.getWidth();
                    ViewGroup.LayoutParams params = holder.oldPriceLine.getLayoutParams();
                    params.width = oldWidth + (oldWidth / 10) * 2;
                    holder.oldPriceLine.setLayoutParams(params);
                }
            });
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
        @Bind(R.id.view_hot_good_old_price_line)
        View oldPriceLine;

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
