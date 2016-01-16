package com.quliantrip.qulian.ui.fragment.meFragment.register;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.view.ClearEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Qulian5 on 2016/1/5.
 */
public class RegisterEmailFragment extends Fragment {
    private Context mContext;
    private View view;
    @Bind(R.id.ct_user_email_number)
    ClearEditText email;
    @Bind(R.id.ct_user_email_password)
    ClearEditText emailPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_register_email,null);
        ButterKnife.bind(this.view);
        return view;
    }
}
