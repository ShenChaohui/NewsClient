package com.geniuses.newsclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.geniuses.newsclient.R;
import com.geniuses.newsclient.activity.WeatherInfoActivity;
import com.geniuses.newsclient.adapter.DailyForecastAdapter;
import com.geniuses.newsclient.adapter.HourlyForecastAdapter;
import com.geniuses.newsclient.adapter.SuggestionAdapter;
import com.geniuses.newsclient.entity.WeatherInfo;
import com.geniuses.newsclient.entity.WeatherModel;
import com.geniuses.newsclient.manager.GsonManager;
import com.geniuses.newsclient.util.ImageUtils;
import java.util.ArrayList;

/**
 * Created by Sch on 2018/2/8.
 */

public class WeatherInfoFragment extends Fragment {
    private WeatherInfo weatherInfo;
    private ArrayList<WeatherModel> mData;
    private AppBarLayout mAppBar;
    private CollapsingToolbarLayout collapsingToolbar;
    private NestedScrollView mScrollView;
    private Toolbar toolbar;

    private ImageView ivWeatherImage;
    private ImageView ivWeatherIcon;
    private TextView tvTemp;
    private TextView tvMaxTemp;
    private TextView tvMinTemp;
    private TextView tvMoreInfo;
    private ListView lvHourlyForecast;
    private ListView lvDailyForecast;
    private ListView lvSuggestion;

    public static WeatherInfoFragment newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        WeatherInfoFragment fragment = new WeatherInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_info, null);
        WeatherInfoActivity activity = (WeatherInfoActivity) getActivity();
        mData = activity.getWeatherModelList();
        WeatherModel weatherModel = mData.get(getArguments().getInt("index"));
        weatherInfo = GsonManager.getGson().fromJson(weatherModel.getWeatherJson(),WeatherInfo.class);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        mAppBar = view.findViewById(R.id.appbar);
        collapsingToolbar = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(weatherModel.getCityName());
        mScrollView = view.findViewById(R.id.nested_scroll_view);

        ivWeatherImage = view.findViewById(R.id.iv_weather_image);
        ivWeatherIcon = view.findViewById(R.id.iv_weather_icon);
        tvTemp = view.findViewById(R.id.tv_temp);
        tvMaxTemp = view.findViewById(R.id.tv_max_temp);
        tvMinTemp = view.findViewById(R.id.tv_min_temp);
        tvMoreInfo = view.findViewById(R.id.tv_more_info);
        lvHourlyForecast = view.findViewById(R.id.slv_weather_info_hourly_forecast);
        lvDailyForecast = view.findViewById(R.id.slv_weather_info_daily_forecast);
        lvSuggestion = view.findViewById(R.id.slv_weather_info_suggestion);

        ivWeatherImage.setImageResource(ImageUtils.getWeatherImage(weatherInfo.getNow().getCond().getTxt()));
        ivWeatherIcon.setImageResource(ImageUtils.getIconByCode(getActivity(),weatherInfo.getNow().getCond().getCode()));
        tvTemp.setText(getString(R.string.tempC, weatherInfo.getNow().getTmp()));
        tvMaxTemp.setText(getString(R.string.now_max_temp, weatherInfo.getDaily_forecast().get(0).getTmp().getMax()));
        tvMinTemp.setText(getString(R.string.now_min_temp, weatherInfo.getDaily_forecast().get(0).getTmp().getMax()));
        StringBuilder sb = new StringBuilder();
        sb.append("体感")
                .append(weatherInfo.getNow().getFl())
                .append("°");
        if (weatherInfo.getAqi() != null && !TextUtils.isEmpty(weatherInfo.getAqi().getCity().getQlty())) {
            sb.append("  ")
                    .append(weatherInfo.getAqi().getCity().getQlty().contains("污染") ? "" : "空气")
                    .append(weatherInfo.getAqi().getCity().getQlty());
        }
        sb.append("  ")
                .append(weatherInfo.getNow().getWind().getDir())
                .append(weatherInfo.getNow().getWind().getSc())
                .append(weatherInfo.getNow().getWind().getSc().endsWith("风") ? "" : "级");
        tvMoreInfo.setText(sb.toString());
        lvHourlyForecast.setAdapter(new HourlyForecastAdapter(weatherInfo.getHourly_forecast()));
        lvDailyForecast.setAdapter(new DailyForecastAdapter(weatherInfo.getDaily_forecast()));
        lvSuggestion.setAdapter(new SuggestionAdapter(weatherInfo.getSuggestion()));
        return view;
    }
}
