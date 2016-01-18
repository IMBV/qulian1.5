package com.quliantrip.qulian.ui.fragment.meFragment.register;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.HomeBean;
import com.quliantrip.qulian.domain.MoBileBean;
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
import de.greenrobot.event.EventBus;

/**
 * Created by yuly on 2016/1/5.
 * 手机注册
 */
public class RegisterPhoneFragment extends Fragment {
    private Context mContext;
    private View view;
    private String code;

    //手机验证下一次的时间
    @Bind(R.id.bt_get_phone_check)
    Button phone_check;
    //注册手机号
    @Bind(R.id.ct_user_phone_number)
    ClearEditText phoneNum;
    @Bind(R.id.ct_user_password)
    ClearEditText passWord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_register_phone, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            if (bean.getTag().endsWith("mobile")) {
                MoBileBean moBileBean = (MoBileBean) bean;
                code = ((MoBileBean) bean).getData();
            } else if (bean.getTag().endsWith("psignup")) {


            }
        }
    }


    @OnClick(R.id.bt_user_register)
    void registerUser() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("password", phoneNum.getText().toString().trim());
        map.put("mobile", passWord.getText().toString().trim());
        map.put("code", code);
        new PacketStringReQuest(HttpConstants.CHECK_MOBILE_NUMBER, new MoBileBean().setTag(this.getClass().getName() + "psignup"), map, null);
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
        new PacketStringReQuest(HttpConstants.CHECK_MOBILE_NUMBER, new MoBileBean().setTag(this.getClass().getName() + "mobile"), map, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
