package com.quliantrip.qulian.adapter.choiceAdapter.playMethod;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.Time;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.adapter.choiceAdapter.good.OrderPlayMethodTypeAdapter;
import com.quliantrip.qulian.domain.choice.good.OrderSubmitBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodOrderSubmitBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.MyListView;
import com.quliantrip.qulian.view.dialog.PlayMethodSubBranchCheckDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 玩法 提交订单列表
 */
public class PlayMethodOrderGoodlistAdapter extends BasicAdapter<PlayMethodOrderSubmitBean.DataEntity> {
    private Context mContext;

    public PlayMethodOrderGoodlistAdapter(ArrayList<PlayMethodOrderSubmitBean.DataEntity> list, Context context) {
        super(list);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_play_method_order_every_good_check_item, null);
        }
        final Holder holder = Holder.getHolder(convertView);
        List<OrderSubmitBean.DataEntity.AttrssEntity> attressList;//有票的日期集合
        PlayMethodOrderSubmitBean.DataEntity bean = list.get(position);
        holder.name.setText(bean.getPlayitem().getName());

//    //初始化可选的套餐类型
        final ArrayList<PlayMethodOrderSubmitBean.DataEntity.AttributeEntity> listAttribute = (ArrayList<PlayMethodOrderSubmitBean.DataEntity.AttributeEntity>) bean.getAttribute();
        final OrderPlayMethodTypeAdapter orderGoodTypeAdapter = new OrderPlayMethodTypeAdapter(listAttribute);
        holder.typeListView.setAdapter(orderGoodTypeAdapter);
        holder.typeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String checkedId = listAttribute.get(position).getId();
                orderGoodTypeAdapter.setCheched(checkedId);
            }
        });
//    }

        //选择门店
//        public String skuId;
        final List<PlayMethodOrderSubmitBean.DataEntity.BranchnameEntity> branchnameList = bean.getBranchname();
        holder.checkSrore.setText(branchnameList.get(0).getName());
        convertView.findViewById(R.id.rl_check_store_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PlayMethodSubBranchCheckDialog dialog = new PlayMethodSubBranchCheckDialog(mContext, branchnameList, holder.checkSrore);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });

        //选着的数量的减少或添加
        convertView.findViewById(R.id.iv_good_person_number_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer newNumber = new Integer(holder.number.getText().toString().trim());
                newNumber = newNumber - 1;
                if (newNumber > 0) {
                    holder.number.setText(newNumber + "");
                } else {
                    ToastUtil.showToast(mContext, "人数不能少于0");
                }
            }
        });
        convertView.findViewById(R.id.iv_good_person_number_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer newNumber = new Integer(holder.number.getText().toString().trim());
                newNumber = newNumber + 1;
                holder.number.setText(newNumber + "");
            }
        });

        //选择日期
        convertView.findViewById(R.id.rl_preview_time_data_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Time time = new Time();
                time.setToNow();
                final int oldYear = time.year;
                final int month = time.month;
                final int day = time.monthDay;
                DatePickerDialog datePicker = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        holder.residueNumber.setText("");
                        //点击日期与现在时间对比
                        String checkData = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                        String oldData = (month + 1) + "/" + day + "/" + oldYear;
                        if (oldYear > year) {
                            ToastUtil.showToast(mContext, "选择时间不能是今天之前");
                            holder.pretime.setText(oldData);
                        } else {
                            if (oldYear == year) {
                                if (month > monthOfYear) {
                                    ToastUtil.showToast(mContext, "选择时间不能是今天之前");
                                    holder.pretime.setText(oldData);
                                } else {
                                    if (month == monthOfYear) {
                                        if (day > dayOfMonth) {
                                            ToastUtil.showToast(mContext, "选择时间不能是今天之前");
                                            holder.pretime.setText(oldData);
                                        } else {
//                                            setNumberInfo(checkData);
                                            holder.pretime.setText(checkData);
                                        }
                                    } else {
//                                        setNumberInfo(checkData);
                                        holder.pretime.setText(checkData);
                                    }
                                }
                            } else {
//                                setNumberInfo(checkData);
                                holder.pretime.setText(checkData);
                            }
                        }
                    }
                }, oldYear, month, day);
                datePicker.show();
            }
        });


        //选着服务时间
        convertView.findViewById(R.id.rl_serve_time_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Time time = new Time();
                time.setToNow();
                int minute = time.minute;
                int hour = time.hour;
                TimePickerDialog timeDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute < 10) {
                            holder.serveTime.setText(hourOfDay + " : 0" + minute);
                        } else {
                            holder.serveTime.setText(hourOfDay + " : " + minute);
                        }
                    }
                }, hour, minute, true);
                timeDialog.show();
            }
        });


        return convertView;
    }
    //设置票数的集合选取数量
//    private void setNumberInfo(Holder holder,String checkData) {
//        holder.pretime.setText(checkData);
//        if (attressList != null) {
//            for (OrderSubmitBean.DataEntity.AttrssEntity attress : attressList) {
//                if (attress.getDate().equals(checkData)) {
//                    if (!(Integer.valueOf(attress.getNum()) > 10))
//                        residueNumber.setText("剩余" + attress.getNum());
//                    else
//                        residueNumber.setText("有票");
//                    break;
//                }
//            }
//        }
//        if (TextUtils.isEmpty(residueNumber.getText())) {
//            residueNumber.setText("无票");
//        }
//    }

    static class Holder {
        @Bind(R.id.tv_good_name)
        TextView name;//商品的名称
        @Bind(R.id.tv_check_store_result_text)
        TextView checkSrore;//选择门店的中文名字
        @Bind(R.id.mlv_good_taocan_type)
        MyListView typeListView;//套餐的类型的列表
        @Bind(R.id.tv_good_check_person_number)
        TextView number;//选着的数量
        //点击日期的选择
        @Bind(R.id.tv_preview_time_data_text)
        TextView pretime;
        @Bind(R.id.tv_serve_time_text)
        TextView serveTime;
        @Bind(R.id.tv_order_buy_number_text)
        TextView residueNumber;

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
