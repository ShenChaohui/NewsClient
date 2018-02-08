package com.geniuses.newsclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.entity.WeatherInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HourlyForecastAdapter extends BaseAdapter {
    private List<WeatherInfo.HourlyForecastBean> mData;

    public HourlyForecastAdapter(List<WeatherInfo.HourlyForecastBean> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_holder_hourly_forecast, parent, false);
            holder = new ViewHolder();
            holder.tvTime = convertView.findViewById(R.id.tv_time);
            holder.tvWeather = convertView.findViewById(R.id.tv_weather);
            holder.tvTemp = convertView.findViewById(R.id.tv_temp);
            holder.tvWind = convertView.findViewById(R.id.tv_wind);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTime.setText(timeFormat(mData.get(position).getDate()));
        holder.tvWeather.setText(mData.get(position).getCond().getTxt());
        holder.tvTemp.setText(context.getString(R.string.temp, mData.get(position).getTmp()));
        holder.tvWind.setText(windFormat(context, mData.get(position).getWind().getSc()));
        return convertView;
    }

    private static class ViewHolder {
        public TextView tvTime;
        public TextView tvWeather;
        public TextView tvTemp;
        public TextView tvWind;
    }

    private String timeFormat(String time) {
        SimpleDateFormat fromSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        SimpleDateFormat toSdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        try {
            return toSdf.format(fromSdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toSdf.format(new Date());
    }

    private String windFormat(Context context, String wind) {
        if (wind.contains("风")) {
            return wind;
        } else {
            return context.getString(R.string.hourly_forecast_wind, wind);
        }
    }
}
