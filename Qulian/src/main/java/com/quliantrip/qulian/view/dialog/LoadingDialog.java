package com.quliantrip.qulian.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.quliantrip.qulian.R;

/**
 * 加载的旋转的弹框
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
        initAnimation();
    }

    private void initAnimation() {
        ImageView antivirus_move = (ImageView) findViewById(R.id.iv_loading_move);
        RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setRepeatCount(Animation.INFINITE);

        //添加动画插曲器
        LinearInterpolator interpolator = new LinearInterpolator();
        animation.setInterpolator(interpolator);
        antivirus_move.startAnimation(animation);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

     /*   if (!hasFocus) {
            dismiss();
        }*/
    }
}