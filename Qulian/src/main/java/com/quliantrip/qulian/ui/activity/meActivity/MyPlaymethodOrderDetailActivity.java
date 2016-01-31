package com.quliantrip.qulian.ui.activity.meActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.playMethod.PlayMethodOrderDetailFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 玩法订单详情页
 */
public class MyPlaymethodOrderDetailActivity extends SwipeBackActivity {
    @Bind(R.id.tv_title_name)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_play_order_detail);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,  new PlayMethodOrderDetailFragment()).commit();
    }

    //设置标题为订单号
    private void setTitle(String s){
        title.setText(s);
    }
}
