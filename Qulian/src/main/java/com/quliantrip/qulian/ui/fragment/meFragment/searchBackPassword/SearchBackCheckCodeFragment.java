package com.quliantrip.qulian.ui.fragment.meFragment.searchBackPassword;

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
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.ui.activity.SimpleBackActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手机找回密码检验验证码
 */
public class SearchBackCheckCodeFragment extends BaseDialogFragment {

    private View view;
    private String phoneString;
    private String reset_key;

    @Bind(R.id.bt_searck_back_password_phone_one)
    ClearEditText newPas;
    @Bind(R.id.bt_searck_back_password_phone_two)
    ClearEditText newTwoPas;
    @Bind(R.id.bt_submit_search_back_password_phone)
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_search_back_password_phone_check_code, null);
        ButterKnife.bind(this, view);
        button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_sub_bg));
        initListener();
        Bundle bundle = getArguments();
        phoneString = bundle.getString("tel");
        reset_key = bundle.getString("reset_key");
        return view;
    }

    private void initListener() {
        newPas.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(newPas.getText())) {
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

    //接收请求结果进行关闭的操作
    public void onEventMainThread(BaseJson bean) {
        //找回密码数据的请求
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            HintInfoBean hintInfoBean = (HintInfoBean) bean;
            if (hintInfoBean.getCode() == 200) {
                ToastUtil.showToast(mContext, hintInfoBean.getMsg());
                ((SimpleBackActivity) mContext).finish();
            } else {
                ToastUtil.showToast(mContext, hintInfoBean.getMsg());
            }
        }
    }

    //提交验证
    @OnClick(R.id.bt_submit_search_back_password_phone)
    void searchBackPasswordSubmit() {
        String newPasString = newPas.getText().toString().trim();
        String newTwoPasString = newTwoPas.getText().toString().trim();

        if (TextUtils.isEmpty(newPasString)) {
            ToastUtil.showToast(mContext, "请数据新密码");
            return;
        }
        if (TextUtils.isEmpty(newTwoPasString)) {
            ToastUtil.showToast(mContext, "请数据确认新密码");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("new_pass", newPasString);
        map.put("check_pass", newTwoPasString);
        map.put("tel", phoneString);
        map.put("reset_key", reset_key);
        new PacketStringReQuest(HttpConstants.USER_BACK_PAS_PHONE_SUBMIT, new HintInfoBean().setTag(getClass().getName()), map);
    }
}
