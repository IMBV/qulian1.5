package com.quliantrip.qulian.ui.fragment.meFragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.ui.activity.SimpleBackActivity;
import com.quliantrip.qulian.util.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yuly on 2015/12/14.
 * www.quliantrip.com
 */
public class MySetting extends BaseFragment {
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

//    //退出当前用户
//    @OnClick(R.id.tv_my_setting_loginout) void loginout(){
//        QulianApplication.getInstance().cleanLoginInfo();
//        ((SimpleBackActivity)mContext).finish();
//    }
//    //修改密码
//    @OnClick(R.id.tv_my_setting_updatePaw) void updataPaw(){
//        ToastUtil.showToast(QulianApplication.getContext(),"暂时还没有实现");
//    }


    @OnClick(R.id.iv_good_person_number_down)
    void downNumber() {
        Integer newNumber = new Integer(number.getText().toString().trim());
        newNumber = newNumber - 1;
        number.setText(newNumber + "");
    }

    @OnClick(R.id.iv_good_person_number_add)
    void addNumber() {
        Integer newNumber = new Integer(number.getText().toString().trim());
        newNumber = newNumber + 1;
        number.setText(newNumber + "");
    }
}
