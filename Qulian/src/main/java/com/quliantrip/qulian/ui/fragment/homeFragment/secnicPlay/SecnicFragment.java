package com.quliantrip.qulian.ui.fragment.homeFragment.secnicPlay;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.util.ToastUtil;

import java.lang.annotation.Target;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Yuly on 2016/1/5.
 */
public class SecnicFragment extends Fragment {
    private Context mContext;
    private View view;
    @Bind(R.id.gv_home_secnic_fragment_list)
    GridView gridView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_home_secnic,null);
        ButterKnife.bind(this,view);
        return view;
    }
}
