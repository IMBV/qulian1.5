package com.quliantrip.qulian.ui.fragment.meFragment.linkman;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.LinkManListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.LinkManBean;
import com.quliantrip.qulian.domain.TuanBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.UIHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Qulian5 on 2016/1/14.
 */
public class MyLinkmanFragment extends BasePageCheckFragment {
    private View view;
    @Bind(R.id.lv_link_man_all)
    ListView listView;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_me_commen_info, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());

        return new QuestBean(map, new LinkManBean().setTag(getClass().getName()), HttpConstants.ALL_LINKMAN);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && (this.getClass().getName()).equals(bean.getTag())) {
            LinkManBean linkManBean = (LinkManBean)bean;
            listView.setAdapter(new LinkManListAdapter((ArrayList<LinkManBean.LinkMan>) linkManBean.getData()));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    LinkManBean.LinkMan bean = (LinkManBean.LinkMan) parent.getAdapter().getItem(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("linkMan",bean);
                    UIHelper.showAddLinkMan(mContext, bundle);
                }
            });
        }
    }

    //添加常用联人
    @OnClick(R.id.bt_add_link_man)
    void addLinkMan() {
        UIHelper.showAddLinkMan(mContext, null);
    }
}
