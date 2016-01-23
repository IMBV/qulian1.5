package com.quliantrip.qulian.adapter.homeAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.home.SecnicPlayResultBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.ui.activity.HomeActivity.SecnicPlayConditionActivity;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 搜索热词
 */
public class SearckConditionHotWordAdapter extends BasicAdapter<String> {

    private Context mContext;
    public SearckConditionHotWordAdapter(ArrayList<String> list,Context context) {
        super(list);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_search_condition_button_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        final String s = list.get(position);
        convertView.findViewById(R.id.bt_home_hot_word_condition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("title", s);
                new PacketStringReQuest(HttpConstants.HOME_SECNICPLAY_CONDITION, new SecnicPlayResultBean().setTag(SecnicPlayConditionActivity.class.getName()), map);
                ((SecnicPlayConditionActivity)mContext).setConditionText(s);
            }
        });
        holder.bt.setText(s);
        return convertView;
    }

    static class Holder {
        @Bind(R.id.bt_home_hot_word_condition)
        Button bt;

        public Holder(View convertView) {
            super();
            ButterKnife.bind(this, convertView);
        }

        public static Holder getHolder(View convertView) {
            Holder holder = (Holder) convertView.getTag();
            if (holder == null) {
                holder = new Holder(convertView);
                convertView.setTag(holder);
            }
            return holder;
        }
    }
}
