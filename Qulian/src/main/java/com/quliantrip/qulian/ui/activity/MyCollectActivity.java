package com.quliantrip.qulian.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.util.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Qulian5 on 2016/1/12.
 * 我的收藏
 */
public class MyCollectActivity extends Activity {
    private Context mContext;
    @Bind(R.id.tv_mycolloct_edit)
    TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_mycollect);
        ButterKnife.bind(this);
        mContext = this;
    }

    //点击进行编辑
    @OnClick(R.id.tv_mycolloct_edit)
    void editMyCollect() {
        ToastUtil.showToast(mContext, "进行编辑我的收藏");
    }

    //点击返回
    @OnClick(R.id.iv_icon_back)
    void back() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }
}
