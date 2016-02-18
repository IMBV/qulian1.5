package com.quliantrip.qulian.ui.activity.choiceActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.quliantrip.qulian.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 玩法订单详情页
 */
public class GoodDetailIntroduceActivity extends SwipeBackActivity {
    @Bind(R.id.tv_good_des)
    TextView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chioce_good_introduce);
        ButterKnife.bind(this);
        if (getIntent().getStringExtra("goodGes") != null){
            webView.setText(Html.fromHtml(getIntent().getStringExtra("goodGes")));
        }
    }

    @OnClick(R.id.iv_simple_back)
    void back() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }
}
