package com.quliantrip.qulian.ui.activity.meActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.good.GoodDetailBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;

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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @OnClick(R.id.iv_back)
    void back() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }

    //电话联系我们
    @OnClick(R.id.iv_feed_call_me)
    void callMe() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + "01087162852"));
        startActivity(intent);
    }

    //添加提交信息
    @OnClick(R.id.bt_add_opinion)
    void addOption() {
        Map<String, String> map = new HashMap<>();
        new PacketStringReQuest(HttpConstants.GOOD_DETAIL, new GoodDetailBean().setTag(getClass().getName()), map);
    }

    //用于接收的请求的结果
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {

        }
    }
}
