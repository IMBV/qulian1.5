package com.quliantrip.qulian.ui.fragment.meFragment.collectFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Qulian5 on 2016/1/5.
 */
public class GoodCollectFragment extends Fragment {
    private Context mContext;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(mContext);
        textView.setText("商品收藏");
        return textView;
    }
}
