package com.geniuses.newsclient.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.geniuses.newsclient.R;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

public class ImageUtils {
    private static ImageOptions imageOptions = new ImageOptions.Builder()
            .setFadeIn(true)
//            .setCircular(true) //设置图片显示为圆形
            .setLoadingDrawableId(R.mipmap.loading)
            .setSquare(true) //设置图片显示为正方形
//            .setCrop(true).setSize(200, 200) //设置大小
            .setIgnoreGif(false) //忽略Gif图片
            .setRadius(20)//拐角弧度
            .setUseMemCache(true) //设置使用MemCache，默认true
            .build();

    public static void loadNetResource(String url, ImageView imageView) {
        x.image().bind(imageView, url, imageOptions);
    }
}
