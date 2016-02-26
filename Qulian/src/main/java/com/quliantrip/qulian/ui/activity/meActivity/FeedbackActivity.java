package com.quliantrip.qulian.ui.activity.meActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.EditText;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.common.HintInfoBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.HttpsTrustManager;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.util.TDevice;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 意见反馈
 */
public class FeedbackActivity extends SwipeBackActivity {
    private Context mContext;
    @Bind(R.id.feed_back_content)
    EditText content;//反馈的内容
    @Bind(R.id.me_feed_back_type)
    EditText type;

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
        intent.setData(Uri.parse("tel:" + "01053683169"));
        startActivity(intent);
    }

    //添加提交信息
    @OnClick(R.id.bt_add_opinion)
    void addOption() {
        String contentString = content.getText().toString().trim();
        String typeString = type.getText().toString().trim();
        if(TextUtils.isEmpty(contentString)){
            ToastUtil.showToast(mContext,"请你填写反馈意见");
            return;
        }
        HttpsTrustManager.allowAllSSL();
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key() == null?"":QulianApplication.getInstance().getLoginUser().getAuth_key());
        map.put("content", contentString);//内容
        map.put("type", typeString);//电话或邮箱　　　
        map.put("version", TDevice.getVersionName());//版本
        new PacketStringReQuest(HttpConstants.MY_USER_FEED_BACK, new HintInfoBean().setTag(getClass().getName()), map, null);
    }

    //用于接收的请求的结果
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            HintInfoBean hintInfoBean = (HintInfoBean) bean;
            if (hintInfoBean.getCode() == 200) {
                finish();
                ToastUtil.showToast(mContext,hintInfoBean.getMsg());
            } else {
                ToastUtil.showToast(mContext, hintInfoBean.getMsg());
            }
        }
    }
}
