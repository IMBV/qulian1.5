package com.quliantrip.qulian.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.util.ToastUtil;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;

/**
 * Created by Qulian5 on 2016/3/1.
 */
public class WXEntryActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        SendAuth.Resp resp = new SendAuth.Resp(intent.getExtras());
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                ToastUtil.showToast(this,"code:"+resp.code);
                this.finish();
                overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED ://用户拒绝授权
                ToastUtil.showToast(this,"拒绝授权");
                this.finish();
                overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL ://用户取消
                ToastUtil.showToast(this,"取消授权");
                this.finish();
                overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
                break;
            default:
                break;
        }
    }
}

