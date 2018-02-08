package com.geniuses.newsclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.entity.WeatherInfo;

import java.util.ArrayList;
import java.util.List;

public class SuggestionAdapter extends BaseAdapter {
    private List<Integer> mIcon = new ArrayList<>();
    private List<String> mTitle = new ArrayList<>();
    private List<WeatherInfo.SuggestionBean.Entity> mData = new ArrayList<>();

    public SuggestionAdapter(WeatherInfo.SuggestionBean data) {
        mIcon.add(R.mipmap.ic_suggestion_comfort);
        mTitle.add("舒适度");
        mData.add(data.getComf());
        mIcon.add(R.mipmap.ic_suggestion_clothe);
        mTitle.add("穿衣");
        mData.add(data.getDrsg());
        mIcon.add(R.mipmap.ic_suggestion_flu);
        mTitle.add("感冒");
        mData.add(data.getFlu());
        mIcon.add(R.mipmap.ic_suggestion_car);
        mTitle.add("洗车");
        mData.add(data.getCw());
        mIcon.add(R.mipmap.ic_suggestion_sport);
        mTitle.add("运动");
        mData.add(data.getSport());
        mIcon.add(R.mipmap.ic_suggestion_travel);
        mTitle.add("旅游");
        mData.add(data.getTrav());
        mIcon.add(R.mipmap.ic_suggestion_uv);
        mTitle.add("紫外线");
        mData.add(data.getUv());
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
            convertView = LayoutInflater.from(context).inflate(R.layout.view_holder_suggestion, parent, false);
            holder = new ViewHolder();
            holder.ivIcon = convertView.findViewById(R.id.iv_icon);
            holder.tvLabel = convertView.findViewById(R.id.tv_label);
            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvDesc = convertView.findViewById(R.id.tv_desc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivIcon.setImageResource(mIcon.get(position));
        holder.tvLabel.setText(mTitle.get(position));
        holder.tvTitle.setText(mData.get(position).brf);
        holder.tvDesc.setText(mData.get(position).txt);
        return convertView;
    }

    public static class ViewHolder {
        public ImageView ivIcon;
        public TextView tvLabel;
        public TextView tvTitle;
        public TextView tvDesc;
    }
}
