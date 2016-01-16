package com.quliantrip.qulian.ui.fragment.meFragment.discount;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.TuanBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.fragment.meFragment.discount.content.MyDiscountContentFragment;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDiscountFragment extends BasePageCheckFragment {
    private View view;
    private AlertDialog dialog;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_me_discount, null);
        ButterKnife.bind(this, view);
        ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_discount_container, new MyDiscountContentFragment()).commit();
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ctl", "tuan");
        map.put("r_type", "1");
        return new QuestBean(map, new TuanBean().setTag(getClass().getName()), HttpConstants.HOST_ADDR_ROOT_NET);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {

    }

    //点击绑定优惠券吗
    @OnClick(R.id.bt_add_discount)
    void bindingDisCount() {
        showSetPassword();
    }

    public void showSetPassword() {
        //建立弹出对话框
        AlertDialog.Builder builder = new Builder(mContext);
        builder.setCancelable(false);
        View view = View.inflate(mContext, R.layout.layout_my_discount_binding, null);
//        //获取加载布局的内容
//        final TextView et_password_set = (TextView) view.findViewById(R.id.et_password_set);
//        final TextView et_password_repeat = (TextView) view.findViewById(R.id.et_password_repeat);
//        //获取按钮
        TextView confire = (TextView) view.findViewById(R.id.tv_discount_binding_confirct);
        TextView cancel = (TextView) view.findViewById(R.id.tv_discount_binding_cancle);
        //确定的点击事件
        confire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(mContext,"绑定");
            }
        });
//        confire.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                //判断，密码不为空
//                String password = et_password_set.getText().toString().trim();
//                if (TextUtils.isEmpty(password)) {
//                    Toast.makeText(getApplicationContext(), "密码不能为空", 0).show();
//                    return;
//                }
//                //对比两次输入是否一致
//                String cPassword = et_password_repeat.getText().toString().trim();
//                if (password.equals(cPassword)) {
//                    Editor edit = sp.edit();
//                    String pwdMd5 = CommentHelp.getMD5(password);
//                    edit.putString("password", pwdMd5);
//                    edit.commit();
//                    dialog.dismiss();
//                } else {
//                    Toast.makeText(getApplicationContext(), "密码前后输入不一致", 0).show();
//                }
//            }
//        });
        //取消的点击事件
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
//        cancel.setOnClickListener(new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//		builder.setView(view);
        dialog = builder.create();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();

    }
}
