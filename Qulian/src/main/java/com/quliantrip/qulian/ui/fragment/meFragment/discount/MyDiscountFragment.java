package com.quliantrip.qulian.ui.fragment.meFragment.discount;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

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

    //显示输入优惠码的的dialog对话框
    public void showSetPassword() {
        //建立弹出对话框
        AlertDialog.Builder builder = new Builder(mContext);
        builder.setCancelable(false);
        View view = View.inflate(mContext, R.layout.layout_my_discount_binding, null);
        final TextView discountNumber = (TextView) view.findViewById(R.id.cet_my_discount_content);
        TextView confire = (TextView) view.findViewById(R.id.tv_discount_binding_confirct);
        TextView cancel = (TextView) view.findViewById(R.id.tv_discount_binding_cancle);
        //确定的点击事件
        confire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = discountNumber.getText().toString().trim();
                if (TextUtils.isEmpty(number)) {
                    ToastUtil.showToast(mContext, "优惠码不能为空");
                    return;
                }
                ToastUtil.showToast(mContext, "请求数据刷新界面");
            }
        });

        //取消的点击事件
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
//		builder.setView(view);
        dialog = builder.create();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
    }
}
