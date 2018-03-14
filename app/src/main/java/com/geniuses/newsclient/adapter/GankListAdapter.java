package com.geniuses.newsclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.entity.GankModel;
import com.geniuses.newsclient.util.ImageUtils;

import java.util.ArrayList;


/**
 * Created by Sch on 2018/3/14.
 */

public class GankListAdapter extends BaseAdapter {
    private ArrayList<GankModel> data;
    private Context context;

    public GankListAdapter(Context context, ArrayList<GankModel> data) {
        this.data = data;
        this.context = context;
    }

    public void notifyDataSetChanged(ArrayList<GankModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
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
            convertView = View.inflate(context, R.layout.item_gank, null);
            viewHolder.tv_desc = convertView.findViewById(R.id.tv_item_gank_desc);
            viewHolder.tv_publisheAt = convertView.findViewById(R.id.tv_item_gank_publisheAt);
            viewHolder.tv_publisheAt2 = convertView.findViewById(R.id.tv_item_gank_publisheAt2);
            viewHolder.tv_who = convertView.findViewById(R.id.tv_item_gank_who);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (viewHolder != null) {
            viewHolder.tv_desc.setText(data.get(position).getDesc());
            viewHolder.tv_publisheAt.setText(data.get(position).getPublishedAt().split("T")[0]);
            viewHolder.tv_publisheAt2.setText(data.get(position).getPublishedAt().split("T")[1].split("Z")[0].split("\\.")[0]);
            viewHolder.tv_who.setText(data.get(position).getWho());
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_desc;
        TextView tv_publisheAt;
        TextView tv_publisheAt2;
        TextView tv_who;
    }
}
