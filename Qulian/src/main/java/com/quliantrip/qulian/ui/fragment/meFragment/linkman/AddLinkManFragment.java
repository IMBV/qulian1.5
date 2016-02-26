package com.quliantrip.qulian.ui.fragment.meFragment.linkman;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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
    @Bind(R.id.cet_linkman_number)
    ClearEditText phone;


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
        phone.setText(linkMan.getTel());
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
            map.put("Contacts[describe]", "");
            map.put("Contacts[address]", addressString);//收货地址
            map.put("Contacts[tel]", phoneString);
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
            map.put("Contacts[describe]", "");
            map.put("Contacts[paper_number]", pagerNumberString);
            map.put("Contacts[address]", addressString);
            map.put("Contacts[tel]", phoneString);
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
    private String phoneString;

    public void checkData() {
        nameString = name.getText().toString().trim();
        xingString = xing.getText().toString().trim();
        mingString = ming.getText().toString().trim();
        birdString = birth.getText().toString().trim();
        sexString = sex.getText().toString().trim();
        pagerTypeString = type.getText().toString().trim();
        pagerNumberString = num.getText().toString().trim();
        addressString = address.getText().toString().trim();
        phoneString = phone.getText().toString().trim();
    }

    //设置出生日期，birth
    @OnClick(R.id.rl_born_data)
    void checkBorn() {

        DatePickerDialog datePicker = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String birthBornString = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                birth.setText(birthBornString);
            }
        }, 1991, 00, 01);
        datePicker.show();
    }

    //弹出选择性别的单选框
    @OnClick(R.id.ll_me_check_sex)
    void checkSex() {

        AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mContext);
        //设置对话框的图标
//        builder.setIcon(R.drawable.header);
        //设置对话框的标题
        builder.setTitle("请选着男女");
        //0: 默认第一个单选按钮被选中
        builder.setSingleChoiceItems(R.array.my_link_man_sex, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String hoddy = getResources().getStringArray(R.array.my_link_man_sex)[which];
                sex.setText(hoddy);
            }
        });

        //添加一个确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //创建一个单选按钮对话框
        Dialog dialog = builder.create();
        dialog.show();
    }

    //选择证件类型
    @OnClick(R.id.rl_certificate_type)
    void checkCertificateType() {

        AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mContext);
        //设置对话框的图标
//        builder.setIcon(R.drawable.header);
        builder.setTitle("请选择证件类型");
        //0: 默认第一个单选按钮被选中
        builder.setSingleChoiceItems(R.array.my_link_man_certificate_type, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String hoddy = getResources().getStringArray(R.array.my_link_man_certificate_type)[which];
                type.setText(hoddy);
            }
        });
        //添加一个确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}
