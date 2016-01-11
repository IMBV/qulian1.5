package com.quliantrip.qulian.ui.fragment.findFragment.content;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.global.QulianApplication;

/**
 * Created by Qulian5 on 2016/1/6.
 */
public class DiscountFragment extends Fragment {
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_find_discount,null);
        return view;
    }
}
