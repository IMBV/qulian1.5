package com.quliantrip.qulian.adapter.myAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.LinkManBean;
import com.quliantrip.qulian.global.QulianApplication;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Qulian5 on 2016/1/19.
 */
public class LinkManListAdapter extends BasicAdapter<LinkManBean.LinkMan> {

    public LinkManListAdapter(ArrayList<LinkManBean.LinkMan> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_link_man_all_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        LinkManBean.LinkMan bean = list.get(position);
        holder.name.setText(bean.getName());
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_link_man_name)
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
