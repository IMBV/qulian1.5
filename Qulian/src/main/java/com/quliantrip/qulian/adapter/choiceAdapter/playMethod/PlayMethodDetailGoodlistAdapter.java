package com.quliantrip.qulian.adapter.choiceAdapter.playMethod;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodDetailBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.util.CommonHelp;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 玩法 单品列表
 */
public class PlayMethodDetailGoodlistAdapter extends BasicAdapter<PlayMethodDetailBean.DataEntity.PackageEntity> {

    public PlayMethodDetailGoodlistAdapter(ArrayList<PlayMethodDetailBean.DataEntity.PackageEntity> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_play_method_detail_good_item, null);
        }
        final Holder holder = Holder.getHolder(convertView);
        holder.playMethod.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                holder.playMethod.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height = holder.playMethod.getHeight();
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.line.getLayoutParams();
                params.height  = height - CommonHelp.dip2px(QulianApplication.getContext(), (float) 42.5);
                holder.line.setLayoutParams(params);
            }
        });
        if (position == list.size()-1){
            holder.end.setVisibility(View.VISIBLE);
        }else{
            holder.end.setVisibility(View.GONE);
        }
        final PlayMethodDetailBean.DataEntity.PackageEntity bean = list.get(position);
//        ImageLoader.getInstance().displayImage((String) bean.getImages(), holder.img, ImageLoaderOptions.pager_options);
        holder.name.setText(bean.getTitle());
        if (bean.getMerchantname() == null||TextUtils.isEmpty(bean.getMerchantname())){
            holder.taocao.setVisibility(View.GONE);
        }else{
            holder.taocao.setVisibility(View.VISIBLE);
            holder.taocao.setText("(" + bean.getMerchantname() + ")");
        }

//        holder.des.setText(bean.getReason());
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_play_method_detail_name)
        TextView name;
        @Bind(R.id.tv_play_method_detail_taocan)
        TextView taocao;
        @Bind(R.id.tv_play_method_detail_des)
        TextView des;
        @Bind(R.id.tv_play_method_detail_price)
        TextView price;


        @Bind(R.id.ll_paly_method_good)
        LinearLayout playMethod;
        @Bind(R.id.v_item_line)
        View line;
        @Bind(R.id.iv_list_end)
        ImageView end;

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
