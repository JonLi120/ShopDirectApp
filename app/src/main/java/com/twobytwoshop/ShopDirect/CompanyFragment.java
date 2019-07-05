package com.twobytwoshop.ShopDirect;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.twobytwoshop.ShopDirect.core.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CompanyFragment extends BaseFragment {

    @BindView(R.id.webView)
    WebView webView;
    private Unbinder unbinder;

    public static CompanyFragment newInstance() {
        Bundle args = new Bundle();

        CompanyFragment fragment = new CompanyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((MainActivity) mActivity).changeMenuLayout(true, false);
        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebSettings webSettings = webView.getSettings();
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

        webView.setWebViewClient(new WebViewClient() {
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

        webView.loadUrl("https://www.google.com.tw");
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();

        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }

        super.onDestroyView();
    }
}
