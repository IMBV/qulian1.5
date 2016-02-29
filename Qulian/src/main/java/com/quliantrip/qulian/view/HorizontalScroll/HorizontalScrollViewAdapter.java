package com.quliantrip.qulian.view.HorizontalScroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.find.SpotDetailBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.util.CommonHelp;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class HorizontalScrollViewAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<SpotDetailBean.DataEntity.NearPorEntity> mDatas;

    public HorizontalScrollViewAdapter(Context context, ArrayList<SpotDetailBean.DataEntity.NearPorEntity> mDatas) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = null;
        this.mDatas = mDatas;
    }

    public int getCount() {
        return mDatas.size();
    }

    public Object getItem(int position) {
        return mDatas.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.view_horizontalscrollview_item, parent, false);
        }

        //获取数据容器，与要进行数据适配的对象
        Holder holder = Holder.getHolder(convertView);
        SpotDetailBean.DataEntity.NearPorEntity bean = mDatas.get(position%mDatas.size());

        ImageLoader.getInstance().displayImage(bean.getImgs().split(",")[0]+"?imageView2/1/w/" + CommonHelp.dip2px(QulianApplication.getContext(), 165) + "/h/" +
                CommonHelp.dip2px(QulianApplication.getContext(), 110), holder.img, ImageLoaderOptions.pager_options);

        holder.name.setText(bean.getName());
        return convertView;
    }

    static class Holder {
        @Bind(R.id.iv_spot_detail_nearPor_img)
        ImageView img;
        @Bind(R.id.tv_spot_detail_nearPor_name)
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
