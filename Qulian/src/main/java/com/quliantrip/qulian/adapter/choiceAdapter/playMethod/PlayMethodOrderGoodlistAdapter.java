package com.quliantrip.qulian.adapter.choiceAdapter.playMethod;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.TextUtils;
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
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodOrderSubmitItemBean;
import com.quliantrip.qulian.domain.choice.good.OrderSubmitBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodOrderSubmitBean;
import com.quliantrip.qulian.domain.common.HintInfoBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.playMethod.SubmitOrderPlayMethodFragment;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.MyListView;
import com.quliantrip.qulian.view.dialog.PlayMethodSubBranchCheckDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 玩法 提交订单列表
 */
public class PlayMethodOrderGoodlistAdapter extends BasicAdapter<PlayMethodOrderSubmitBean.DataEntity> {
    private Context mContext;
    private HashMap<Integer, PlayMethodOrderSubmitItemBean> resuleMap;
    private SubmitOrderPlayMethodFragment submitOrderPlayMethodFragment;

    public HashMap<Integer, PlayMethodOrderSubmitItemBean> getResuleMap() {
        return resuleMap;
    }

    public PlayMethodOrderGoodlistAdapter(ArrayList<PlayMethodOrderSubmitBean.DataEntity> list, Context context,SubmitOrderPlayMethodFragment submitOrderPlayMethodFragment) {
        super(list);
        mContext = context;
        resuleMap = new HashMap<>();
        this.submitOrderPlayMethodFragment = submitOrderPlayMethodFragment;
        initResultMap();
    }

    private void initResultMap() {
        for (int i = 0; i < list.size(); i++) {
            PlayMethodOrderSubmitBean.DataEntity dataEntity = list.get(i);
            String playid = dataEntity.getPlayitem().getPlayitemsid();//玩法条目的id
            String sku_id = dataEntity.getAttribute().get(0).getId();//玩法条目套餐的id
            String date = "";
            String dataString = "所有时间都没有票";
            String price = "0.00";
            if (dataEntity.getAttrss().size() > 0) {
                date = dataEntity.getAttrss().get(0).getDate();//订单的日期
                dataString = dataEntity.getAttrss().get(0).getDe();//日期字符串
                price = dataEntity.getAttrss().get(0).getProce();//价格
            }
            String num = "1";//购买数量
            String service = "11:00";//服务时间
            String store = "";
            String storeNameString = "";
            if (dataEntity.getBranchname().size() > 0) {
                store = dataEntity.getBranchname().get(0).getId();//商店的id
                storeNameString = dataEntity.getBranchname().get(0).getName();
            }
            resuleMap.put(i, new PlayMethodOrderSubmitItemBean(playid, sku_id, date, dataString, num, service, store, price, storeNameString));
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_play_method_order_every_good_check_item, null);
        }
        final Holder holder = Holder.getHolder(convertView);

        //进行数据适配的对象
        final PlayMethodOrderSubmitBean.DataEntity bean = list.get(position);
        List<PlayMethodOrderSubmitBean.DataEntity.AttrssEntity> attressList = bean.getAttrss();//有票的日期集合
        final PlayMethodOrderSubmitItemBean playMethodOrderSubmitItemBean = resuleMap.get(position);
        holder.name.setText(bean.getPlayitem().getTitle());

        //初始化可选的套餐类型
        final ArrayList<PlayMethodOrderSubmitBean.DataEntity.AttributeEntity> listAttribute = (ArrayList<PlayMethodOrderSubmitBean.DataEntity.AttributeEntity>) bean.getAttribute();
        final OrderPlayMethodTypeAdapter orderGoodTypeAdapter = new OrderPlayMethodTypeAdapter(listAttribute);
        holder.typeListView.setAdapter(orderGoodTypeAdapter);
        holder.typeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String checkedId = listAttribute.get(position).getId();
                orderGoodTypeAdapter.setCheched(checkedId);
                playMethodOrderSubmitItemBean.setSku_id(checkedId);
            }
        });

        //选择门店
        final List<PlayMethodOrderSubmitBean.DataEntity.BranchnameEntity> branchnameList = bean.getBranchname();
        holder.checkSrore.setText(playMethodOrderSubmitItemBean.getStoreNameString());
        if (branchnameList.size() > 0)
            convertView.findViewById(R.id.rl_check_store_setting).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final PlayMethodSubBranchCheckDialog dialog = new PlayMethodSubBranchCheckDialog(mContext, branchnameList, holder.checkSrore, playMethodOrderSubmitItemBean);
                    dialog.setCancelable(true);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.show();
                }
            });

        holder.number.setText("1");
        //选着的数量的减少或添加
        convertView.findViewById(R.id.iv_good_person_number_down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer newNumber = new Integer(holder.number.getText().toString().trim());
                newNumber = newNumber - 1;
                if (newNumber > 0) {
                    playMethodOrderSubmitItemBean.setNum(newNumber + "");
                    holder.number.setText(newNumber + "");
                    playMethodOrderSubmitItemBean.setNum(newNumber + "");
                    submitOrderPlayMethodFragment.getTotalPrice();
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
                playMethodOrderSubmitItemBean.setNum(newNumber + "");
                holder.number.setText(newNumber + "");
                playMethodOrderSubmitItemBean.setNum(newNumber + "");
                submitOrderPlayMethodFragment.getTotalPrice();
            }
        });

        holder.pretime.setText(playMethodOrderSubmitItemBean.getDateString());
        if (attressList != null) {
            //选择日期
            convertView.findViewById(R.id.rl_preview_time_data_setting).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dateSring = playMethodOrderSubmitItemBean.getDateString();
                    String[] dateArray = dateSring.split("-");
                    final int oldYear = Integer.valueOf(dateArray[0]);
                    final int month = Integer.valueOf(dateArray[1]) - 1;
                    final int day = Integer.valueOf(dateArray[2]);

                    DatePickerDialog datePicker = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            holder.residueNumber.setText("");
                            //点击日期与现在时间对比
                            String monthString = monthOfYear + "";
                            String dayString = dayOfMonth + "";
                            if ((monthOfYear + 1) < 10)
                                monthString = "0" + (monthOfYear + 1);
                            if (dayOfMonth < 10)
                                dayString = "0" + dayOfMonth;
                            String checkData = year + "-" + monthString + "-" + dayString;
                            String oldData = playMethodOrderSubmitItemBean.getDateString();
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
                                                setNumberInfo(holder, checkData, playMethodOrderSubmitItemBean, bean.getAttrss());
                                            }
                                        } else {
                                            setNumberInfo(holder, checkData, playMethodOrderSubmitItemBean, bean.getAttrss());
                                        }
                                    }
                                } else {
                                    setNumberInfo(holder, checkData, playMethodOrderSubmitItemBean, bean.getAttrss());
                                }
                            }
                        }
                    }, oldYear, month, day);
                    datePicker.show();
                }
            });
        } else {
            ToastUtil.showToast(mContext, "所有时间都无票");
        }

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
                        String time;
                        if (minute < 10) {
                            time = hourOfDay + " : 0" + minute;
                        } else {
                            time = hourOfDay + " : " + minute;
                        }
                        holder.serveTime.setText(time);
                        playMethodOrderSubmitItemBean.setService(time);
                    }
                }, hour, minute, true);
                timeDialog.show();
            }
        });
        return convertView;
    }

    //设置票数的集合选取数量
    private void setNumberInfo(Holder holder, String checkData,
                               PlayMethodOrderSubmitItemBean playMethodOrderSubmitItemBean,
                               List<PlayMethodOrderSubmitBean.DataEntity.AttrssEntity> attrss) {
        holder.pretime.setText(checkData);
        if (attrss != null) {
            for (PlayMethodOrderSubmitBean.DataEntity.AttrssEntity attress : attrss) {
                if (attress.getDe().equals(checkData)) {
                    if (!(Integer.valueOf(attress.getNum()) > 10))
                        holder.residueNumber.setText("剩余" + attress.getNum());
                    else
                        holder.residueNumber.setText("有票");
                    playMethodOrderSubmitItemBean.setDate(attress.getDate());
                    playMethodOrderSubmitItemBean.setPrice(attress.getProce());
                    submitOrderPlayMethodFragment.getTotalPrice();
                    break;
                }
            }
        }
        if (TextUtils.isEmpty(holder.residueNumber.getText())) {
            holder.residueNumber.setText("无票");
        }
    }

    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

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

    //公共检测的方法
    private void checkTicketNumber(int pos) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("date", resuleMap.get(pos).getDate());
        map.put("num", resuleMap.get(pos).getNum());
        map.put("sku_id", resuleMap.get(pos).getSku_id());
        new PacketStringReQuest(HttpConstants.PLAY_METHOD_ORDER_SUBMIT,
                new HintInfoBean().setTag(SubmitOrderPlayMethodFragment.class.getName() + "check"), map);

    }
}
