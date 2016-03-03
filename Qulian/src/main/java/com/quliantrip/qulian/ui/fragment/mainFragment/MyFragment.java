package com.quliantrip.qulian.ui.fragment.mainFragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.ui.activity.meActivity.FeedbackActivity;
import com.quliantrip.qulian.ui.activity.meActivity.MyCollectActivity;
import com.quliantrip.qulian.ui.activity.meActivity.MyOrderActivity;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;
import com.quliantrip.qulian.view.CircleImageView;
import com.quliantrip.qulian.view.dialog.UploadHeadImgDialog;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人中心
 */
public class MyFragment extends BaseFragment {
//    上传图片有关的操作
    public static final int PHOTO_REQUEST_CAMERA = 41001;// 拍照
    public static final int PHOTO_REQUEST_GALLERY = 4102;// 从相册中选择
    public static final int PHOTO_REQUEST_CUT = 4103;// 结果
    private Bitmap bitmap;

    public static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    //用户的头像
    @Bind(R.id.ll_user_loading)
    LinearLayout ll_user_loading;
    @Bind(R.id.tv_me_userImage)
    CircleImageView userImage;
    //用户的名称
    @Bind(R.id.tv_me_username)
    TextView userName;

    private Bundle bundle = new Bundle();
    private View view;

    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.fragment_main_me, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 41) {
            initDate();
        }
        if (requestCode == 42) {
            initDate();
        }

        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (hasSdcard()) {
                tempFile = new File(Environment.getExternalStorageDirectory(),
                        PHOTO_FILE_NAME);
                crop(Uri.fromFile(tempFile));
            } else {
                ToastUtil.showToast(mContext, "未找到存储卡，无法存储照片！");
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            try {
                bitmap = data.getParcelableExtra("data");
                this.userImage.setImageBitmap(bitmap);
                boolean delete = tempFile.delete();
                System.out.println("delete = " + delete);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    //获取有没有内存卡
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    //加载显示数据
    @Override
    public void initDate() {
        //已经登录进行初始化值
        if (QulianApplication.getInstance().isLogin()) {
            userName.setText(QulianApplication.getInstance().getLoginUser().getUsername());
            ll_user_loading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final UploadHeadImgDialog dialog = new UploadHeadImgDialog(mContext, MyFragment.this);
                    dialog.setCancelable(true);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.show();
                }
            });
        } else {
            userName.setText("点击登录");
            ll_user_loading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.showMyLogin(MyFragment.this, 41);
                    ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
                }
            });
        }
    }

//    //登录界面
//    @OnClick(R.id.ll_user_loading)
//    void showMylogin() {
//        UIHelper.showMyLogin(this, 41);
//        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
//    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() != null) {
            initDate();
        }
    }

    //设置
    @OnClick(R.id.iv_me_seeting)
    void showMySetting() {
        if (QulianApplication.getInstance().isLogin()) {
            UIHelper.showMeSetting(this, 42);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        } else {
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }


    //我的收藏
    @OnClick(R.id.ll_me_collect)
    void showCollect() {
        if (QulianApplication.getInstance().isLogin()) {
            Intent intent = new Intent(mContext, MyCollectActivity.class);
            mContext.startActivity(intent);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        } else {
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //我的积分
    @OnClick(R.id.ll_me_integral)
    void showIntegral() {
        if (QulianApplication.getInstance().isLogin()) {
            ToastUtil.showToast(mContext, "该功能稍后开放");
//            UIHelper.showIntegral(mContext, null);
//            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        } else {
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //我的订单
    @OnClick(R.id.ll_me_orderList)
    void showOrderList() {
        if (QulianApplication.getInstance().isLogin()) {
            Intent intent = new Intent(mContext, MyOrderActivity.class);
            mContext.startActivity(intent);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        } else {
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //常用联系人
    @OnClick(R.id.ll_me_offen_lianxiren)
    void showOffLianxiren() {
        if (QulianApplication.getInstance().isLogin()) {
            UIHelper.showMyCommonInfo(mContext, null);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        } else {
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //我的优惠券
    @OnClick(R.id.ll_me_youhuijuan)
    void showYouhuijuan() {
        if (QulianApplication.getInstance().isLogin()) {
            ToastUtil.showToast(mContext, "该功能稍后开放");
//            UIHelper.showMyDisCount(mContext, null);
//            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        } else {
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //意见反馈
    @OnClick(R.id.ll_me_fankui)
    void showFankui() {
        Intent intent = new Intent(mContext, FeedbackActivity.class);
        mContext.startActivity(intent);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }
}
