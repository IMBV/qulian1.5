package com.quliantrip.qulian.ui.fragment.meFragment.searchBackPassword;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseDialogFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.common.HintInfoBean;
import com.quliantrip.qulian.domain.me.SearchBackPhoneBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.TimeCountUtil;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手机找回密码获取验证码
 */
public class SearchBackGetCodeFragment  extends BaseDialogFragment {

    private View view;

    //手机验证下一次的时间
    @Bind(R.id.bt_get_phone_check)
    Button phone_check;

    @Bind(R.id.bt_searck_back_password_phone)
    ClearEditText phoneNum;
    @Bind(R.id.bt_searck_back_password_phone_check_code)
    ClearEditText checkNum;
    @Bind(R.id.bt_submit_search_back_password_phone)
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_search_back_password_phone_get_code, null);
        ButterKnife.bind(this, view);
        button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_sub_bg));
        initListener();
        return view;
    }


    private void initListener() {
        phoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(phoneNum.getText())) {
                    button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_sub_bg));
                    button.setTextColor(CommonHelp.getColor(R.color.login_bg_color));
                } else {
                    button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_collor));
                    button.setTextColor(CommonHelp.getColor(R.color.colorPrimary));
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onEventMainThread(BaseJson bean) {
        //找回密码数据的请求
        if (bean != null && (this.getClass().getName() + "getCode").equals(bean.getTag())) {
            HintInfoBean hintInfoBean = (HintInfoBean) bean;
            ToastUtil.showToast(mContext, hintInfoBean.getMsg());
        }
    }

    //提交验证
    @OnClick(R.id.bt_submit_search_back_password_phone)
    void searchBackPasswordSubmit() {
        String phone = phoneNum.getText().toString().trim();
        String num = checkNum.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast(mContext, "请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(num)) {
            ToastUtil.showToast(mContext, "请输入验证码");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("tel", phone);
        map.put("code", num);
        new PacketStringReQuest(HttpConstants.USER_BACK_PAS_PHONE_CHECK, new SearchBackPhoneBean().setTag(SearchBackPhoneFragment.class.getName()+"checkCode"), map);
    }

    //手机号码的验证
    @OnClick(R.id.bt_get_phone_check)
    void phoneCheck() {
        String phone = phoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast(mContext, "请输入手机号");
            return;
        }

        if (!CommonHelp.isMobileNO(phone)) {
            ToastUtil.showToast(mContext, "请输入正确的手机号");
            return;
        }
        TimeCountUtil timeCountUtil = new TimeCountUtil((Activity) mContext, 60000, 1000, phone_check);
        timeCountUtil.start();
        Map<String, String> map = new HashMap<>();
        map.put("tel", phone);
        new PacketStringReQuest(HttpConstants.USER_BACK_PAS_PHONE, new HintInfoBean().setTag(getClass().getName()+"getCode"), map);
    }

    public String getPhone(){
       return phoneNum.getText().toString().trim();
    }
}
