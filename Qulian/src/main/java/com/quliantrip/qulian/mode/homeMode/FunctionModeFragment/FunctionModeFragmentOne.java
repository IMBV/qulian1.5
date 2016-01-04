package com.quliantrip.qulian.mode.homeMode.FunctionModeFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quliantrip.qulian.R;

import butterknife.ButterKnife;

public class FunctionModeFragmentOne extends Fragment {

    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (Context) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_function_mode_fragment_one, null);
        ButterKnife.bind(this, view);
        return view;
    }

}
