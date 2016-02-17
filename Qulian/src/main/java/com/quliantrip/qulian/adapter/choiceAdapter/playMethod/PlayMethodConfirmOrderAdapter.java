package com.quliantrip.qulian.adapter.choiceAdapter.playMethod;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMehtodOrderConfirmBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.view.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 玩法确认订单，单品信息数据适配器
 */
public class PlayMethodConfirmOrderAdapter extends BasicAdapter<PlayMehtodOrderConfirmBean.DataEntity.PlayorderEntity> {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public PlayMethodConfirmOrderAdapter(ArrayList<PlayMehtodOrderConfirmBean.DataEntity.PlayorderEntity> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_choice_play_method_confirm_order_good_list, null);
        }
        Holder holder = Holder.getHolder(convertView);
        PlayMehtodOrderConfirmBean.DataEntity.PlayorderEntity bean = list.get(position);
        holder.orderName.setText(bean.getOrdershop().getName());
        holder.taocan.setText(bean.getAttribute());
        holder.data.setText(sdf.format(new Date(Integer.valueOf(bean.getOrdershop().getDate()) * 1000L)));
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_order_confirm_name)
        TextView orderName;
        @Bind(R.id.tv_order_confirm_taocan)
        TextView taocan;
        @Bind(R.id.tv_order_confirm_data)
        TextView data;
        @Bind(R.id.tv_order_confirm_time)
        TextView time;

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
