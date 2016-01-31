package com.quliantrip.qulian.view.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.playMethod.SubBranchCheckAdapter;
import com.quliantrip.qulian.domain.choice.good.OrderSubmitBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodOrderSubmitItemBean;

import java.util.ArrayList;
import java.util.List;

public class SubBranchCheckDialog extends Dialog {
    private ListView listView;
    private List<OrderSubmitBean.DataEntity.BranchnameEntity> list;

    public SubBranchCheckDialog(Context context, List<OrderSubmitBean.DataEntity.BranchnameEntity> list,TextView checkSrore,PlayMethodOrderSubmitItemBean playMethodOrderSubmitItemBean) {
        this(context, R.style.quick_option_dialog, list,checkSrore,playMethodOrderSubmitItemBean);
    }

    @SuppressLint("InflateParams")
    private SubBranchCheckDialog(Context context, int defStyle, final List<OrderSubmitBean.DataEntity.BranchnameEntity> list,
                                 final TextView checkSrore, final PlayMethodOrderSubmitItemBean playMethodOrderSubmitItemBean) {
        super(context, defStyle);
        View contentView = getLayoutInflater().inflate(
                R.layout.dialog_subbranch_check, null);
        listView = (ListView) contentView.findViewById(R.id.lv_subBranch_check_list);
        listView.setAdapter(new SubBranchCheckAdapter((ArrayList<OrderSubmitBean.DataEntity.BranchnameEntity>) list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderSubmitBean.DataEntity.BranchnameEntity bean = list.get(position);
                checkSrore.setText(bean.getName());
                playMethodOrderSubmitItemBean.setStore(bean.getId());
                dismiss();
            }
        });

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SubBranchCheckDialog.this.dismiss();
                return true;
            }
        });
        super.setContentView(contentView);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setGravity(Gravity.BOTTOM);

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
    }

    //回调借口
//    public interface OnQuickOptionformClick {
//        void onQuickOptionClick(int id);
//    }
//
//    private OnQuickOptionformClick mListener;
}
