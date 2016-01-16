package com.quliantrip.qulian.ui.activity.meActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 意见反馈
 */
public class FeedbackActivity extends Activity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfeed);
        ButterKnife.bind(this);
        mContext = this;
    }

    @OnClick(R.id.iv_back)
    void back() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }

    @OnClick(R.id.iv_feed_call_me)
    void callMe() {
        ToastUtil.showToast(getApplicationContext(), "电话联系我们");
    }

}
