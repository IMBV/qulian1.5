package com.quliantrip.qulian.ui.fragment.meFragment.searchBackPassword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseDialogFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.me.SearchBackPhoneBean;
import com.quliantrip.qulian.util.ToastUtil;

import butterknife.ButterKnife;

/**
 * 手机找回
 */
public class SearchBackPhoneFragment extends BaseDialogFragment {
    //使用到的变量
    private View view;
    private SearchBackGetCodeFragment searchBackGetCodeFragment;
    private SearchBackCheckCodeFragment searchBackCheckCodeFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_search_back_password_phone, null);
        ButterKnife.bind(this, view);
        searchBackGetCodeFragment = new SearchBackGetCodeFragment();
        getFragmentManager().beginTransaction().replace(R.id.fl_search_back_phone, searchBackGetCodeFragment).commit();
        return view;
    }

    public void onEventMainThread(BaseJson bean) {
        if (bean != null && (this.getClass().getName() + "checkCode").equals(bean.getTag())) {
            SearchBackPhoneBean searchBackPhoneBean = (SearchBackPhoneBean) bean;
            if (searchBackPhoneBean.getCode() == 200) {
                //这里进行修改密码的界面的切换
                searchBackCheckCodeFragment = new SearchBackCheckCodeFragment();
                Bundle bundle = new Bundle();
                bundle.putString("reset_key",searchBackPhoneBean.getData().getReset_key());
                bundle.putString("tel",searchBackGetCodeFragment.getPhone());
                searchBackCheckCodeFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fl_search_back_phone, searchBackCheckCodeFragment).commit();
            } else {
                ToastUtil.showToast(mContext, searchBackPhoneBean.getMsg());
            }
        }
    }
}
