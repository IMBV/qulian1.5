package com.quliantrip.qulian.ui.fragment.meFragment;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 用户
 */
public class UserProtocolFragment extends BaseFragment {
    @Bind(R.id.wv_uset_protocel)
    WebView webView;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_me_user_protocol,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void initDate() {
        webView.loadUrl("http://wap.v2.quliantrip.com/index.php?r=login/agreement");
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });

    }
}
