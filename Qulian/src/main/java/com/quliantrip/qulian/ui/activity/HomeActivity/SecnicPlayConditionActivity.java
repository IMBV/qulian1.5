package com.quliantrip.qulian.ui.activity.HomeActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.ui.fragment.homeFragment.SecnicPlayConditionFragment;
import com.quliantrip.qulian.ui.fragment.homeFragment.SecnicPlayFragment;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.ClearEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Qulian5 on 2016/1/5.
 */
public class SecnicPlayConditionActivity extends SwipeBackActivity {
    @Bind(R.id.fl_secnic_container)
    FrameLayout fragmentContainer;

    //查询输入的文字
    @Bind(R.id.cet_home_search)
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
        ButterKnife.bind(this);
        secnicPlayConditionFragment = new SecnicPlayConditionFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_secnic_container, secnicPlayConditionFragment).commit();
        initTextListener();
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
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    //切换fragment
    private void changeResultFragment() {
        if (secnicPlayFragment == null) {
            //这里是隐藏输入键盘的操作
            InputMethodManager imm = (InputMethodManager)getSystemService(SecnicPlayConditionActivity.this.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
            secnicPlayFragment = new SecnicPlayFragment();
            Bundle bundle = new Bundle();
            bundle.putString("keyWord", "nihao");
            secnicPlayFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_secnic_container, secnicPlayFragment).commit();
        } else {

        }
    }

    //搜索点击事件
    @OnClick(R.id.tv_home_search_hint)
    void searckResult() {
        if (TextUtils.isEmpty(searchText.getText())) {
            finish();
        } else {
            changeResultFragment();
        }
    }

    //点击返回显示
    @OnClick(R.id.iv_home_result_searck_back)
    void backFinish() {
        finish();
    }

}
