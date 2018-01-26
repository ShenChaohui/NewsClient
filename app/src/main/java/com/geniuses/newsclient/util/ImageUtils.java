package com.geniuses.newsclient.util;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.geniuses.newsclient.R;


import java.io.File;

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
}
