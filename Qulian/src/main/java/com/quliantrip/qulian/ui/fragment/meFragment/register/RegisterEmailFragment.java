package com.quliantrip.qulian.ui.fragment.meFragment.register;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.quliantrip.qulian.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Qulian5 on 2016/1/5.
 */
public class RegisterEmailFragment extends Fragment{
    private Context mContext;
    private View view;
    @Bind(R.id.lv_home_local_play_fragment_list)
    ListView listView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(mContext);
        textView.setText("邮箱注册");
        return textView;
    }
}
