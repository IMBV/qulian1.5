package com.quliantrip.qulian.ui.activity.findActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.ui.fragment.findFragment.SpotDetailFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 单品详情页
 */
public class SpotDetailActivity extends SwipeBackActivity {
    private Context mContext;

    @Bind(R.id.fl_fargment_container)
    FrameLayout container;
    @Bind(R.id.iv_spot_activity_detail_back)
    RelativeLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_detail);
        ButterKnife.bind(this);
        mContext = this;
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fargment_container, new SpotDetailFragment()).commit();
    }

    @OnClick(R.id.iv_spot_activity_detail_back)
    void finishActivity() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }

    public void showOrHideBack(boolean b) {
        if (b) {
            back.setVisibility(View.VISIBLE);
        } else {
            back.setVisibility(View.GONE);
        }
    }
}
