package com.geniuses.newsclient.activity;

import android.content.Intent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.entity.GankModel;
import com.geniuses.newsclient.entity.NewsModel;

/**
 * Created by Sch on 2018/3/14.
 */

public class GankDetailActivity extends BasicActivity{
    private WebView mWebView;
    @Override
    public int getActivity() {
        return R.layout.activity_gank_detail;
    }

    @Override
    protected void initView() {
        initTitle();
        mWebView = findViewById(R.id.web_gank_detail);
    }

    @Override
    protected void main() {
        Intent intent = getIntent();
        GankModel gankModel = (GankModel) intent.getSerializableExtra("gankModel");
        toolbar.setSubtitle(gankModel.getDesc());
        mWebView.getSettings().setJavaScriptEnabled(true);//支持javascript
        mWebView.requestFocus();//触摸焦点起作用mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);//取消滚动条
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置允许js弹出alert对话框
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl(gankModel.getUrl());

    }
}
