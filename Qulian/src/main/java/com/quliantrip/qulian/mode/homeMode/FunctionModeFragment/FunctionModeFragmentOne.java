package com.quliantrip.qulian.mode.homeMode.FunctionModeFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.ui.activity.mainAcivity.MainActivity;

import butterknife.ButterKnife;

public class FunctionModeFragmentOne extends Fragment {

    protected MainActivity mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_function_mode_fragment_one, null);
        ButterKnife.bind(this, view);
        mContext.test();
        return view;
    }

}
