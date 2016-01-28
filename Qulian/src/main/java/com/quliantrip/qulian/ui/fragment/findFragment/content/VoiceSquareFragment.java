package com.quliantrip.qulian.ui.fragment.findFragment.content;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.finderAdapter.VoiceSquareListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.find.VoiceSquareBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 语音官界面
 */
public class VoiceSquareFragment extends BasePageCheckFragment {
    private View view;
    private Context mContext;
    @Bind(R.id.lv_voicequare_list)
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_find_voice_square, null);
        ButterKnife.bind(this, view);
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
            if (((VoiceSquareBean) bean).getCode() == 200) {
                listView.setAdapter(new VoiceSquareListAdapter((ArrayList<VoiceSquareBean.DataEntity>)voiceSquareBean.getData()));
            } else {
                ToastUtil.showToast(mContext, voiceSquareBean.getMsg());
            }
        }
    }
}
