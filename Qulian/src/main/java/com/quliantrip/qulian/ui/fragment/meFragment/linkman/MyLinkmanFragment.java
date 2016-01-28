package com.quliantrip.qulian.ui.fragment.meFragment.linkman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.myAdapter.LinkManListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.common.HintInfoBean;
import com.quliantrip.qulian.domain.me.LinkManBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.SimpleBackActivity;
import com.quliantrip.qulian.util.ToastUtil;
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
    @Bind(R.id.rl_pager_empty)
    RelativeLayout empty;

    private boolean isItemOnclick;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_me_commen_info, null);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            if (getArguments().getBoolean("isItemOnclick"))
                isItemOnclick = true;
        }
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
            LinkManBean linkManBean = (LinkManBean) bean;
            if (linkManBean.getData() != null) {
                empty.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                listView.setAdapter(new LinkManListAdapter((ArrayList<LinkManBean.LinkMan>) linkManBean.getData(), mContext, MyLinkmanFragment.this));
                if (isItemOnclick) {
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            LinkManBean.LinkMan bean = (LinkManBean.LinkMan) parent.getAdapter().getItem(position);
                            Intent intent = getActivity().getIntent();
                            intent.putExtra("linkMan", bean);
                            ((SimpleBackActivity) mContext).setResult(((SimpleBackActivity) mContext).RESULT_OK, intent);
                            ((SimpleBackActivity) mContext).finish();
                            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
                        }
                    });
                }
            } else {
                empty.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }
        }
        if (bean != null && (this.getClass().getName() + "delete").equals(bean.getTag())) {
            HintInfoBean hintInfoBean = (HintInfoBean) bean;
            ToastUtil.showToast(mContext, hintInfoBean.getMsg());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", QulianApplication.getInstance().getLoginUser().getAuth_key());
        new PacketStringReQuest(HttpConstants.ALL_LINKMAN, new LinkManBean().setTag(getClass().getName()), map);
    }

    //添加常用联人
    @OnClick(R.id.bt_add_link_man)
    void addLinkMan() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("linkMan", null);
        UIHelper.showAddLinkMan(mContext, this, 441, bundle);
    }
}
