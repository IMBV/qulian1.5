package com.quliantrip.qulian.ui.fragment.meFragment.orderFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.quliantrip.qulian.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Yuly on 2016/1/5.
 * 显示消费券
 */
public class ShowConSumerFragment extends Fragment {
    private Context mContext;
    private View view;
    @Bind(R.id.ll_line)
    LinearLayout line;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_see_consumer, null);
        ButterKnife.bind(this, view);
        line.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        return view;
    }
}
