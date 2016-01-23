package com.quliantrip.qulian.ui.fragment.meFragment.discount.content;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quliantrip.qulian.R;

/**
 * Created by Qulian5 on 2016/1/6.
 */
public class PastDueDiscountFragment extends Fragment {
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.view_pulltorefresh_listview,null);
        return view;
    }
}
