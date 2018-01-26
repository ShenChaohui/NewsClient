package com.geniuses.newsclient.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.geniuses.newsclient.R;


import java.io.File;
import java.util.concurrent.ExecutionException;

public class ImageUtils {
    public static void loadNetResource(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.mipmap.loading)
                .into(imageView);
    }
    public static void loadMipmap(Context context,int id,ImageView imageView){
        Glide.with(context)
                .load(id)
                .priority(Priority.HIGH)
                .centerCrop()
                .into(imageView);
    }
    public static void loadFile(Context context, File file, ImageView imageView){
        Glide.with(context)
                .load(file)
                .priority(Priority.HIGH)
                .into(imageView);
    }
    public static Bitmap getBitmap(Context context, String url){
        Bitmap bitmap = null;
        try {
             bitmap = Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .centerCrop()
                    .into(500,500)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
