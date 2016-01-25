package com.quliantrip.qulian.adapter.popAdapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.choice.HotGoodBean;

import java.util.List;

public class HotGoodChildAdapter extends BaseAdapter {
    private Context mContext;
    List<HotGoodBean.DataEntity.ScreenEntity.ChildEntity> mChildArr;// 子item标题数组
    public HotGoodChildAdapter(Context context) {
        mContext = context;
    }

    public void setChildData(List<HotGoodBean.DataEntity.ScreenEntity.ChildEntity> childArr) {
        this.mChildArr = childArr;
    }

//    private int groupId;
//    private void setGroupId(int id){
//        groupId = id;
//    }
//    private void setmChildId(int id){
//
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_child_layout, null);
            holder.childText = (TextView) convertView
                    .findViewById(R.id.child_textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (TextUtils.isEmpty(mChildArr.get(position).getName()))
            holder.childText.setText(mChildArr.get(position).getTag_name());
        else
            holder.childText.setText(mChildArr.get(position).getName());


        return convertView;
    }

    static class ViewHolder {
        TextView childText;
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
