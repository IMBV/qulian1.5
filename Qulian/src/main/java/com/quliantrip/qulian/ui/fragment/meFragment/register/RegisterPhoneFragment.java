package com.quliantrip.qulian.ui.fragment.meFragment.register;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.util.TimeCountUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Qulian5 on 2016/1/5.
 * 手机注册
 */
public class RegisterPhoneFragment extends Fragment {
    private Context mContext;
    private View view;
    @Bind(R.id.bt_get_phone_check)
    Button phone_check;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_register_phone, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.bt_get_phone_check)
    void phoneCheck() {
        TimeCountUtil timeCountUtil = new TimeCountUtil((Activity) mContext, 60000, 1000, phone_check);
        timeCountUtil.start();
    }
}
