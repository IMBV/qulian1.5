package com.quliantrip.qulian.adapter.myAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.me.PlayMethodOrderBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 玩法订单中的单品的listView的条目
 */
public class PlayMethodOrderListGoodItemAdapter extends BasicAdapter<PlayMethodOrderBean.DataEntity.PalyEntity> {
    public PlayMethodOrderListGoodItemAdapter(ArrayList<PlayMethodOrderBean.DataEntity.PalyEntity> list) {
        super(list);
    }

    public void addItem(PlayMethodOrderBean.DataEntity.PalyEntity s) {
        list.add(s);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_my_play_order_list_good_detail_item, null);
        }

        PlayMethodOrderBean.DataEntity.PalyEntity bean = list.get(position);
        Holder holder = Holder.getHolder(convertView);
        ImageLoader.getInstance().displayImage(bean.getIcon_img(),holder.typeImg, ImageLoaderOptions.pager_options);
        holder.name.setText(bean.getTitle());
        holder.isUser.setText(bean.getIsorder());
        return convertView;
    }

    static class Holder {
        @Bind(R.id.iv_play_method_content_img)
        ImageView typeImg;//条目分类的图片

        @Bind(R.id.tv_play_method_order_good_name)
        TextView name;//条目的名称
        @Bind(R.id.tv_play_method_user_content)
        TextView isUser;

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
