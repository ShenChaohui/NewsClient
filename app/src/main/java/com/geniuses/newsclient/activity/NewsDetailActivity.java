package com.geniuses.newsclient.activity;

import android.content.Intent;
import android.widget.ImageView;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.entity.NewsModel;
import com.geniuses.newsclient.util.ImageUtils;

/**
 * Created by Sch on 2017/12/1.
 */

public class NewsDetailActivity extends BasicActivity{
    private ImageView iv_pic;

    @Override
    public int getActivity() {
        return R.layout.activity_newsdetail;
    }

    @Override
    protected void initView() {
        initTitle();
        iv_pic = findViewById(R.id.iv_news_detail_pic);

    }

    @Override
    protected void main() {
        Intent intent = getIntent();
        NewsModel newsModel = (NewsModel) intent.getSerializableExtra("newsModel");
        toolbar.setSubtitle(newsModel.getTitle());
        ImageUtils.loadNetResource(this,newsModel.getPic(),iv_pic);

    }
}
