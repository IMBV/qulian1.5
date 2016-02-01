package com.quliantrip.qulian.adapter.choiceAdapter.good;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.choice.good.GoodDetailBean;
import com.quliantrip.qulian.domain.choice.good.OrderSubmitBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 选着门店
 */
public class GoodDetailBranchCheckAdapter extends BasicAdapter<GoodDetailBean.DataEntity.BranchEntity> {
    public GoodDetailBranchCheckAdapter(ArrayList<GoodDetailBean.DataEntity.BranchEntity> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_dialog_subbranch_check_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        final GoodDetailBean.DataEntity.BranchEntity bean = list.get(position);
        ImageLoader.getInstance().displayImage(bean.getName().getImages(), holder.img, ImageLoaderOptions.pager_options);
        holder.name.setText(bean.getName().getName());
        holder.address.setText(bean.getName().getAddress());
        return convertView;
    }

    static class Holder {
        @Bind(R.id.iv_sub_branch_img)
        ImageView img;
        @Bind(R.id.iv_sub_branch_name)
        TextView name;
        @Bind(R.id.iv_sub_branch_address)
        TextView address;

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
