package com.quliantrip.qulian.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quliantrip.qulian.view.dialog.LoadingDialog;

import de.greenrobot.event.EventBus;

/**
 * Created by yuly on 2015/11/26.
 */
public abstract class BaseDialogFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (Context) getActivity();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private LoadingDialog progressDialog;

    /**
     * 显示提示框
     *
     * @param title
     */
    public void showDialog(String title) {
        cancelDialog();
        progressDialog = new LoadingDialog(mContext, title);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public void showDialog_cancel(String title) {
        cancelDialog();
        progressDialog = new LoadingDialog(mContext, title);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
    }

    public void cancelDialog() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }
}
