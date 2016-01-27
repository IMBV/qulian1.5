package com.quliantrip.qulian.ui.fragment.findFragment.content;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.find.DiscountBean;
import com.quliantrip.qulian.domain.find.VoiceSquareBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * 语音官界面
 */
public class VoiceSquareFragment extends BasePageCheckFragment {
    private View view;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_find_voice_square, null);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("city", "21000000");
        return new QuestBean(map, new VoiceSquareBean().setTag(getClass().getName()), HttpConstants.VOICE_SQUARE_LIST);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            VoiceSquareBean voiceSquareBean = (VoiceSquareBean) bean;
            if(((VoiceSquareBean) bean).getCode() == 200){

            }else{
                ToastUtil.showToast(mContext,voiceSquareBean.getMsg());
            }
        }
    }
}
