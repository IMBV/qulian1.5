package com.quliantrip.qulian.adapter.myAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.Test;
import com.quliantrip.qulian.domain.me.GoodCollectListBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.ui.activity.meActivity.MyCollectActivity;
import com.quliantrip.qulian.view.SlipRihtLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 收藏数据适配器单品
 */
public class GoodCollectListAdapter extends BasicAdapter<GoodCollectListBean.DataEntity> {
    private MyCollectActivity activity;

    public GoodCollectListAdapter(ArrayList<GoodCollectListBean.DataEntity> list, Context context) {
        super(list);
        activity = (MyCollectActivity) context;
    }

    public void addItem(GoodCollectListBean.DataEntity s) {
        list.add(s);
    }

    private boolean isEdit = false;

    public void setEdit(boolean b) {
        isEdit = b;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_my_collect_hot_good_item, null);
        }
        final Holder holder = Holder.getHolder(convertView);

        final GoodCollectListBean.DataEntity bean = list.get(position);
        holder.checkDeledct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.addOrDelectCollect(bean.ischeck(), "saasdf" + bean.getName());
                bean.setIscheck(!bean.ischeck());
                notifyDataSetChanged();

            }
        });
        if (bean.ischeck())
            holder.state.setImageResource(R.mipmap.cnb_wode_pre);
        else
            holder.state.setImageResource(R.mipmap.cnb_wode_nor);
        holder.slipRihtLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                holder.slipRihtLayout.layoutContent(isEdit);
            }
        });

        //添加单品的信息
        ImageLoader.getInstance().displayImage(bean.getImgs().split(",")[0], holder.img, ImageLoaderOptions.pager_options);
        holder.isCollect.setVisibility(View.GONE);
        holder.name.setText(bean.getName());
        holder.newPrice.setText("￥"+bean.getProce());
        holder.locationDiscount.setText("no"+" · "+bean.getMeter());
        holder.oldPrice.setText("￥ " + bean.getSale());

        holder.oldPrice.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
//                        holder.oldPrice.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int oldWidth = holder.oldPrice.getWidth();
                ViewGroup.LayoutParams params = holder.oldPriceLine.getLayoutParams();
                params.width = oldWidth + 10;
                holder.oldPriceLine.setLayoutParams(params);
            }
        });
        return convertView;
    }

    static class Holder {
        @Bind(R.id.ll_is_checked_delect)
        LinearLayout checkDeledct;
        @Bind(R.id.srl_play_method_collect_item)
        SlipRihtLayout slipRihtLayout;
        @Bind(R.id.iv_collect_Img_state)
        ImageView state;

        //添加单品的信息
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