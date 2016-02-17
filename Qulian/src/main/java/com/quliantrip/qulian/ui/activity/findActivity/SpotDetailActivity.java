package com.quliantrip.qulian.ui.activity.findActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.service.Iservice;
import com.quliantrip.qulian.service.MusicService;
import com.quliantrip.qulian.ui.fragment.findFragment.SpotDetailFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 景点详情加载页详情页
 */
public class SpotDetailActivity extends SwipeBackActivity {
    private Context mContext;

    @Bind(R.id.fl_fargment_container)
    FrameLayout container;
    @Bind(R.id.iv_spot_activity_detail_back)
    RelativeLayout back;
    //音乐有关的数据
    private Iservice iservice;//就是我们的中间人对象的实现
    private MyConn conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_detail);
        initMusic();
        ButterKnife.bind(this);
        mContext = this;
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fargment_container, new SpotDetailFragment()).commit();
    }

    private void initMusic() {
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        conn = new MyConn();
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public Iservice getIservice() {
        return iservice;
    }

    ;


    //未加载完成的显示界面
    @OnClick(R.id.iv_spot_activity_detail_back)
    void finishActivity() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }

    public void showOrHideBack(boolean b) {
        if (b) {
            back.setVisibility(View.VISIBLE);
        } else {
            back.setVisibility(View.GONE);
        }
    }

    //监视服务的状态
    private class MyConn implements ServiceConnection {


        //当服务连接成功的时候调用  获取我们定义的中间人对象(IBinder)
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取我们定义的中间人对象

            iservice = (Iservice) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

    }

    @Override
    protected void onDestroy() {
        //当Activity销毁的时候 解绑服务
        unbindService(conn);
        super.onDestroy();
    }

}
