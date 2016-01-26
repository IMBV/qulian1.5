package com.quliantrip.qulian.ui.fragment.choicenessFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.OrderGoodTypeAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.OrderSubmitBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
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

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.adapter_good_order_item, null);
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
                System.out.println(branchnameList.size());
            } else {
                ToastUtil.showToast(mContext, orderSubmitBean.getMsg());
            }
        }
    }

    //初始化可选的套餐类型
    private void initListView(final List<OrderSubmitBean.DataEntity.AttributeEntity> attributeList) {
        final OrderGoodTypeAdapter orderGoodTypeAdapter = new OrderGoodTypeAdapter((ArrayList<OrderSubmitBean.DataEntity.AttributeEntity>) attributeList);
        typeListView.setAdapter(orderGoodTypeAdapter);
        typeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                orderGoodTypeAdapter.setCheched(attributeList.get(position).getAttrid());
            }
        });
    }

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

    //提交订单
    @OnClick(R.id.bt_order_submi_topay)
    void toConfirmOrder() {
        UIHelper.showOrderConfirm(mContext, null);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    @Bind(R.id.tv_preview_time_data_text)
    TextView pretime;

    @OnClick(R.id.rl_preview_time_data_setting)
    void setPriviewTime() {
        Time time = new Time();
        time.setToNow();
        int year = time.year;
        int month = time.month;
        int day = time.monthDay;
        DatePickerDialog datePicker = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                pretime.setText(year + " - " + (monthOfYear + 1) + " - " + dayOfMonth);
            }
        }, year, month, day);
        datePicker.show();
    }

    @Bind(R.id.tv_serve_time_text)
    TextView serveTime;

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
    private List<OrderSubmitBean.DataEntity.BranchnameEntity> branchnameList;
    @OnClick(R.id.rl_check_store_setting)
    void checkStore() {
        final SubBranchCheckDialog dialog = new SubBranchCheckDialog(mContext,branchnameList,checkSrore);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}
