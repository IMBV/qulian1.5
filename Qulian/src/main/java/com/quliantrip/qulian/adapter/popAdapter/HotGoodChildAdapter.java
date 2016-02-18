package com.quliantrip.qulian.adapter.popAdapter;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.choice.good.HotGoodBean;
import com.quliantrip.qulian.util.CommonHelp;

import java.util.List;

public class HotGoodChildAdapter extends BaseAdapter {
    private Context mContext;
    private int bigCate = 0;
    private String merchant = "-1";
    private String bespeak = "-2";
    private String theme = "-3";


    List<HotGoodBean.DataEntity.ScreenEntity.ChildEntity> mChildArr;// 子item标题数组
    public HotGoodChildAdapter(Context context) {
        mContext = context;
    }

    public void setChildData(List<HotGoodBean.DataEntity.ScreenEntity.ChildEntity> childArr) {
        this.mChildArr = childArr;
    }
    public void setBigCate(int cate){
        this.bigCate = cate;
        notifyDataSetChanged();
    }

    public void setMerchantId(String id){
        this.merchant = id;
        notifyDataSetChanged();
    }
    public void setBespeakId(String id){
        this.bespeak = id;
        notifyDataSetChanged();
    }

    public void setThemeId(String id){
        this.theme = id;
        notifyDataSetChanged();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_child_layout, null);
            holder.childText = (TextView) convertView.findViewById(R.id.child_textView);
            holder.childBg = (LinearLayout) convertView.findViewById(R.id.ll_child_bg_show);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HotGoodBean.DataEntity.ScreenEntity.ChildEntity bean = mChildArr.get(position);
        if (TextUtils.isEmpty(bean.getName()))
            holder.childText.setText(mChildArr.get(position).getTag_name());
        else
            holder.childText.setText(mChildArr.get(position).getName());
        switch (bigCate){
            case 0:
                if((bean.getId().equals("")?"-1":bean.getId()).equals(this.merchant)){
                    holder.childBg.setBackground(CommonHelp.getDrawable(R.drawable.shape_bg_gray));
                    holder.childText.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
                }else{
                    holder.childBg.setBackground(CommonHelp.getDrawable(R.drawable.shape_bg_white));
                    holder.childText.setTextColor(CommonHelp.getColor(R.color.app_main_title_text));
                }
                break;
            case 1:
                if((bean.getId().equals("")?"-2":bean.getId()).equals(bespeak)){
                    holder.childBg.setBackground(CommonHelp.getDrawable(R.drawable.shape_bg_gray));
                    holder.childText.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
                }else{
                    holder.childBg.setBackground(CommonHelp.getDrawable(R.drawable.shape_bg_white));
                    holder.childText.setTextColor(CommonHelp.getColor(R.color.app_main_title_text));
                }
                break;
            case 2:
                if((bean.getId().equals("")?"-3":bean.getId()).equals(theme)){
                    holder.childBg.setBackground(CommonHelp.getDrawable(R.drawable.shape_bg_gray));
                    holder.childText.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
                }else{
                    holder.childBg.setBackground(CommonHelp.getDrawable(R.drawable.shape_bg_white));
                    holder.childText.setTextColor(CommonHelp.getColor(R.color.app_main_title_text));
                }
                break;
        }
        return convertView;
    }

    static class ViewHolder {
        TextView childText;
        LinearLayout childBg;
    }

    @Override
    public int getCount() {
        if (mChildArr == null) {
            return 0;
        }
        return mChildArr.size();
    }

    @Override
    public Object getItem(int position) {
        return mChildArr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
