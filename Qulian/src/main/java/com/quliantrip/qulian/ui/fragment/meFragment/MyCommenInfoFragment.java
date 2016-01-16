package com.quliantrip.qulian.ui.fragment.meFragment;

import android.view.View;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.TuanBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by Qulian5 on 2016/1/14.
 */
public class MyCommenInfoFragment extends BasePageCheckFragment {
    private View view;

    @Override
    protected View getSuccessView() {
        view =View.inflate(mContext, R.layout.fragment_me_commen_info,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ctl", "tuan");
        map.put("r_type", "1");
        return new QuestBean(map, new TuanBean().setTag(getClass().getName()), HttpConstants.HOST_ADDR_ROOT_NET);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {

    }
}
