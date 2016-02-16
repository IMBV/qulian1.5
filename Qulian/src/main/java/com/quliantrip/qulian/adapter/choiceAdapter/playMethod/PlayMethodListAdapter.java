package com.quliantrip.qulian.adapter.choiceAdapter.playMethod;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.view.CircleImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *玩法列表展示页
 */
public class PlayMethodListAdapter extends BasicAdapter<PlayMethodBean.DataEntity.PlayEntity> {

    public PlayMethodListAdapter(ArrayList<PlayMethodBean.DataEntity.PlayEntity> list) {
        super(list);
    }

    public void updataListView(ArrayList<PlayMethodBean.DataEntity.PlayEntity> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_play_list_item, null);
        }
        Holder holder = Holder.getHolder(convertView);

        //进行数据添加
        PlayMethodBean.DataEntity.PlayEntity bean = list.get(position);
        //添加玩法图片资源
        ImageLoader.getInstance().displayImage(bean.getImgs().split(",")[0], holder.img, ImageLoaderOptions.pager_options_big);
        //添加达人头像
        ImageLoader.getInstance().displayImage(bean.getHead_img(), holder.authoeImg, ImageLoaderOptions.pager_options);
//        if (bean.isIs_house()) {
//            holder.isCollect.setVisibility(View.VISIBLE);
//        } else {
//            holder.isCollect.setVisibility(View.GONE);
//        }
        holder.title.setText(bean.getTitle());
        holder.des.setText(bean.getSummary());
        holder.price.setText("￥"+bean.getMin_price());
        return convertView;
    }

    static class Holder {
        @Bind(R.id.iv_choice_paly_method_pic)
        ImageView img;
        @Bind(R.id.civ_expert_author_img)
        CircleImageView authoeImg;
        @Bind(R.id.tv_like_person_number)
        TextView likeNumber;
        @Bind(R.id.tv_play_method_title)
        TextView title;
        @Bind(R.id.tv_play_method_des)
        TextView des;
        @Bind(R.id.tv_play_method_dian_disount)
        TextView dianDisCount;
        @Bind(R.id.tv_play_method_price)
        TextView price;

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
