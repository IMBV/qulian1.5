package com.quliantrip.qulian.adapter.choiceAdapter.good;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.choice.good.OrderSubmitBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodOrderSubmitBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.util.CommonHelp;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 订单套餐类型的数据适配器
 */
public class OrderPlayMethodTypeAdapter extends BasicAdapter<PlayMethodOrderSubmitBean.DataEntity.AttributeEntity> {

    public OrderPlayMethodTypeAdapter(ArrayList<PlayMethodOrderSubmitBean.DataEntity.AttributeEntity> list) {
        super(list);
    }

    private String checkedId = list.get(0).getId();

    public void setCheched(String id) {
        checkedId = id;
        notifyDataSetChanged();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_good_order_type_list_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        PlayMethodOrderSubmitBean.DataEntity.AttributeEntity bean = list.get(position);
        if (bean.getId().equals(checkedId)) {
            holder.name.setBackground(CommonHelp.getDrawable(R.drawable.cnb_taocan));
            holder.name.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
        } else {
            holder.name.setBackground(CommonHelp.getDrawable(R.drawable.shape_text_right_angle));
            holder.name.setTextColor(CommonHelp.getColor(R.color.app_main_sub_title_text));
        }
        holder.name.setText(bean.getPackageX());
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_good_taocan_type_name)
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
