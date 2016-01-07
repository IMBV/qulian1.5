package com.quliantrip.qulian.view.HorizontalScroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.ImageBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.util.CommonHelp;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yuly on 2015/12/1.
 */
public class HorizontalScrollViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> mDatas;

    int mPosition = 0;// 选中的位置

    public HorizontalScrollViewAdapter(Context context, ArrayList<String> mDatas) {
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
        System.out.println("dianji");
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.view_horizontalscrollview_item, parent, false);
        }
        Holder holder = Holder.getHolder(convertView);
//        if (position == 0) {
//
//        } else {
//            params.leftMargin = CommonHelp.dip2px(mContext, 5);
//        }
        if (mPosition == position) {
            System.out.println("dianji");
//            convertView.setBackgroundColor(mContext.getResources().getColor(
//                    R.color.colorPrimary));
//            holder.groupName.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
            holder.name.setSelected(true);
        } else {
            System.out.println("dianji2");
//            convertView.setBackgroundColor(mContext.getResources().getColor(
//                    R.color.app_main_sub_bg));
//            holder.groupName.setTextColor(CommonHelp.getColor(R.color.app_main_sub_title_text));
            holder.name.setSelected(false);
        }

        holder.name.setText(mDatas.get(position));
        return convertView;
    }

    static class Holder {
        @Bind(R.id.rb_home_page)
        Button name;

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

    /**
     * 设置选择的位置
     *
     * @param position
     */
    public void setSelectedPosition(int position) {
        this.mPosition = position;
    }
}
