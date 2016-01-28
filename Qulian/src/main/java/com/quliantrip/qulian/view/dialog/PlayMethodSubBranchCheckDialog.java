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
import com.quliantrip.qulian.adapter.choiceAdapter.playMethod.PlayMethodSubBranchCheckAdapter;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodOrderSubmitBean;

import java.util.ArrayList;
import java.util.List;

public class PlayMethodSubBranchCheckDialog extends Dialog {
    private ListView listView;
    private List<PlayMethodOrderSubmitBean.DataEntity.BranchnameEntity> list;

    public PlayMethodSubBranchCheckDialog(Context context, List<PlayMethodOrderSubmitBean.DataEntity.BranchnameEntity> list, TextView checkSrore) {
        this(context, R.style.quick_option_dialog, list, checkSrore);
    }

    @SuppressLint("InflateParams")
    private PlayMethodSubBranchCheckDialog(Context context, int defStyle, final List<PlayMethodOrderSubmitBean.DataEntity.BranchnameEntity> list, final TextView checkSrore) {
        super(context, defStyle);
        View contentView = getLayoutInflater().inflate(
                R.layout.dialog_subbranch_check, null);
        listView = (ListView) contentView.findViewById(R.id.lv_subBranch_check_list);
        listView.setAdapter(new PlayMethodSubBranchCheckAdapter((ArrayList<PlayMethodOrderSubmitBean.DataEntity.BranchnameEntity>) list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlayMethodOrderSubmitBean.DataEntity.BranchnameEntity bean = list.get(position);
                checkSrore.setText(bean.getName());
                dismiss();
            }
        });

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                PlayMethodSubBranchCheckDialog.this.dismiss();
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
