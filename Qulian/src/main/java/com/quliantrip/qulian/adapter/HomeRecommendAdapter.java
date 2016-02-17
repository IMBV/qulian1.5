package com.quliantrip.qulian.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.HomeBean;
import com.quliantrip.qulian.domain.home.HomeShowBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页玩法列表页面
 */
public class HomeRecommendAdapter extends BasicAdapter<HomeShowBean.DataEntity.PlayEntity> {

    public HomeRecommendAdapter(ArrayList<HomeShowBean.DataEntity.PlayEntity> list) {
        super(list);
    }

    public void upDataItem(ArrayList<HomeShowBean.DataEntity.PlayEntity> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_home_recommend_play, null);
        }
        final Holder holder = Holder.getHolder(convertView);
        HomeShowBean.DataEntity.PlayEntity bean = list.get(position);
        ImageLoader.getInstance().displayImage(bean.getImgs().split(",")[0], holder.pic, ImageLoaderOptions.pager_options_big);

        holder.preView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                holder.preView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height = holder.pic.getHeight();
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.preView.getLayoutParams();
                params.height = height;
                holder.preView.setLayoutParams(params);
            }
        });
        holder.title.setText(bean.getTitle());
        holder.like.setText("有"+bean.getBuynum()+"人这样玩");
        return convertView;
    }

    static class Holder {
        @Bind(R.id.iv_home_recommend_pic)
        ImageView pic;
        @Bind(R.id.v_image_preview)
        View preView;
        @Bind(R.id.tv_home_play_title)
        TextView title;
        @Bind(R.id.tv_home_play_method_like)
        TextView like;

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
