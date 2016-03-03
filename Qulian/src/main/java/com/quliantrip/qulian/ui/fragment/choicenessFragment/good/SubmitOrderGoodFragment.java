package com.quliantrip.qulian.ui.fragment.choicenessFragment.good;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.good.OrderGoodTypeAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.good.GoodOrderSubmitBean;
import com.quliantrip.qulian.domain.choice.good.OrderSubmitBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodOrderSubmitItemBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;
import com.quliantrip.qulian.view.MyListView;
import com.quliantrip.qulian.view.dialog.SubBranchCheckDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品提交订单
 */
public class SubmitOrderGoodFragment extends BasePageCheckFragment {
    //提交订单的信息
    private PlayMethodOrderSubmitItemBean playMethodOrderSubmitItemBean;
    private String goodId;//单品的id
    private List<OrderSubmitBean.DataEntity.AttrssEntity> attressList;//有票的日期集合

    @Bind(R.id.tv_good_name)
    TextView name;//商品的名称
    @Bind(R.id.mlv_good_taocan_type)
    MyListView typeListView;//套餐的类型的列表
    @Bind(R.id.tv_good_check_person_number)
    TextView number;//选着的数量
    @Bind(R.id.tv_order_buy_number_text)
    TextView residueNumber;
    @Bind(R.id.iv_good_collect_text)
    TextView totalPrice;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_choice_good_order_check, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        goodId = getArguments().getString("goodId");
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", goodId);
        return new QuestBean(map, new OrderSubmitBean().setTag(getClass().getName()), HttpConstants.GOOD_ORDER);
    }

    //点击日期的选择
    private String dataString = "请选择日期";
    @Bind(R.id.tv_preview_time_data_text)
    TextView pretime;

    @OnClick(R.id.rl_preview_time_data_setting)
    void setPriviewTime() {
        if (attressList.size() > 0) {
            String[] dateArray = dataString.split("-");
            Calendar now = Calendar.getInstance();
            final int oldYear = Integer.valueOf(dateArray[0]);
            final int month = Integer.valueOf(dateArray[1]) - 1;
            final int day = Integer.valueOf(dateArray[2]);
//            final int oldYear = now.get(Calendar.YEAR);
//            final int month = now.get(Calendar.MONTH);
//            final int day = now.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePicker = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    residueNumber.setText("");
                    //点击日期与现在时间对比
                    String monthString = monthOfYear + "";
                    String dayString = dayOfMonth + "";
                    if ((monthOfYear + 1) < 10)
                        monthString = "0" + (monthOfYear + 1);
                    if (dayOfMonth < 10)
                        dayString = "0" + dayOfMonth;

                    String checkData = year + "-" + monthString + "-" + dayString;
                    String oldData = dataString;
                    if (oldYear > year) {
                        ToastUtil.showToast(mContext, "选择时间不能是今天之前");
                        pretime.setText(oldData);
                    } else {
                        if (oldYear == year) {
                            if (month > monthOfYear) {
                                ToastUtil.showToast(mContext, "选择时间不能是今天之前");
                                pretime.setText(oldData);
                            } else {
                                if (month == monthOfYear) {
                                    if (day > dayOfMonth) {
                                        ToastUtil.showToast(mContext, "选择时间不能是今天之前");
                                        pretime.setText(oldData);
                                    } else {
                                        setNumberInfo(checkData);
                                    }
                                } else {
                                    setNumberInfo(checkData);
                                }
                            }
                        } else {
                            setNumberInfo(checkData);
                        }
                    }
                }
            }, oldYear, month, day);
            datePicker.show();
        } else {
            ToastUtil.showToast(mContext, "所有时间都无票");
        }
    }

    //设置票数的集合选取数量
    private void setNumberInfo(String checkData) {
        pretime.setText(checkData);
        if (attressList != null) {
            for (OrderSubmitBean.DataEntity.AttrssEntity attress : attressList) {
                if (attress.getDe().equals(checkData)) {
                    if (!(Integer.valueOf(attress.getNum()) > 10))
                        residueNumber.setText("剩余" + attress.getNum());
                    else
                        residueNumber.setText("有票");
                    //点击的操作
                    playMethodOrderSubmitItemBean.setDate(attress.getDate());
                    playMethodOrderSubmitItemBean.setPrice(attress.getProce());
                    totalPrice.setText("￥" + playMethodOrderSubmitItemBean.getTotalPrice());
                    break;
                }
            }
        }
        if (TextUtils.isEmpty(residueNumber.getText())) {
            residueNumber.setText("无票");
        }
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            OrderSubmitBean orderSubmitBean = (OrderSubmitBean) bean;
            OrderSubmitBean.DataEntity dataEntity = orderSubmitBean.getData();
            if (orderSubmitBean.getCode() == 200) {
                String playid = dataEntity.getOnline().getId();//玩法条目的id
                String sku_id = dataEntity.getAttribute().get(0).getId();//玩法条目套餐的id
                String date = null;
                String price = "0";
                if (dataEntity.getAttrss().size() > 0) {
                    setNumberInfo(dataEntity.getAttrss().get(0).getDate());
                    date = dataEntity.getAttrss().get(0).getDate();//订单的日期
                    dataString = dataEntity.getAttrss().get(0).getDe();//
                    attressList = dataEntity.getAttrss();
                    price = dataEntity.getAttrss().get(0).getProce();//价格
                } else {
                    residueNumber.setText("所有日期都没有票");
                }
                String num = "1";//购买数量
                String service = "11:00";//服务时间
                String store = "";
                if (dataEntity.getBranchname().size() > 0) {
                    store = dataEntity.getBranchname().get(0).getId();//商店的id
                    branchnameList = dataEntity.getBranchname();
                    checkSrore.setText(branchnameList.get(0).getName());
                } else {
                    checkSrore.setText("没有课选门店");
                }

                playMethodOrderSubmitItemBean = new PlayMethodOrderSubmitItemBean(playid, sku_id, date, dataString, num, service, store, price);
                if (dataEntity.getAttrss().size() > 0)
                    setNumberInfo(dataEntity.getAttrss().get(0).getDe());
                //设置选择日期
                name.setText(dataEntity.getOnline().getName());
                dataString = playMethodOrderSubmitItemBean.getDateString();
                pretime.setText(dataString);
                totalPrice.setText("￥" + playMethodOrderSubmitItemBean.getTotalPrice());
                initListView(dataEntity.getAttribute());
            } else {
                ToastUtil.showToast(mContext, orderSubmitBean.getMsg());
            }
        }

        if (bean != null && (this.getClass().getName() + "goodSubmit").equals(bean.getTag())) {
            GoodOrderSubmitBean goodOrderSubmitBean = (GoodOrderSubmitBean) bean;
            if (goodOrderSubmitBean.getCode() == 200) {
                Bundle bundle = new Bundle();
                bundle.putString("orderId", goodOrderSubmitBean.getData().getId() + "");
                bundle.putString("orderSn", goodOrderSubmitBean.getData().getOrder_sn() + "");
                UIHelper.showOrderConfirm(mContext, bundle);
                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
            } else {
                ToastUtil.showToast(mContext, goodOrderSubmitBean.getMsg());
            }
        }
    }

    //提交订单
    @OnClick(R.id.bt_order_submi_topay)
    void toConfirmOrder() {
        if (playMethodOrderSubmitItemBean.getDateString().equals("请选择日期")) {
            ToastUtil.showToast(mContext, "请选择日期");
        } else {
            Map<String, String> map = new HashMap<String, String>();
            map.put("proid", goodId);
            map.put("date", playMethodOrderSubmitItemBean.getDate() == null ? null : playMethodOrderSubmitItemBean.getDate());
            map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
            map.put("total_price", playMethodOrderSubmitItemBean.getTotalPrice() == null ? "" : playMethodOrderSubmitItemBean.getTotalPrice());
            map.put("num", playMethodOrderSubmitItemBean.getNum() == null ? "" : playMethodOrderSubmitItemBean.getNum());
            map.put("type", "1");
            map.put("price", playMethodOrderSubmitItemBean.getPrice() == null ? "" : playMethodOrderSubmitItemBean.getPrice());
            map.put("service", playMethodOrderSubmitItemBean.getService() == null ? "" : playMethodOrderSubmitItemBean.getService());
            map.put("sku_id", playMethodOrderSubmitItemBean.getSku_id() == null ? "" : playMethodOrderSubmitItemBean.getSku_id());
            map.put("store", playMethodOrderSubmitItemBean.getStore() == null ? "" : playMethodOrderSubmitItemBean.getStore());
            new PacketStringReQuest(HttpConstants.GOOD_ORDER_SUBMIT, new GoodOrderSubmitBean().setTag(getClass().getName() + "goodSubmit"), map);
        }
    }

    //初始化可选的套餐类型
    private void initListView(final List<OrderSubmitBean.DataEntity.AttributeEntity> attributeList) {
        final OrderGoodTypeAdapter orderGoodTypeAdapter = new OrderGoodTypeAdapter((ArrayList<OrderSubmitBean.DataEntity.AttributeEntity>) attributeList);
        typeListView.setAdapter(orderGoodTypeAdapter);
        typeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String checkedId = attributeList.get(position).getId();
                orderGoodTypeAdapter.setCheched(checkedId);
                playMethodOrderSubmitItemBean.setSku_id(checkedId);
            }
        });
    }

    //选择数量
    @OnClick(R.id.iv_good_person_number_down)
    void downNumber() {
        Integer newNumber = new Integer(number.getText().toString().trim());
        newNumber = newNumber - 1;
        if (newNumber > 0) {
            number.setText(newNumber + "");
            playMethodOrderSubmitItemBean.setNum(newNumber + "");
            playMethodOrderSubmitItemBean.setNum(newNumber + "");
            totalPrice.setText("￥" + playMethodOrderSubmitItemBean.getTotalPrice());
        } else {
            ToastUtil.showToast(mContext, "人数不能少于0");
        }
    }

    @OnClick(R.id.iv_good_person_number_add)
    void addNumber() {
        Integer newNumber = new Integer(number.getText().toString().trim());
        newNumber = newNumber + 1;
        number.setText(newNumber + "");
        playMethodOrderSubmitItemBean.setNum(newNumber + "");
        playMethodOrderSubmitItemBean.setNum(newNumber + "");
        totalPrice.setText("￥" + playMethodOrderSubmitItemBean.getTotalPrice());
    }


    @Bind(R.id.tv_serve_time_text)
    TextView serveTime;

    //单击时间的选择
    @OnClick(R.id.rl_serve_time_setting)
    void setServeTime() {
        Time time = new Time();
        time.setToNow();
        int minute = time.minute;
        int hour = time.hour;
        TimePickerDialog timeDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (minute < 10) {
                    serveTime.setText(hourOfDay + " : 0" + minute);
                    playMethodOrderSubmitItemBean.setService(hourOfDay + " : 0" + minute);
                } else {
                    serveTime.setText(hourOfDay + " : " + minute);
                    playMethodOrderSubmitItemBean.setService(hourOfDay + " : " + minute);
                }
            }
        }, hour, minute, true);
        timeDialog.show();
    }

    //选择门店
    @Bind(R.id.tv_check_store_result_text)
    TextView checkSrore;//选择门店的中文名字
    private List<OrderSubmitBean.DataEntity.BranchnameEntity> branchnameList = null;

    @OnClick(R.id.rl_check_store_setting)
    void checkStore() {
        if (branchnameList != null) {
            final SubBranchCheckDialog dialog = new SubBranchCheckDialog(mContext, branchnameList, checkSrore, playMethodOrderSubmitItemBean);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
        } else {
            ToastUtil.showToast(mContext, "没有可以选门店");
        }
    }

}

//这里是进行票数检验的数据适配对象
//        if (bean != null && (this.getClass().getName() + "check").equals(bean.getTag())) {
//            GoodOrderSubmitCheckBean goodOrserSubmitCheckBean = (GoodOrderSubmitCheckBean) bean;
//            if (goodOrserSubmitCheckBean.getCode() == 200) {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("proid", "11");
//                map.put("date", playMethodOrderSubmitItemBean.getDate());
//                map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
//                map.put("total_price", playMethodOrderSubmitItemBean.getPrice());
//                map.put("num", playMethodOrderSubmitItemBean.getNum());
//                map.put("type", "1");
//                map.put("price", playMethodOrderSubmitItemBean.getPrice());
//                map.put("service", playMethodOrderSubmitItemBean.getService());
//                map.put("sku_id", playMethodOrderSubmitItemBean.getSku_id());
//                new PacketStringReQuest(HttpConstants.GOOD_ORDER_SUBMIT, new GoodOrderSubmitBean().setTag(getClass().getName() + "submit"), map);
//            } else {
//                ToastUtil.showToast(mContext, goodOrserSubmitCheckBean.getMsg());
//            }
//        }