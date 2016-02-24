package com.quliantrip.qulian.adapter.finderAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.find.VoiceSquareBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.ui.activity.findActivity.SpotDetailActivity;
import com.quliantrip.qulian.view.MyGridView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 城市地区下景点的列表数据适配器
 */
public class VoiceSquareListAdapter extends BasicAdapter<VoiceSquareBean.DataEntity> {
    private Context mContext;

    public VoiceSquareListAdapter(ArrayList<VoiceSquareBean.DataEntity> list, Context context) {
        super(list);
        mContext = context;
    }

    public void updataList(ArrayList<VoiceSquareBean.DataEntity> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_find_voice_square_area_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        //设置地区的名称
        VoiceSquareBean.DataEntity bean = list.get(position);
        holder.countryName.setText(bean.getArea_name()+"地区");

        //设置地区下的景点条目的点击事件
        holder.gridView.setAdapter(new VoiceSquareSecnicGridViewAdapter((ArrayList<VoiceSquareBean.DataEntity.ChildEntity>) bean.getChild()));
        holder.gridView.setFocusable(false);
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VoiceSquareBean.DataEntity.ChildEntity bean = ((VoiceSquareBean.DataEntity.ChildEntity) parent.getAdapter().getItem(position));
                Intent intent = new Intent(mContext, SpotDetailActivity.class);
                mContext.startActivity(intent);
                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
            }
        });
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_city_country)
        TextView countryName;
        @Bind(R.id.mgv_city_list_country)
        MyGridView gridView;

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
