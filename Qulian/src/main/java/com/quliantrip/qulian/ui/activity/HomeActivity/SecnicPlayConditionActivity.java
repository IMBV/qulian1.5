package com.quliantrip.qulian.ui.activity.HomeActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.ui.fragment.homeFragment.SecnicPlayFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Qulian5 on 2016/1/5.
 */
public class SecnicPlayConditionActivity extends SwipeBackActivity {
    @Bind(R.id.fl_secnic_container)
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secnicplay_condition);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_secnic_container, new SecnicPlayFragment()).commit();

    }

}
