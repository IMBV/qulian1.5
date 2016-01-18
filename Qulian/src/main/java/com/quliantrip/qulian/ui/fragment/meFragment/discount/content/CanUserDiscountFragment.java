package com.quliantrip.qulian.ui.fragment.meFragment.discount.content;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quliantrip.qulian.R;

//可以使用的fragment
//条目adapter_my_discount_can_user
public class CanUserDiscountFragment extends Fragment {
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.adapter_my_discount_item,null);
        return view;
    }
}
