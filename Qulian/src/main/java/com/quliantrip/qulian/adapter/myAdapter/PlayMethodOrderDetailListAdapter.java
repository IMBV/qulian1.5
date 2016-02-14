package com.quliantrip.qulian.adapter.myAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.me.PlayMethodOrderDetailBean;
import com.quliantrip.qulian.global.QulianApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 玩法订单详情列表页
 */
public class PlayMethodOrderDetailListAdapter extends BasicAdapter<PlayMethodOrderDetailBean.DataEntity> {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PlayMethodOrderDetailListAdapter(ArrayList<PlayMethodOrderDetailBean.DataEntity> list) {
        super(list);
    }

    public void addItem(PlayMethodOrderDetailBean.DataEntity s) {
        list.add(s);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_my_play_order_detail_list_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        PlayMethodOrderDetailBean.DataEntity bean = list.get(position);

        holder.number.setText(bean.getOrder_code());
        holder.name.setText(bean.getTitle());
        holder.taocan.setText(bean.getPackageX());
        holder.dataTime.setText(sdf.format(new Date(Integer.valueOf(bean.getDate()) * 1000L)) + " " + bean.getService());
        holder.num.setText(bean.getNum());
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_play_method_good_order_number)
        TextView number;
        @Bind(R.id.tv_play_method_order_good_name)
        TextView name;
        @Bind(R.id.tv_play_method_order_good_taocan)
        TextView taocan;
        @Bind(R.id.tv_play_method_order_good_data_time)
        TextView dataTime;
        @Bind(R.id.tv_play_method_order_good_num)
        TextView num;

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
