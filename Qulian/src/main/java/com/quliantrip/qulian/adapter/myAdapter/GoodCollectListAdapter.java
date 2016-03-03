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
public class GoodCollectListAdapter extends BasicAdapter<GoodCollectListBean.DataEntity.HouseEntity> {
    private MyCollectActivity activity;
    private boolean isEdit = false;//设置是否编辑

    public GoodCollectListAdapter(ArrayList<GoodCollectListBean.DataEntity.HouseEntity> list, Context context) {
        super(list);
        activity = (MyCollectActivity) context;
    }

    //添加一个条目
    public void addItem(GoodCollectListBean.DataEntity.HouseEntity s) {
        list.add(s);
    }

    //更新数据适配的对象,进行刷新数据
    public void updataData(ArrayList<GoodCollectListBean.DataEntity.HouseEntity> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    //删除选中数据对象，去除删除的数据
    public void removeAll(ArrayList<GoodCollectListBean.DataEntity.HouseEntity> list) {
        for (int i = 0; i < this.list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (this.list.get(i).getId().equals(list.get(j).getId())) {
                    this.list.remove(this.list.get(i));
                }
            }
        }
        for (GoodCollectListBean.DataEntity.HouseEntity houseEntity : this.list) {
            houseEntity.setIsRefresh(true);
        }
        notifyDataSetChanged();
    }

    //设置是否编辑
    public void setEdit(boolean b) {
        isEdit = b;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_my_collect_hot_good_item, null);
        }
        final Holder holder = Holder.getHolder(convertView);

        final GoodCollectListBean.DataEntity.HouseEntity bean = list.get(position);

        //选择要进行删除的条目
        holder.checkDeledct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.addOrDelectCollect(bean.ischeck(), bean);
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
        if (bean.isRefresh()) {
        ImageLoader.getInstance().displayImage(bean.getImgs().split(",")[0], holder.img, ImageLoaderOptions.pager_options);
        bean.setIsRefresh(false);
        }
        holder.isCollect.setVisibility(View.GONE);
        holder.name.setText(bean.getName());
        holder.newPrice.setText("￥" + bean.getProce());
        holder.locationDiscount.setText(bean.getChinese_name() + " · " + bean.getMeter());
        holder.oldPrice.setText("￥" + bean.getSale());

        if (holder.oldPriceLine.getWidth() != holder.oldPrice.getWidth() + 10)
            holder.oldPrice.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    holder.oldPrice.getViewTreeObserver().removeGlobalOnLayoutListener(this);
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