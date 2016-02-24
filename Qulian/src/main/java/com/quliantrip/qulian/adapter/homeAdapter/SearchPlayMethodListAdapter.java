package com.quliantrip.qulian.adapter.homeAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.home.SecnicPlayResultBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.view.CircleImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 搜索结果的数据的适配
 */
public class SearchPlayMethodListAdapter extends BasicAdapter<SecnicPlayResultBean.DataEntity.RuleEntity> {

    public SearchPlayMethodListAdapter(ArrayList<SecnicPlayResultBean.DataEntity.RuleEntity> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_play_list_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        SecnicPlayResultBean.DataEntity.RuleEntity bean = list.get(position);
        ImageLoader.getInstance().displayImage(bean.getImgs().split(",")[0], holder.img, ImageLoaderOptions.pager_options_big);
        ImageLoader.getInstance().displayImage(bean.getHead_img(), holder.authoeImg, ImageLoaderOptions.pager_options);
        holder.title.setText(bean.getTitle());
        holder.des.setText(bean.getSummary());
        holder.likeNumber.setText("有"+bean.getBuynum()+"这样玩");
        holder.dianDisCount.setText(bean.getRegion());
        holder.price.setText("￥"+bean.getProce());
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
