package com.geniuses.newsclient.activity;

import android.content.Intent;
import android.util.Log;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.entity.WeatherInfo;
import com.geniuses.newsclient.manager.GsonManager;

/**
 * Created by Sch on 2018/2/7.
 */

public class WeatherInfoActivity extends BasicActivity {
    private WeatherInfo weatherInfo;
    @Override
    public int getActivity() {
        return R.layout.activity_weather_info;
    }

    @Override
    protected void initView() {
        initTitle();

    }

    @Override
    protected void main() {
        Intent intent = getIntent();
        String weatherJson = intent.getStringExtra("weatherJson");
        weatherInfo = GsonManager.getGson().fromJson(weatherJson, WeatherInfo.class);
        Log.e("test",weatherInfo.getDaily_forecast().get(0).getCond().getTxt_n());
    }
}
