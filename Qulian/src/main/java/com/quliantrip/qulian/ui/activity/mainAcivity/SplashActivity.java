package com.quliantrip.qulian.ui.activity.mainAcivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

    private LocationManager locationManager;
    private MyLocationListener myLocationListener;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        applyKitKatTranslucency();
        ButterKnife.bind(this);
        mContext = this;
//        //1.获取位置的管理者
//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        //2.获取最佳的定位方式
//        Criteria criteria = new Criteria();
//        criteria.setAltitudeRequired(true);
//        String bestProvider = locationManager.getBestProvider(criteria, true);
//        //3.定位
//        myLocationListener = new MyLocationListener();
//        //provider : 定位的方式
//        //minTime : 定位的最小时间间隔
//        //minDistance : 定位最小的间隔距离
//        //LocationListener : 定位监听
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100000, 100, myLocationListener);
//        locationManager.requestLocationUpdates(bestProvider, 0, 0, myLocationListener);
        new Thread() {
            public void run() {
                SystemClock.sleep(2000);
                if (CommonHelp.getSp(mContext, "guideFirst", false)) {
                    enterMain();
                    finish();
                } else {
                    enterGuide();
                    finish();
                }
            };
        }.start();
    }

    private class MyLocationListener implements LocationListener {
        //当定位位置改变的调用的方法
        //Location : 当前的位置
        @Override
        public void onLocationChanged(Location location) {
            location.getAccuracy();//获取精确位置
            location.getAltitude();//获取海拔
            latitude = location.getLatitude();//获取纬度，平行
            longitude = location.getLongitude();//获取经度，垂直
//            if(TextUtils.isEmpty(""+latitude)){
//                textview1.setText("kong");
//            }else{
//                textview1.setText("longitude:"+longitude+"  latitude:" + latitude);
//            }
        }
        //当定位状态发生改变的时候调用的方式
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        //当定位可用的时候调用的方法
        @Override
        public void onProviderEnabled(String provider) {
        }
        //当定位不可用的时候调用的方法
        @Override
        public void onProviderDisabled(String provider) {

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        locationManager.removeUpdates(myLocationListener);//关闭gps,但是高版本中规定打开和关闭gps必须由用户自己主观的去实现，代码已经不允许进行操作
//        CommonHelp.saveStringSp(mContext,"geographic",latitude+"::"+longitude);
    }
    private void enterMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void enterGuide() {
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