package com.quliantrip.qulian.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.quliantrip.qulian.R;

/**
 * Created by Yuly on 2016/1/12.
 */
public class LoadingDialog extends Dialog {
    // 定义回调事件，用于dialog的点击事件
    public LoadingDialog(Context context, String strMessage) {
        super(context, R.style.dialog);
        this.setContentView(R.layout.loading_dialog);
        this.getWindow().getAttributes().gravity = Gravity.CENTER;
        TextView tvMsg = (TextView) this.findViewById(R.id.txt);

        if (!TextUtils.isEmpty(strMessage)) {
            tvMsg.setText(strMessage);
        } else {
            tvMsg.setVisibility(View.GONE);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

     /*   if (!hasFocus) {
            dismiss();
        }*/
    }
}