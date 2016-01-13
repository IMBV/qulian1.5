package com.quliantrip.qulian.mode.detailMode;

import android.view.View;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.mode.BaseMode;

import java.util.List;

import butterknife.ButterKnife;

/**
 * 单品和玩法详界面的够吗须知的内容
 * Created by Yuly on 2015/12/4.
 * www.quliantrip.com
 */
public class BuyNotesMode extends BaseMode<List<String>> {
    private View view;

    public BuyNotesMode() {
        view = View.inflate(QulianApplication.getContext(), R.layout.mode_detail_buy_notes, null);
    }

    @Override
    public View getModelView() {
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setData(List<String> advs) {

    }
}