package com.quliantrip.qulian.ui.fragment.meFragment.collectFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.view.SlipRihtLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Qulian5 on 2016/1/5.
 */
public class PlayMethodCollectFragment extends Fragment {
    private Context mContext;
    private View view;
    @Bind(R.id.srl_play_method_collect_item)
    SlipRihtLayout slipRihtLayout;
    @Bind(R.id.iv_collect_Img_style)
    ImageView collectCheckImg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.adapter_my_collect_play_method_item, null);
        ButterKnife.bind(this, view);
        return view;
    }

    private boolean isOpen = true;

    @OnClick(R.id.isopen)
    void open() {
        slipRihtLayout.layoutContent(isOpen);
        isOpen = !isOpen;
    }

    private boolean isChecked = true;

    @OnClick(R.id.ll_is_checked_delect)
    void ischecked() {
        if (isChecked) {
            collectCheckImg.setImageResource(R.mipmap.cnb_wode_nor);
        } else {
            collectCheckImg.setImageResource(R.mipmap.cnb_wode_pre);
        }
        isChecked = !isChecked;
    }

}
