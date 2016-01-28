package com.quliantrip.qulian.ui.fragment.meFragment.linkman;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.common.HintInfoBean;
import com.quliantrip.qulian.domain.me.LinkManBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.ui.activity.SimpleBackActivity;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 添加常用联系人
 */
public class AddLinkManFragment extends Fragment {
    private Context mContext;
    private View view;
    private LinkManBean.LinkMan linkMan;

    @Bind(R.id.cet_link_man_name)
    ClearEditText name;
    @Bind(R.id.cet_linkman_pingyin_xing)
    ClearEditText xing;
    @Bind(R.id.cet_linkman_pingyin_ming)
    ClearEditText ming;

    @Bind(R.id.cet_linkman_birth)
    TextView birth;
    @Bind(R.id.tv_link_man_sex)
    TextView sex;
    @Bind(R.id.tv_link_man_certificate_type)
    TextView type;
    @Bind(R.id.cet_link_man_certificate_number)
    ClearEditText num;
    @Bind(R.id.cet_linkman_address)
    ClearEditText address;

    @Bind(R.id.bt_save_link_man)
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_add_linkman, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle.getSerializable("linkMan") != null) {
            linkMan = (LinkManBean.LinkMan) bundle.getSerializable("linkMan");
            intidate(linkMan);
        }
    }

    public void onEventMainThread(BaseJson bean) {
        if (bean != null && (this.getClass().getName()).equals(bean.getTag())) {
            HintInfoBean hintInfoBean = (HintInfoBean) bean;
            if (hintInfoBean.getCode() == 200) {
                Intent intent = getActivity().getIntent();
                ((SimpleBackActivity) mContext).setResult(((SimpleBackActivity) mContext).RESULT_OK, intent);
                ((SimpleBackActivity) mContext).finish();
                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
            } else {
                ToastUtil.showToast(mContext, hintInfoBean.getMsg());
            }
        }
    }

    //进行初始化修改的参数
    private void intidate(LinkManBean.LinkMan linkMan) {
        button.setText("修改");
        name.setText(linkMan.getName());
        xing.setText(linkMan.getSurname());
        ming.setText(linkMan.getPyname());
        birth.setText(linkMan.getBirth_date());
        sex.setText(linkMan.getSex());
        type.setText(linkMan.getPaper_type());
        num.setText(linkMan.getPaper_number());
        address.setText(linkMan.getAddress());
    }

    //单击添加操作
    @OnClick(R.id.bt_save_link_man)
    void editLinkMan() {
        checkData();
        if (linkMan == null) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("Contacts[key]", QulianApplication.getInstance().getLoginUser().getAuth_key());
            map.put("Contacts[name]", nameString);
            map.put("Contacts[surname]", xingString);
            map.put("Contacts[pyname]", mingString);
            map.put("Contacts[birth_date]", birdString);
            map.put("Contacts[sex]", sexString);
            map.put("Contacts[paper_type]", pagerTypeString);
            map.put("Contacts[paper_number]", pagerNumberString);
            map.put("Contacts[describe]", "");//备注
            map.put("Contacts[address]", addressString);//收货地址
            new PacketStringReQuest(HttpConstants.ADD_LINKMAN, new HintInfoBean().setTag(getClass().getName()), map);
        } else {
            Map<String, String> map = new HashMap<String, String>();
            map.put("Contacts[id]", linkMan.getId());
            map.put("Contacts[name]", nameString);
            map.put("Contacts[surname]", xingString);
            map.put("Contacts[pyname]", mingString);
            map.put("Contacts[birth_date]", birdString);
            map.put("Contacts[sex]", sexString);
            map.put("Contacts[paper_type]", pagerTypeString);
            map.put("Contacts[paper_number]", pagerNumberString);
            map.put("Contacts[address]", addressString);
            new PacketStringReQuest(HttpConstants.EDIT_LINKMAN, new HintInfoBean().setTag(getClass().getName()), map);
        }
    }

    private String nameString;
    private String xingString;
    private String mingString;
    private String birdString;
    private String sexString;
    private String pagerTypeString;
    private String pagerNumberString;
    private String addressString;

    public void checkData() {
        nameString = name.getText().toString().trim();
        xingString = xing.getText().toString().trim();
        mingString = ming.getText().toString().trim();
        birdString = birth.getText().toString().trim();
        sexString = sex.getText().toString().trim();
        pagerTypeString = type.getText().toString().trim();
        pagerNumberString = num.getText().toString().trim();
        addressString = address.getText().toString().trim();
    }
}
