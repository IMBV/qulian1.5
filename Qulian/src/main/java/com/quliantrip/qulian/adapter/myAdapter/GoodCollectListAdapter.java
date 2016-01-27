package com.quliantrip.qulian.adapter.myAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.Test;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.ui.activity.meActivity.MyCollectActivity;
import com.quliantrip.qulian.view.SlipRihtLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 收藏数据适配器单品
 */
public class GoodCollectListAdapter extends BasicAdapter<Test> {
    private MyCollectActivity activity;

    public GoodCollectListAdapter(ArrayList<Test> list, Context context) {
        super(list);
        activity = (MyCollectActivity) context;
    }

    public void addItem(com.quliantrip.qulian.domain.Test s) {
        list.add(s);
    }

    private boolean isEdit = false;

    public void setEdit(boolean b) {
        isEdit = b;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_my_collect_hot_good_item, null);
        }
        final Holder holder = Holder.getHolder(convertView);

        final com.quliantrip.qulian.domain.Test name = list.get(position);
        holder.checkDeledct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.addOrDelectCollect(name.ischeck(), "saasdf" + name.getName());
                name.setIscheck(!name.ischeck());
                notifyDataSetChanged();

            }
        });

        if (name.ischeck())
            holder.state.setImageResource(R.mipmap.cnb_wode_pre);
        else
            holder.state.setImageResource(R.mipmap.cnb_wode_nor);

        holder.slipRihtLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                holder.slipRihtLayout.layoutContent(isEdit);
            }
        });

        holder.city.setText(name.getName());
        return convertView;
    }

    static class Holder {
        @Bind(R.id.ll_is_checked_delect)
        LinearLayout checkDeledct;
        @Bind(R.id.srl_play_method_collect_item)
        SlipRihtLayout slipRihtLayout;
        @Bind(R.id.iv_collect_Img_state)
        ImageView state;

        @Bind(R.id.iv_hot_good_name)
        TextView city;


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