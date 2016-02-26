package com.quliantrip.qulian.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
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
import com.quliantrip.qulian.util.CommonHelp;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页玩法列表页面
 */
public class HomeRecommendAdapter extends BasicAdapter<HomeShowBean.DataEntity.PlayEntity> {

    private Context mContext;
    private int widthImg;
    private int heightImg;

    public HomeRecommendAdapter(ArrayList<HomeShowBean.DataEntity.PlayEntity> list, Context context) {
        super(list);
        this.mContext = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        widthImg = wm.getDefaultDisplay().getWidth() - CommonHelp.dip2px(context, 20);
        heightImg = widthImg/2;
    }

    public void upDataItem(ArrayList<HomeShowBean.DataEntity.PlayEntity> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void clearAllData() {
        this.list.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_home_recommend_play, null);
        }
        final Holder holder = Holder.getHolder(convertView);
        HomeShowBean.DataEntity.PlayEntity bean = list.get(position);
        ImageLoader.getInstance().displayImage(bean.getImgs().split(",")[0]+ "?imageView2/1/w/" + widthImg + "/h/" +
                heightImg, holder.pic, ImageLoaderOptions.pager_options_big);

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
        holder.like.setText(bean.getBuynum());
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
