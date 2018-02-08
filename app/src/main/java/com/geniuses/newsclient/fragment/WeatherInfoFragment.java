package com.geniuses.newsclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.activity.WeatherInfoActivity;
import com.geniuses.newsclient.datebase.DatabaseOpenHelper;
import com.geniuses.newsclient.entity.WeatherInfo;
import com.geniuses.newsclient.entity.WeatherModel;

import org.xutils.DbManager;

import java.util.ArrayList;

/**
 * Created by Sch on 2018/2/8.
 */

public class WeatherInfoFragment extends Fragment {
    private String TAG;
    private ArrayList<WeatherModel> mData;
    private TextView cityName;

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
        TAG = weatherModel.getCityName()+"Fragment";
        cityName = view.findViewById(R.id.tv_weather_info_cityName);
        cityName.setText(weatherModel.getCityName());
        return view;
    }
}
