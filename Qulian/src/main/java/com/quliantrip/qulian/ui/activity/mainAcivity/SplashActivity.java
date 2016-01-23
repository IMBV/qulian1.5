package com.quliantrip.qulian.ui.activity.mainAcivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Window;
import android.view.WindowManager;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.util.CommonHelp;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;

public class SplashActivity extends Activity {
    private Context mContext;
    private SystemBarTintManager mTintManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        applyKitKatTranslucency();
        ButterKnife.bind(this);
        mContext = this;
        new Thread(){
            public void run() {
                SystemClock.sleep(2000);
                if(CommonHelp.getSp(mContext,"guideFirst",false)){
                    enterMain();
                    finish();
                }else{
                    enterGuide();
                    finish();
                }
            };
        }.start();
    }

    private void enterMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void enterGuide(){
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
    }

    private void applyKitKatTranslucency() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setNavigationBarTintEnabled(true);
            mTintManager.setTintColor(android.R.color.white);
//            mTintManager.setNavigationBarTintColor(android.R.color.white);
//            mTintManager.setStatusBarTintColor(android.R.color.white);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}