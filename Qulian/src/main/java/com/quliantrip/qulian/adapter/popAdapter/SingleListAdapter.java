package com.quliantrip.qulian.adapter.popAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.SingleListBean;

import java.util.List;

public class SingleListAdapter extends BaseAdapter {
    Context mContext;
    List<SingleListBean> singleList;

    public SingleListAdapter(Context context, List<SingleListBean> singleList) {
        this.mContext = context;
        this.singleList = singleList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        // 初始化布局控件
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_single_list_layout, null);
            holder.groupName = (TextView) convertView
                    .findViewById(R.id.tv_single_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.groupName.setText(singleList.get(position).getDes());
        return convertView;
    }

    @Override
    public int getCount() {
        return singleList.size();
    }

    @Override
    public Object getItem(int position) {
        return singleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {

        TextView groupName;
    }
}
