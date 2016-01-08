package com.quliantrip.qulian.ui.activity;

import android.content.Context;
import android.os.Bundle;

import com.quliantrip.qulian.R;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Yuly on 2016/1/8.
 */
public class PlayMethodDetailActivity extends SwipeBackActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_play_method_detail);
        ButterKnife.bind(this);
        mContext = this;
    }
}
