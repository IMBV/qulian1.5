package com.quliantrip.qulian.view.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.ui.activity.mainAcivity.MainActivity;
import com.quliantrip.qulian.ui.fragment.mainFragment.MyFragment;

import java.io.File;

public class UploadHeadImgDialog extends Dialog {
    public UploadHeadImgDialog(Context context, Fragment fragment) {
        this(context, R.style.quick_option_dialog, fragment);
    }

    @SuppressLint("InflateParams")
    private UploadHeadImgDialog(final Context context, int defStyle, final Fragment fragment) {
        super(context, defStyle);
        //添加布局显示内容
        final View contentView = getLayoutInflater().inflate(R.layout.dialog_check_upload_head_img, null);
        //加载内容进行数据的处理
        Button buttonTuku = (Button) contentView.findViewById(R.id.bt_dialog_upload_img_tuku);
        Button buttonCamera = (Button) contentView.findViewById(R.id.bt_dialog_upload_img_camera);

        buttonTuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * 从相册获取
                 */
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                fragment.startActivityForResult(intent, MyFragment.PHOTO_REQUEST_GALLERY);
                dismiss();
            }
        });

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * 从相机获取
                 */
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                // 判断存储卡是否可以用，可用进行存储
                if (hasSdcard()) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File(Environment
                                    .getExternalStorageDirectory(), MyFragment.PHOTO_FILE_NAME)));
                }
                fragment.startActivityForResult(intent, MyFragment.PHOTO_REQUEST_CAMERA);
                dismiss();
            }
        });

        //设置标题和周围点击的效果
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                UploadHeadImgDialog.this.dismiss();
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

    //判断是否有sd卡
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
