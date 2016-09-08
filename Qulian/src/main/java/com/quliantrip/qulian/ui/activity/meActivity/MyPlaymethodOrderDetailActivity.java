package com.quliantrip.qulian.ui.activity.meActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.common.HintInfoBean;
import com.quliantrip.qulian.domain.me.PlayMethodOrderBean;
import com.quliantrip.qulian.domain.me.PlayMethodOrderDetailBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.playMethod.PlayMethodOrderDetailFragment;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 玩法订单详情页的activity界面
 */
public class MyPlaymethodOrderDetailActivity extends SwipeBackActivity {
    @Bind(R.id.tv_title_name)
    TextView title;
    private String playOrdernumber;//订单的编号
    private String orderId;//订单的id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_play_order_detail);
        ButterKnife.bind(this);
        PlayMethodOrderDetailFragment playMethodOrderDetailFragment = new PlayMethodOrderDetailFragment();
        Bundle bundle = getIntent().getExtras();
        playMethodOrderDetailFragment.setArguments(bundle);
        playOrdernumber = bundle.getString("playOrdernumber");
        orderId = bundle.getString("orderId");
        setTitle("订单组号："+playOrdernumber);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,playMethodOrderDetailFragment ).commit();
        EventBus.getDefault().register(this);
    }

    //设置标题为订单号
    private void setTitle(String s) {
        title.setText(s);
    }

    //简单返回的操作
    @OnClick(R.id.iv_simple_back)
    void back() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }

    //取消订单
    @OnClick(R.id.tv_play_order_cancel)
    void cancelPlayMethodOrder(){
        Map<String, String> map = new HashMap<>();
        map.put("id", orderId);
        new PacketStringReQuest(HttpConstants.ME_ORDER_PLAY_METHOD_CANCEL, new HintInfoBean().setTag(getClass().getName()), map);
    }

    //用来接收数据请求是否成功进行的操作
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            HintInfoBean hintInfoBean = (HintInfoBean) bean;
            if(hintInfoBean.getCode() == 200){
                finish();
                ToastUtil.showToast(this,hintInfoBean.getMsg());
            }else {
                ToastUtil.showToast(this,hintInfoBean.getMsg());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}