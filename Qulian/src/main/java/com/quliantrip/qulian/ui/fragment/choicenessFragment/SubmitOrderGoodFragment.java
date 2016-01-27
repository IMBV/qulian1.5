package com.quliantrip.qulian.ui.fragment.choicenessFragment;

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
import com.quliantrip.qulian.domain.choice.good.GoodOrderSubmitCheckBean;
import com.quliantrip.qulian.domain.choice.good.OrderSubmitBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;
import com.quliantrip.qulian.view.MyListView;
import com.quliantrip.qulian.view.dialog.SubBranchCheckDialog;

import java.util.ArrayList;
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
    @Bind(R.id.tv_good_name)
    TextView name;//商品的名称
    @Bind(R.id.mlv_good_taocan_type)
    MyListView typeListView;//套餐的类型的列表

    @Bind(R.id.tv_good_check_person_number)
    TextView number;//选着的数量
    @Bind(R.id.tv_order_buy_number_text)
    TextView residueNumber;
    private List<OrderSubmitBean.DataEntity.AttrssEntity> attressList;//有票的日期集合

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_choice_good_order_check, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "11");
        return new QuestBean(map, new OrderSubmitBean().setTag(getClass().getName()), HttpConstants.GOOD_ORDER);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            OrderSubmitBean orderSubmitBean = (OrderSubmitBean) bean;
            OrderSubmitBean.DataEntity dataEntity = orderSubmitBean.getData();
            if (orderSubmitBean.getCode() == 200) {
                name.setText(dataEntity.getOnline().getName());
                initListView(dataEntity.getAttribute());
                branchnameList = dataEntity.getBranchname();
                skuId = branchnameList.get(0).getId();
                checkSrore.setText(branchnameList.get(0).getName());
                attressList = dataEntity.getAttrss();
            } else {
                ToastUtil.showToast(mContext, orderSubmitBean.getMsg());
            }
        }
        if (bean != null && (this.getClass().getName() + "check").equals(bean.getTag())) {
            GoodOrderSubmitCheckBean goodOrserSubmitCheckBean = (GoodOrderSubmitCheckBean) bean;
            if (goodOrserSubmitCheckBean.getCode() == 200) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("proid", "11");
                map.put("date", "2/12/2015");
                map.put("key", "-14KirwNmSQQCMiuYBEXtJBWLllbs7Ma");
                map.put("total_price", "11111");
                map.put("num", "3");
                map.put("type", "1");
                map.put("price", "1111");
                map.put("service", "11:30");
                map.put("sku_id", "15");
                new PacketStringReQuest(HttpConstants.GOOD_ORDER_SUBMIT, new GoodOrderSubmitBean().setTag(getClass().getName() + "submit"), map);
            } else {
                ToastUtil.showToast(mContext, goodOrserSubmitCheckBean.getMsg());
            }
        }
        if (bean != null && (this.getClass().getName() + "submit").equals(bean.getTag())) {
            GoodOrderSubmitBean goodOrderSubmitBean = (GoodOrderSubmitBean) bean;
            if (goodOrderSubmitBean.getCode() == 200) {
                Bundle bundle =  new Bundle();
                bundle.putString("orderId",goodOrderSubmitBean.getData().getId()+"");
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
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("proid", "11");
//        map.put("date","2/12/2015");
//        map.put("num","12");
//        map.put("sku_id","15");
//        new PacketStringReQuest(HttpConstants.GOOD_ORDER_CHECK, new GoodOrderSubmitCheckBean().setTag(getClass().getName() + "check"), map);
        Bundle bundle =  new Bundle();
        bundle.putString("orderId","53");
        UIHelper.showOrderConfirm(mContext, bundle);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
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
            }
        });
    }

    //选择够吗数量
    @OnClick(R.id.iv_good_person_number_down)
    void downNumber() {
        Integer newNumber = new Integer(number.getText().toString().trim());
        newNumber = newNumber - 1;
        if (newNumber > 0) {
            number.setText(newNumber + "");
        } else {
            ToastUtil.showToast(mContext, "人数不能少于0");
        }
    }

    @OnClick(R.id.iv_good_person_number_add)
    void addNumber() {
        Integer newNumber = new Integer(number.getText().toString().trim());
        newNumber = newNumber + 1;
        number.setText(newNumber + "");
    }


    //点击日期的选择
    @Bind(R.id.tv_preview_time_data_text)
    TextView pretime;

    @OnClick(R.id.rl_preview_time_data_setting)
    void setPriviewTime() {
        Time time = new Time();
        time.setToNow();
        final int oldYear = time.year;
        final int month = time.month;
        final int day = time.monthDay;
        DatePickerDialog datePicker = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                residueNumber.setText("");
                //点击日期与现在时间对比
                String checkData = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                String oldData = (month + 1) + "/" + day + "/" + oldYear;
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
    }

    //设置票数的集合选取数量
    private void setNumberInfo(String checkData) {
        pretime.setText(checkData);
        if (attressList != null) {
            for (OrderSubmitBean.DataEntity.AttrssEntity attress : attressList) {
                if (attress.getDate().equals(checkData)) {
                    if (!(Integer.valueOf(attress.getNum()) > 10))
                        residueNumber.setText("剩余" + attress.getNum());
                    else
                        residueNumber.setText("有票");
                    break;
                }
            }
        }
        if (TextUtils.isEmpty(residueNumber.getText())) {
            residueNumber.setText("无票");
        }
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
                } else {
                    serveTime.setText(hourOfDay + " : " + minute);
                }
            }
        }, hour, minute, true);
        timeDialog.show();
    }

    //选择门店
    @Bind(R.id.tv_check_store_result_text)
    TextView checkSrore;//选择门店的中文名字

    public String skuId;
    private List<OrderSubmitBean.DataEntity.BranchnameEntity> branchnameList;

    @OnClick(R.id.rl_check_store_setting)
    void checkStore() {
        final SubBranchCheckDialog dialog = new SubBranchCheckDialog(mContext, branchnameList, checkSrore);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}
