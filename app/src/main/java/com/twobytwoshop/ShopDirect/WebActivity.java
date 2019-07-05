package com.twobytwoshop.ShopDirect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.twobytwoshop.ShopDirect.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebActivity extends BaseActivity {

    private static final String KEY_URL = "KEY_URL";
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cancel_btn)
    ImageButton cancelBtn;

    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(KEY_URL, url);
        context.startActivity(intent);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);


        String url = getIntent().getStringExtra(KEY_URL);

        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
//        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setDisplayZoomControls(false);
//        webSettings.setBuiltInZoomControls(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setAllowFileAccess(false);
        webSettings.setSavePassword(false);
        webSettings.setLoadsImagesAutomatically(true);

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });

        if (url != null) {
            web.loadUrl(url);
        } else {
            finish();
        }
    }

    @OnClick(R.id.cancel_btn)
    protected void onClick(View view) {
        if (view.getId() == R.id.cancel_btn) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        if (web != null) {
            web.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            web.clearHistory();

            ((ViewGroup) web.getParent()).removeView(web);
            web.destroy();
            web = null;
        }
        super.onDestroy();
    }
}
