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
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.view.ClearEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 邮箱找回
 */
public class SearckBachEmailFragment extends BaseDialogFragment {
    private View view;
    @Bind(R.id.bt_searck_back_password_email)
    ClearEditText emailInto;
    @Bind(R.id.bt_submit_searck_back_password_email)
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_search_back_password_email, null);
        ButterKnife.bind(this, view);
        button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_sub_bg));
        initListener();
        return view;
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
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && (this.getClass().getName()).equals(bean.getTag())) {
        }
    }

    //发送邮件重设密码
    @OnClick(R.id.bt_submit_searck_back_password_email)
    void searchBackPasswordEmail() {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("SignupForm[email]", email);
//        map.put("SignupForm[password]", pas);
//        new PacketStringReQuest(HttpConstants.EMAIL_REGISTER, new UserInfoBean().setTag(getClass().getName()), map, null);
    }
}
