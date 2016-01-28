package com.quliantrip.qulian.ui.activity.choiceActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.ui.fragment.choicenessFragment.PlayMethodDetailFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Yuly on 2016/1/8.
 */
public class PlayMethodDetailActivity extends SwipeBackActivity {
    private Context mContext;

    @Bind(R.id.fl_fargment_container)
    FrameLayout container;
    @Bind(R.id.iv_Play_method_activity_detail_back)
    RelativeLayout back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_method);
        ButterKnife.bind(this);
        mContext = this;
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fargment_container,new PlayMethodDetailFragment()).commit();
    }

    @OnClick(R.id.iv_Play_method_activity_detail_back)
    void finishActivity() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }

    public void showOrHideBack(boolean b) {
        if (b) {
            back.setVisibility(View.VISIBLE);
        }else{
            back.setVisibility(View.GONE);
        }
    }
}
