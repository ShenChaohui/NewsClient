package com.geniuses.newsclient.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.geniuses.newsclient.R;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.Calendar;

public class ImageUtils {
    private static ImageOptions imageOptions = new ImageOptions.Builder()
            .setFadeIn(true)
//            .setCircular(true) //设置图片显示为圆形
            .setLoadingDrawableId(R.mipmap.loading)
            .setSquare(true) //设置图片显示为正方形
//            .setCrop(true).setSize(200, 200) //设置大小
            .setIgnoreGif(true) //忽略Gif图片
            .setRadius(20)//拐角弧度
            .setUseMemCache(true) //设置使用MemCache，默认true
            .build();

    public static void loadNetResource(String url, ImageView imageView) {
        x.image().bind(imageView, url, imageOptions);
    }

    public static int getIconByCode(Context context, String code) {
        return context.getResources().getIdentifier("ic_weather_icon_" + code, "mipmap", context.getPackageName());
    }

    public static int getWeatherImage(String weather) {
        if (weather.contains("转")) {
            weather = weather.substring(0, weather.indexOf("转"));
        }
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= 7 && hour < 19) {
            if (weather.contains("晴")) {
                return R.mipmap.header_weather_day_sunny;
            }
            if (weather.contains("云") || weather.contains("阴")) {
                return R.mipmap.header_weather_day_cloudy;
            }
            if (weather.contains("雨")) {
                return R.mipmap.header_weather_day_rain;
            }
            if (weather.contains("雪") || weather.contains("冰雹")) {
                return R.mipmap.header_weather_day_snow;
            }
            if (weather.contains("雾") || weather.contains("霾") || weather.contains("沙") || weather.contains("浮尘")) {
                return R.mipmap.header_weather_day_fog;
            }
            return R.mipmap.header_sunrise;
        } else {
            if (weather.contains("晴")) {
                return R.mipmap.header_weather_night_sunny;
            }
            if (weather.contains("云") || weather.contains("阴")) {
                return R.mipmap.header_weather_night_cloudy;
            }
            if (weather.contains("雨")) {
                return R.mipmap.header_weather_night_rain;
            }
            if (weather.contains("雪") || weather.contains("冰雹")) {
                return R.mipmap.header_weather_night_snow;
            }
            if (weather.contains("雾") || weather.contains("霾") || weather.contains("沙") || weather.contains("浮尘")) {
                return R.mipmap.header_weather_day_fog;
            }
            return R.mipmap.header_sunset;
        }
    }
}
