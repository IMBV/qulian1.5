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
import com.quliantrip.qulian.domain.me.PlayCollectListBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.ui.activity.meActivity.MyCollectActivity;
import com.quliantrip.qulian.view.CircleImageView;
import com.quliantrip.qulian.view.RatioImageView;
import com.quliantrip.qulian.view.SlipRihtLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 玩法的收藏的数据适配器
 */
public class PlayMethodCollectListAdapter extends BasicAdapter<PlayCollectListBean.DataEntity> {
    private MyCollectActivity activity;

    public PlayMethodCollectListAdapter(ArrayList<PlayCollectListBean.DataEntity> list, Context context) {
        super(list);
        activity = (MyCollectActivity) context;
    }

    public void removeAll(ArrayList<PlayCollectListBean.DataEntity> list) {
        for (int i = 0; i < this.list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (this.list.get(i).getId().equals(list.get(j).getId())) {
                    this.list.remove(this.list.get(i));
                }
            }
        }
//        for (PlayCollectListBean.DataEntity dataEntity : this.list) {
//            dataEntity.setIsRefresh(true);
//        }
        notifyDataSetChanged();
    }

    public void addItem(PlayCollectListBean.DataEntity dataEntity) {
        list.add(dataEntity);
    }

    private boolean isEdit = false;

    public void setEdit(boolean b) {
        isEdit = b;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_my_collect_play_method_item, null);
        }
        final Holder holder = Holder.getHolder(convertView);
        final PlayCollectListBean.DataEntity bean = list.get(position);

        //设置左边编辑按钮
        if (bean.ischeck())
            holder.state.setImageResource(R.mipmap.cnb_wode_pre);
        else
            holder.state.setImageResource(R.mipmap.cnb_wode_nor);

        holder.checkDeledct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.addOrDelectCollect(bean.ischeck(), bean);
                bean.setIscheck(!bean.ischeck());
                notifyDataSetChanged();
            }
        });
        holder.slipRihtLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                holder.slipRihtLayout.layoutContent(isEdit);
            }
        });

        //添加数据
        //添加玩法图片资源
//        if (bean.isRefresh()) {
        ImageLoader.getInstance().displayImage(bean.getImgs().split(",")[0], holder.img, ImageLoaderOptions.pager_options_big);
        ImageLoader.getInstance().displayImage(bean.getHead_img(), holder.authoeImg, ImageLoaderOptions.pager_options);
//            bean.setIsRefresh(false);
//        }

        holder.dianDisCount.setText(bean.getRegion());
        holder.title.setText(bean.getTitle());
        holder.des.setText(bean.getSummary());
        holder.likeNumber.setText("有" + bean.getBuynum() + "这样玩");
        holder.price.setText("￥" + bean.getProce());
        return convertView;
    }

    static class Holder {
        //左边选择框
        @Bind(R.id.ll_is_checked_delect)
        LinearLayout checkDeledct;
        @Bind(R.id.srl_play_method_collect_item)
        SlipRihtLayout slipRihtLayout;
        @Bind(R.id.iv_collect_Img_state)
        ImageView state;

        //右边数据对象
        @Bind(R.id.iv_choice_paly_method_pic)
        RatioImageView img;
        @Bind(R.id.civ_expert_author_img)
        CircleImageView authoeImg;
        @Bind(R.id.tv_like_person_number)
        TextView likeNumber;
        @Bind(R.id.tv_play_method_title)
        TextView title;
        @Bind(R.id.tv_play_method_des)
        TextView des;
        @Bind(R.id.tv_play_method_dian_disount)
        TextView dianDisCount;
        @Bind(R.id.tv_play_method_price)
        TextView price;

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
