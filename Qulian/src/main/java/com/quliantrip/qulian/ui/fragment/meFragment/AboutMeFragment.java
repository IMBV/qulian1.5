package com.quliantrip.qulian.ui.fragment.meFragment;

import android.view.View;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.util.TDevice;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 关于我们
 */
public class AboutMeFragment extends BaseFragment {
    @Bind(R.id.tv_about_me_code)
    TextView code;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_my_about_me, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initDate() {
        code.setText(TDevice.getVersionName());
    }

}
