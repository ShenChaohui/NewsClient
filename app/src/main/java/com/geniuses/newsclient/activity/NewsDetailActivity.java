package com.geniuses.newsclient.activity;

import android.content.Intent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.entity.NewsModel;
import com.geniuses.newsclient.util.ImageUtils;

/**
 * Created by Sch on 2017/12/1.
 */

public class NewsDetailActivity extends BasicActivity{
    private WebView mWebView;
    @Override
    public int getActivity() {
        return R.layout.activity_newsdetail;
    }

    @Override
    protected void initView() {
        initTitle();
        mWebView = findViewById(R.id.web_view_news_detail);


    }

    @Override
    protected void main() {
        Intent intent = getIntent();
        NewsModel newsModel = (NewsModel) intent.getSerializableExtra("newsModel");
        toolbar.setSubtitle(newsModel.getTitle());

        mWebView.getSettings().setJavaScriptEnabled(true);//支持javascript
        mWebView.requestFocus();//触摸焦点起作用mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);//取消滚动条
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置允许js弹出alert对话框
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl(newsModel.getUrl());

    }
}
