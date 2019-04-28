package com.geniuses.newsclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.entity.WeatherInfo;
import com.geniuses.newsclient.entity.WeatherModel;
import com.geniuses.newsclient.util.GsonUtil;
import com.geniuses.newsclient.util.ImageUtils;

import java.util.ArrayList;


/**
 * Created by Sch on 2018/2/7.
 */

public class WeatherListAdapter extends BaseAdapter {
    private ArrayList<WeatherModel> mData;
    private Context context;

    public WeatherListAdapter(ArrayList<WeatherModel> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    public void updateData(ArrayList<WeatherModel> data) {
        this.mData = data;
        notifyDataSetChanged();
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_weather_list, null);
            viewHolder.ivWeatherIcon = convertView.findViewById(R.id.iv_item_weather_icon);
            viewHolder.tvCityName = convertView.findViewById(R.id.tv_item_cityName);
            viewHolder.tvTemp = convertView.findViewById(R.id.tv_item_temp);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (viewHolder != null) {
            WeatherModel weatherModel = mData.get(position);
            String weatherJson = weatherModel.getWeatherJson();
            WeatherInfo weatherInfo = GsonUtil.parseJsonWithGson(weatherJson, WeatherInfo.class);
            if (weatherInfo != null) {
                viewHolder.tvCityName.setText(weatherModel.getCityName());
                viewHolder.tvTemp.setText(context.getString(R.string.tempC, weatherInfo.getNow().getTmp()));
                viewHolder.ivWeatherIcon.setImageResource(ImageUtils.getIconByCode(context, weatherInfo.getNow().getCond().getCode()));
            }
        }
        return convertView;
    }

    class ViewHolder {
        ImageView ivWeatherIcon;
        TextView tvCityName;
        TextView tvTemp;
    }
}
