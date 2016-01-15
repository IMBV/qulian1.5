package com.quliantrip.qulian.ui.fragment.meFragment;

import android.view.View;

import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.net.volleyManage.QuestBean;

/**
 * Created by Qulian5 on 2016/1/14.
 */
public class MyCommenInfo extends BasePageCheckFragment {
    private View view;

    @Override
    protected View getSuccessView() {

        return view;
    }

    @Override
    protected QuestBean requestData() {
        return null;
    }

    @Override
    public void onEventMainThread(BaseJson bean) {

    }
}
