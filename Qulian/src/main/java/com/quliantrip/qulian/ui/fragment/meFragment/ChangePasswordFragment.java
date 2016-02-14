package com.quliantrip.qulian.ui.fragment.meFragment;

import android.text.TextUtils;
import android.view.View;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.common.HintInfoBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.ClearEditText;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.fragment_me_change_paaword, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initDate() {

    }

    //点击保存按钮
    @OnClick(R.id.bt_change_password)
    void savePassword() {
        String oldPas = oldPassword.getText().toString().trim();
        String newPas = newPassword.getText().toString().trim();
        String confirdPas = confirmPassword.getText().toString().trim();
        if (TextUtils.isEmpty(oldPas)) {
            ToastUtil.showToast(mContext, "请输入旧密码");
            return;
        }
        if (confirdPas.equals(newPas)) {
            ToastUtil.showToast(mContext,"这里还不行还没有同步tonkey的值");
//            //修改密码
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
//            map.put("old_pass", oldPas);
//            map.put("new_pass", newPas);
//            map.put("check_pass", confirdPas);
//            new PacketStringReQuest(HttpConstants.MY_USER_CHANG_PASSWORD, new HintInfoBean().setTag(getClass().getName()), map, null);
        } else {
            ToastUtil.showToast(mContext, "密码前后输入不一致");
        }
    }

    //接收数据
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            HintInfoBean hintInfoBean = (HintInfoBean) bean;
            if (hintInfoBean.getCode() == 200){
                //成果的结果
            }else{
                ToastUtil.showToast(mContext,hintInfoBean.getMsg());
            }
        }
    }

}
