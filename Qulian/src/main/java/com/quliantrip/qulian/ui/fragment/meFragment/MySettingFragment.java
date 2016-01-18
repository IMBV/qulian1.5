package com.quliantrip.qulian.ui.fragment.meFragment;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yuly on 2015/12/14.
 * www.quliantrip.com
 * 设置界面
 */
public class MySettingFragment extends BaseFragment {
    @Bind(R.id.tv_app_versionCode)
    TextView versionCode;//新版的编号

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_my_setting, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initDate() {

    }

    //修改密码
    @OnClick(R.id.tv_my_setting_updatePasword)
    void updataPasword() {
        UIHelper.showChangePassword(mContext, null);
    }

    //关于我们
    @OnClick(R.id.tv_my_setting_loginout)
    void aboutMe() {
        ToastUtil.showToast(QulianApplication.getContext(), "关于我们");
    }

    //退出当前用户
    @OnClick(R.id.tv_my_setting_loginout)
    void loginoutCurrentUser() {
        ToastUtil.showToast(QulianApplication.getContext(), "退出当前用户");
    }

}
