package com.quliantrip.qulian.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.util.TDevice;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.CircleImageView;
import com.quliantrip.qulian.view.dialog.UploadHeadImgDialog;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改个人信息
 */
public class EditPersonalInformationFragment extends BaseFragment {
    //    上传图片有关的操作
    public static final int PHOTO_REQUEST_CAMERA = 41001;// 拍照
    public static final int PHOTO_REQUEST_GALLERY = 4102;// 从相册中选择
    public static final int PHOTO_REQUEST_CUT = 4103;// 结果
    private Bitmap bitmap;
    public static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    @Bind(R.id.tv_edit_me_userImage)
    CircleImageView userImage;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_me_edit_personal_information, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initDate() {

    }

    //点击添加头像
    @OnClick(R.id.ll_me_edit_person_information_head_img)
    void addHeadImg(){
         final UploadHeadImgDialog dialog = new UploadHeadImgDialog(mContext, EditPersonalInformationFragment.this);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    //点击设置昵称
    @OnClick(R.id.rl_edit_me_nickname)
    void setNickName(){

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
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

}
