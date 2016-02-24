package com.quliantrip.qulian.adapter.myAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.common.HintInfoBean;
import com.quliantrip.qulian.domain.me.GoodOrderListBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.good.GoodOrderFragment;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 商品订单列表页
 */
public class GoodOrderListAdapter extends BasicAdapter<GoodOrderListBean.DataEntity> {
    private Context mContext;
    private AlertDialog dialog;

    public GoodOrderListAdapter(ArrayList<GoodOrderListBean.DataEntity> list,Context context) {
        super(list);
        this.mContext = context;
    }

    private int delPosition;

    public void updataList(ArrayList<GoodOrderListBean.DataEntity> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void addItem(GoodOrderListBean.DataEntity s) {
        list.add(s);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_my_good_order_list_item, null);
        }

        Holder holder = Holder.getHolder(convertView);
        final GoodOrderListBean.DataEntity bean = list.get(position);

        //添加数据
        if (bean.getImgs() != null)
            ImageLoader.getInstance().displayImage(bean.getImgs().split(",")[0], holder.img, ImageLoaderOptions.pager_options);
        holder.name.setText(bean.getName());
        holder.ePrice.setText("￥" + bean.getPrice());//单价
        holder.totalPrice.setText(bean.getTotal_price());//总的价格
        holder.taocan.setText(bean.getPackageX());//套餐
        holder.num.setText("×" + bean.getNum());//数量
        holder.orderNumber.setText(bean.getOrder_sn());//编号
        //设置点击事件
        convertView.findViewById(R.id.bt_good_order_see_consume).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(QulianApplication.getContext(), "稍后添加查看优惠券的功能");
            }
        });

        if (bean.getPay_status().equals("0")&&bean.getOrder_status().equals("0")&&bean.getIs_use().equals("0")
                ||bean.getPay_status().equals("1")&&bean.getOrder_status().equals("0")&&bean.getIs_use().equals("0")
                ||bean.getPay_status().equals("1")&&bean.getOrder_status().equals("1")&&bean.getIs_use().equals("1")){
            convertView.findViewById(R.id.bt_good_order_cancel).setVisibility(View.VISIBLE);
            convertView.findViewById(R.id.bt_good_order_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("id", bean.getId());
                    delPosition = position;
                    new PacketStringReQuest(HttpConstants.MY_ORDER_GOOD_CANCEL, new HintInfoBean().setTag(GoodOrderFragment.class.getName() + "delOrder"), map);
                }
            });
        }else{
            holder.stype.setText("已关闭");
            convertView.findViewById(R.id.bt_good_order_cancel).setVisibility(View.GONE);
        }
//        if (bean.getAfter_sale().equals("0")) {
//            holder.stype.setText("正常");
//            convertView.findViewById(R.id.bt_good_order_cancel).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Map<String, String> map = new HashMap<String, String>();
//                    map.put("id", bean.getId());
//                    delPosition = position;
//                    new PacketStringReQuest(HttpConstants.MY_ORDER_GOOD_CANCEL, new HintInfoBean().setTag(GoodOrderFragment.class.getName() + "delOrder"), map);
//                }
//            });
//            convertView.findViewById(R.id.bt_good_order_cancel).setVisibility(View.VISIBLE);
//        }else {
//            holder.stype.setText("已关闭");
//            convertView.findViewById(R.id.bt_good_order_cancel).setVisibility(View.GONE);
//        }

        //设置订单的状态
        holder.stype.setText(bean.getIsorder());
        convertView.findViewById(R.id.bt_good_order_link_daren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCancelable(true);
                View view = View.inflate(mContext, R.layout.layout_find_into_qun_grounp, null);
                dialog = builder.create();
                dialog.setView(view, 0, 0, 0, 0);
                dialog.show();
            }
        });

        return convertView;
    }

    static class Holder {
        @Bind(R.id.iv_good_detail_img)
        ImageView img;
        @Bind(R.id.tv_good_detail_name)
        TextView name;
        @Bind(R.id.tv_good_order_total_price)
        TextView totalPrice;
        @Bind(R.id.tv_good_order_taocan)
        TextView taocan;
        @Bind(R.id.tv_good_order_every_price)
        TextView ePrice;
        @Bind(R.id.tv_good_order_num)
        TextView num;
        @Bind(R.id.tv_order_number_info)
        TextView orderNumber;

        //订单状态
        @Bind(R.id.tv_order_user_stype)
        TextView stype;

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
