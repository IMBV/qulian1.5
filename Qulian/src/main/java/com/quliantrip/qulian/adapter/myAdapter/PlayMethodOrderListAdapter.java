package com.quliantrip.qulian.adapter.myAdapter;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.me.PlayMethodOrderBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.view.MyListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 玩法订单列表页
 */
public class PlayMethodOrderListAdapter extends BasicAdapter<PlayMethodOrderBean.DataEntity> {

    public PlayMethodOrderListAdapter(ArrayList<PlayMethodOrderBean.DataEntity> list) {
        super(list);
    }
    public void addItem(PlayMethodOrderBean.DataEntity s) {
        list.add(s);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_my_play_order_list_item, null);
        }

        PlayMethodOrderBean.DataEntity bean = list.get(position);
        Holder holder = Holder.getHolder(convertView);

        holder.orderNUmber.setText(bean.getOrder_sn());//设置订单编号
        holder.name.setText(bean.getName());

        holder.listView.setDivider(new ColorDrawable(CommonHelp.getColor(R.color.colorPrimary)));
        holder.listView.setDividerHeight(CommonHelp.dip2px(QulianApplication.getContext(), 15));
        holder.listView.setAdapter(new PlayMethodOrderListGoodItemAdapter((ArrayList<PlayMethodOrderBean.DataEntity.PalyEntity>) bean.getPaly()));
        holder.listView.setFocusable(false);
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_play_method_group_number)
        TextView orderNUmber;
        @Bind(R.id.tv_play_method_order_name)
        TextView name;
        @Bind(R.id.mlv_play_method_good_list)
        MyListView listView;

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
