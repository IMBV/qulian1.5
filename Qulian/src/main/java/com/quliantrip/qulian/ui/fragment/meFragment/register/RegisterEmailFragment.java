package com.quliantrip.qulian.ui.fragment.meFragment.register;

import android.app.Activity;
import android.content.Intent;
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
import com.quliantrip.qulian.domain.me.UserInfoBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.ui.activity.SimpleBackActivity;
import com.quliantrip.qulian.ui.activity.mainAcivity.MainActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 邮箱注册
 */
public class RegisterEmailFragment extends BaseDialogFragment {
    private View view;
    @Bind(R.id.ct_user_email_number)
    ClearEditText emailInto;
    @Bind(R.id.ct_user_email_password)
    ClearEditText passwordInfo;
    @Bind(R.id.bt_user_login_email_submit)
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_register_email, null);
        ButterKnife.bind(this, view);
        button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_sub_bg));
        return view;
    }

    public void onEventMainThread(BaseJson bean) {
        if (bean != null && (this.getClass().getName()).equals(bean.getTag())) {
            UserInfoBean userInfoBean = (UserInfoBean) bean;
            if (userInfoBean.getCode() == 200) {
                QulianApplication.getInstance().saveUserInfo(userInfoBean.getData());
                Intent intent = new Intent(mContext, MainActivity.class);
                ((SimpleBackActivity) mContext).setResult(((SimpleBackActivity) mContext).RESULT_OK, intent);
                ((SimpleBackActivity) mContext).finish();
                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
            } else
                ToastUtil.showToast(mContext, userInfoBean.getMsg());
        }
    }

    @OnClick(R.id.bt_user_login_email_submit)
    void registerUser() {
        String email = emailInto.getText().toString().trim();
        String pas = passwordInfo.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            ToastUtil.showToast(mContext, "邮箱不能给空");
            return;
        }

        if (TextUtils.isEmpty(pas)) {
            ToastUtil.showToast(mContext, "密码不能给空");
            return;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("SignupForm[email]", email);
        map.put("SignupForm[password]", pas);
        new PacketStringReQuest(HttpConstants.EMAIL_REGISTER, new UserInfoBean().setTag(getClass().getName()), map, null);
    }

    private void initListener() {
        emailInto.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(emailInto.getText())) {
                    button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_sub_bg));
                } else {
                    button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_collor));
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
}
