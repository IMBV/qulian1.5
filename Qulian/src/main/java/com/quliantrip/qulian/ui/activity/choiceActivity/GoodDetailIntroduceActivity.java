package com.quliantrip.qulian.ui.activity.choiceActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.net.constant.HttpConstants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 玩法订单详情页
 */
public class GoodDetailIntroduceActivity extends SwipeBackActivity {
    @Bind(R.id.tv_good_des_wv)
    WebView webView;
    @Bind(R.id.tv_title_name)
    TextView titleName;

    private String id;
    private String type;
    private String field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chioce_good_introduce);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("goodId");
        type = getIntent().getStringExtra("type");
        field = getIntent().getStringExtra("field");
        if ("desc".equals(field)) {
            titleName.setText("详细信息");
        } else if ("pricedesc".equals(field)) {
            titleName.setText("费用说明");
        } else {
            titleName.setText("达人经验");
        }
        if (getIntent().getStringExtra("goodId") != null && getIntent().getStringExtra("field") != null
                && getIntent().getStringExtra("type") != null) {
            webView.loadUrl("http://wap.v2.quliantrip.com/index.php?r=index%2Furlxp&id=" + id + "&field=" + field + "&type=" + type);
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

    @OnClick(R.id.iv_simple_back)
    void back() {
        finish();
        overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }
}
