package com.quliantrip.qulian.adapter.myAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.HintInfoBean;
import com.quliantrip.qulian.domain.LinkManBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.ui.fragment.meFragment.linkman.MyLinkmanFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Qulian5 on 2016/1/19.
 */
public class LinkManListAdapter extends BasicAdapter<LinkManBean.LinkMan> {

    public LinkManListAdapter(ArrayList<LinkManBean.LinkMan> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_link_man_all_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        final LinkManBean.LinkMan bean = list.get(position);
        convertView.findViewById(R.id.iv_link_man_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(bean);
                notifyDataSetChanged();
                Map<String, String> map = new HashMap<String, String>();
                System.out.println("cheshi"+bean.getId());
                map.put("id", bean.getId());
                new PacketStringReQuest(HttpConstants.DELETE_LINKMAN, new HintInfoBean().setTag(MyLinkmanFragment.class.getName()+"delete"), map);
            }
        });

        holder.name.setText(bean.getName());
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_link_man_name)
        TextView name;

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
