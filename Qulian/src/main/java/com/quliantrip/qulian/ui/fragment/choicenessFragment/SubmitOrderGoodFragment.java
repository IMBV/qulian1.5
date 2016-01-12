package com.quliantrip.qulian.ui.fragment.choicenessFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.format.Time;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Qulian5 on 2016/1/8.
 */
public class SubmitOrderGoodFragment extends BaseFragment {
    @Bind(R.id.tv_good_check_person_number)
    TextView number;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.adapter_good_order_item, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initDate() {

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

    //进去支付界面
    @OnClick(R.id.bt_order_submi_topay)
    void toPay() {
        UIHelper.showPayMethod(mContext, null);
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

    @Bind(R.id.tv_check_store_result_text)
    TextView checkSrore;

    @OnClick(R.id.rl_check_store_setting)
    void checkStore() {
        ToastUtil.showToast(mContext, "请选择门店");
    }
}
