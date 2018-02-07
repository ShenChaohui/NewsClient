package com.geniuses.newsclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.geniuses.newsclient.R;
import com.geniuses.newsclient.entity.WeatherModel;
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
    public void updateData(ArrayList<WeatherModel> data){
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
            convertView = View.inflate(context, R.layout.item_weather, null);
            viewHolder.tv_city = convertView.findViewById(R.id.tv_item_weather_city);
            viewHolder.tv_weather = convertView.findViewById(R.id.tv_item_weather_weather);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (viewHolder != null) {
            viewHolder.tv_city.setText(mData.get(position).getCity());
            viewHolder.tv_weather.setText(mData.get(position).getNowWeather());
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_city;
        TextView tv_weather;
    }
}
