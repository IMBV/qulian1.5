package com.quliantrip.qulian.ui.activity.meActivity;

import android.content.Context;
import android.os.Bundle;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.good.GoodDetailBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 意见反馈
 */
public class FeedbackActivity extends SwipeBackActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfeed);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
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

    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {

        }
    }


    @OnClick(R.id.bt_add_opinion)
    void addOption() {
        Map<String, String> map = new HashMap<>();
        new PacketStringReQuest(HttpConstants.GOOD_DETAIL, new GoodDetailBean().setTag(getClass().getName()), map);
    }
}
