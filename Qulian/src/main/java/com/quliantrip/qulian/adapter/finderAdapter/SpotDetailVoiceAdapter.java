package com.quliantrip.qulian.adapter.finderAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.find.SpotDetailBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 景点详情语音播放的列表的数据适配
 */
public class SpotDetailVoiceAdapter extends BasicAdapter<SpotDetailBean.DataEntity.VoicInfoEntity> {

    public SpotDetailVoiceAdapter(ArrayList<SpotDetailBean.DataEntity.VoicInfoEntity> list) {
        super(list);
    }

    //设置选中的播放的条目
    private int checkId = -1;

    public void setCheckId(int id) {
        checkId = id;
        notifyDataSetChanged();
    }

    public void updataList(ArrayList<SpotDetailBean.DataEntity.VoicInfoEntity> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_spot_detail_voice_play_item, null);
        }

        //获取数据时适配的对象和要添加的控件
        Holder holder = Holder.getHolder(convertView);
        SpotDetailBean.DataEntity.VoicInfoEntity bean = list.get(position);
//
//        if (ImageLoader.getInstance().getLoadingUriForView(holder.img) != null)
//            if (!ImageLoader.getInstance().getLoadingUriForView(holder.img).equals(bean.getImg_url().split(",")[0].trim()))
                ImageLoader.getInstance().displayImage(bean.getImg_url().split(",")[0].trim(), holder.img, ImageLoaderOptions.pager_options);

        //添加数据
        holder.name.setText(bean.getName());
        if (checkId == position) {
            holder.isPalyImg.setImageResource(R.mipmap.icon_paly);
        } else {
            holder.isPalyImg.setImageResource(R.mipmap.icon_play_h);
        }
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_spot_detai_voice_name)
        TextView name;
        @Bind(R.id.iv_spot_detail_voice_img)
        ImageView img;
        @Bind(R.id.iv_spot_detail_voice_is_play)
        ImageView isPalyImg;

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
