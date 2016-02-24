package com.quliantrip.qulian.ui.activity.meActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.playMethod.PlayMethodOrderDetailFragment;
import com.quliantrip.qulian.util.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 玩法订单详情页
 */
public class MyPlaymethodOrderDetailActivity extends SwipeBackActivity {
    @Bind(R.id.tv_title_name)
    TextView title;
    private String playOrdernumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_play_order_detail);
        ButterKnife.bind(this);
        PlayMethodOrderDetailFragment playMethodOrderDetailFragment = new PlayMethodOrderDetailFragment();
        Bundle bundle = getIntent().getExtras();
        playMethodOrderDetailFragment.setArguments(bundle);
        playOrdernumber = bundle.getString("playOrdernumber");
        setTitle("订单组号："+playOrdernumber);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,playMethodOrderDetailFragment ).commit();
    }

    //设置标题为订单号
    private void setTitle(String s) {
        title.setText(s);
    }

    @OnClick(R.id.iv_simple_back)
    void back() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }

    //取消订单
    @OnClick(R.id.tv_play_order_cancel)
    void cancelPlayMethodOrder(){
        ToastUtil.showToast(this,"该取消返回显示界面");
    }
}