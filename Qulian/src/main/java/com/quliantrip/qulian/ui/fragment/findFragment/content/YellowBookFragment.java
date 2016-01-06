package com.quliantrip.qulian.ui.fragment.findFragment.content;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.global.QulianApplication;

/**
 * Created by Qulian5 on 2016/1/6.
 */
public class YellowBookFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(QulianApplication.getContext());
        textView.setText("趣连小黄书");
        return textView;
    }
}
