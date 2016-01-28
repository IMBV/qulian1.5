package com.quliantrip.qulian.adapter.homeAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.home.SecnicPlayResultBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.ui.activity.HomeActivity.SecnicPlayConditionActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 历史搜索记录
 */
public class SearckConditionHistoryAdapter extends BasicAdapter<String> {

    private Context mContext;

    public SearckConditionHistoryAdapter(ArrayList<String> list, Context context) {
        super(list);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_search_condition_history_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        final String s = list.get(position);
        convertView.findViewById(R.id.tv_history_list_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("title", s);
                new PacketStringReQuest(HttpConstants.HOME_SECNICPLAY_CONDITION, new SecnicPlayResultBean().setTag(SecnicPlayConditionActivity.class.getName()), map);
                ((SecnicPlayConditionActivity) mContext).setConditionText(s);
            }
        });
        holder.text.setText(s);
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_history_list_item)
        TextView text;

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
