package com.quliantrip.qulian.ui.fragment.meFragment;

import android.view.View;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.ClearEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class ChangePasswordFragment extends BaseFragment {
    private View view;

    @Bind(R.id.cet_old_password)
    ClearEditText oldPassword;
    @Bind(R.id.cet_new_password)
    ClearEditText newPassword;
    @Bind(R.id.cet_confirmation_password)
    ClearEditText confirmPassword;
    private String newPas;

    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.fragment_me_change_paaword,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void initDate() {

    }

    //点击保存按钮
    @OnClick(R.id.bt_change_password)
    void savePassword(){
        String newPas = newPassword.getText().toString().trim();
        String confirdPas = confirmPassword.getText().toString().trim();
        if (confirdPas.equals(newPas)) {
            //可以进行修改
            ToastUtil.showToast(mContext,"可以进行修改");
        } else {
            ToastUtil.showToast(mContext, "密码前后输入不一致");
        }

    }



}
