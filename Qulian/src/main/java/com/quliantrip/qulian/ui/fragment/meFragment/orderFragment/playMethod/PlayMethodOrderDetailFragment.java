package com.quliantrip.qulian.ui.fragment.meFragment.orderFragment.playMethod;

import android.view.View;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.net.volleyManage.QuestBean;

/**
 * 我的订单详情页
 */
public class PlayMethodOrderDetailFragment extends BasePageCheckFragment {
    private View view;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_me_play_method_detail_order, null);
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
