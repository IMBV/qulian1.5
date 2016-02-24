package com.quliantrip.qulian.ui.activity.HomeActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.home.SecnicPlayResultBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.ui.fragment.homeFragment.SecnicPlayConditionFragment;
import com.quliantrip.qulian.ui.fragment.homeFragment.SecnicPlayFragment;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 首页搜索的界面
 */
public class SecnicPlayConditionActivity extends SwipeBackActivity {
    private Context mContext;

    //查询输入的文字
    @Bind(R.id.cet_home_search_text)
    ClearEditText searchText;

    @Bind(R.id.tv_home_search_hint)
    TextView hintText;

    //显示结果的fragment
    private SecnicPlayFragment secnicPlayFragment;
    private SecnicPlayConditionFragment secnicPlayConditionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secnicplay_condition);
        mContext = this;
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        secnicPlayConditionFragment = new SecnicPlayConditionFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_secnic_container, secnicPlayConditionFragment).commit();
        initTextListener();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    //添加数值的监听
    private void initTextListener() {
        //添加文职改变事件
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(searchText.getText())) {
                    hintText.setText("取消");
                } else {
                    hintText.setText("搜索");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    //搜索点击事件
    @OnClick(R.id.tv_home_search_hint)
    void searckResult() {
        String condition = searchText.getText().toString().trim();
        if (TextUtils.isEmpty(condition)) {
            finish();
            overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
        } else {
            Map<String, String> map = new HashMap<String, String>();
            map.put("title", condition);
            new PacketStringReQuest(HttpConstants.HOME_SECNICPLAY_CONDITION, new SecnicPlayResultBean().setTag(getClass().getName()), map);
        }
    }

    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            SecnicPlayResultBean secnicPlayResultBean = (SecnicPlayResultBean) bean;
            if (secnicPlayResultBean.getCode() == 200) {
                changeResultFragment(secnicPlayResultBean.getData());
            }
            ToastUtil.showToast(mContext, secnicPlayResultBean.getMsg());
        }
    }

    //切换fragment
    private void changeResultFragment(SecnicPlayResultBean.DataEntity dataEntity) {
        //这里是隐藏输入键盘的操作
        InputMethodManager imm = (InputMethodManager) getSystemService(SecnicPlayConditionActivity.this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);

        //跳转到结果页面
        secnicPlayFragment = new SecnicPlayFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("resultData", dataEntity);
        secnicPlayFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_secnic_container, secnicPlayFragment).commit();
        secnicPlayFragment = null;
    }

    //点击返回显示
    @OnClick(R.id.iv_home_result_searck_back)
    void backFinish() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }

    public void setConditionText(String s){
        searchText.setText(s);
        searchText.setFocusable(true);
    }
}
