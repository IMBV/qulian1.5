package com.quliantrip.qulian.adapter.myAdapter;

import android.view.View;
import android.view.ViewGroup;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.global.QulianApplication;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * 玩法订单列表页
 */
public class GoodOrderListAdapter extends BasicAdapter<String> {
    public GoodOrderListAdapter(ArrayList<String> list) {
        super(list);
    }

    public void addItem(String s) {
        list.add(s);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_my_good_order_list_item, null);
        }
//        Holder holder = Holder.getHolder(convertView);
//        String name = list.get(position);
//        holder.city.setText(name);
        return convertView;
    }

    static class Holder {
//        @Bind(R.id.tv_city)
//        TextView city;

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
