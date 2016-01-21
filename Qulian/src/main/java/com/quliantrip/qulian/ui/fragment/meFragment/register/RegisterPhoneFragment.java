package com.quliantrip.qulian.ui.fragment.meFragment.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseDialogFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.HomeBean;
import com.quliantrip.qulian.domain.MoBileBean;
import com.quliantrip.qulian.domain.UserInfoBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.ui.activity.SimpleBackActivity;
import com.quliantrip.qulian.ui.activity.mainAcivity.MainActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.TimeCountUtil;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by yuly on 2016/1/5.
 * 手机注册
 */
public class RegisterPhoneFragment extends BaseDialogFragment {
    private View view;

    //手机验证下一次的时间
    @Bind(R.id.bt_get_phone_check)
    Button phone_check;
    //注册手机号
    @Bind(R.id.ct_user_phone_number)
    ClearEditText phoneNum;
    @Bind(R.id.ct_user_password)
    ClearEditText passWord;
    @Bind(R.id.ct_user_phone_auth_code_number)
    ClearEditText checkNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_register_phone, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public void onEventMainThread(BaseJson bean) {
        if (bean != null && (this.getClass().getName() + "mobile").equals(bean.getTag())) {
            MoBileBean moBileBean = (MoBileBean) bean;
            if (moBileBean.getCode()==200)
                checkNum.setText(moBileBean.getData());
            else
                ToastUtil.showToast(mContext, moBileBean.getMsg());
        }
        if (bean != null && (this.getClass().getName() + "psignup").equals(bean.getTag())) {
            UserInfoBean userInfoBean = (UserInfoBean) bean;
            if (userInfoBean.getCode() == 200) {
                QulianApplication.getInstance().saveUserInfo(userInfoBean.getData());
                Intent intent = new Intent(mContext, MainActivity.class);
                ((SimpleBackActivity) mContext).setResult(((SimpleBackActivity) mContext).RESULT_OK, intent);
                ((SimpleBackActivity) mContext).finish();
                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
            } else {
                ToastUtil.showToast(mContext, userInfoBean.getMsg());
            }
        }
    }

    @OnClick(R.id.bt_user_register)
    void registerUser() {
        String phone = phoneNum.getText().toString();
        String pas = passWord.getText().toString();
        String num = checkNum.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast(mContext, "请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(num)) {
            ToastUtil.showToast(mContext, "请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(pas)) {
            ToastUtil.showToast(mContext, "请输入密码");
            return;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("SignupForm[mobile]", phone);
        map.put("SignupForm[password]", pas);
        map.put("SignupForm[code]", num);
        new PacketStringReQuest(HttpConstants.MOBILE_REGISTER, new UserInfoBean().setTag(getClass().getName() + "psignup"), map);
    }

    //手机号码的验证
    @OnClick(R.id.bt_get_phone_check)
    void phoneCheck() {
        String phone = phoneNum.getText().toString();
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

        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile", phone);
        new PacketStringReQuest(HttpConstants.CHECK_MOBILE_NUMBER, new MoBileBean().setTag(getClass().getName() + "mobile"), map, null);
    }
}
