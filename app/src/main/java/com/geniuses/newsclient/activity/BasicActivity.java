package com.geniuses.newsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.geniuses.newsclient.R;

public abstract class BasicActivity extends AppCompatActivity {

    protected String TAG;
    protected Toolbar toolbar;
    protected Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivity());
        TAG = this.getClass().getSimpleName();
        this.context = this;
        initView();
        main();
    }

    /**
     * 初始化状态栏
     */
    protected void initTitle() {
        toolbar = (Toolbar) findViewById(R.id.basic_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        toolbar.setTitleTextColor(getResources().getColor(R.color.White));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.White));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    /**
     * 显示右侧按钮，并设置图片,获取按钮
     */
    protected ImageButton getRightButton(int resId) {
        ImageButton imageButton = (ImageButton) findViewById(R.id.basic_ibt_menu);
        imageButton.setVisibility(View.VISIBLE);
        imageButton.setImageResource(resId);
        return imageButton;
    }
    public abstract int getActivity();
    protected abstract void initView();
    protected abstract void main();
}
