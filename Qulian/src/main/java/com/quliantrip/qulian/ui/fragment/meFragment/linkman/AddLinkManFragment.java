package com.quliantrip.qulian.ui.fragment.meFragment.linkman;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.LinkManBean;
import com.quliantrip.qulian.view.ClearEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 添加常用联系人
 */
public class AddLinkManFragment extends Fragment {
    private Context mContext;
    private View view;

    @Bind(R.id.cet_link_man_name)
    ClearEditText name;
//    @Bind(R.id.cet_link_man_name)
//    ClearEditText name;
    @Bind(R.id.bt_save_link_man)
    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
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
        LinkManBean.LinkMan linkMan = (LinkManBean.LinkMan) bundle.getSerializable("linkMan");
        intidate(linkMan);
    }

    private void intidate(LinkManBean.LinkMan linkMan) {
        if (linkMan!= null){
            button.setText("修改");
            name.setText(linkMan.getName());
        }
    }
}
