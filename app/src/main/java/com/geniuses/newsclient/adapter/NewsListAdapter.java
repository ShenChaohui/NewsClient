package com.geniuses.newsclient.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.geniuses.newsclient.R;
import com.geniuses.newsclient.entity.NewsModel;
import com.geniuses.newsclient.util.ImageCache;
import com.geniuses.newsclient.util.ImageUtils;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Sch on 2017/12/1.
 */

public class NewsListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NewsModel> data;
    private ImageCache imageCache = new ImageCache();

    public NewsListAdapter(Context context, ArrayList<NewsModel> data) {
        this.context = context;
        this.data = data;
    }

    public void notifyDataSetChanged(ArrayList<NewsModel> data) {
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
            convertView = View.inflate(context, R.layout.item_news, null);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_item_news_title);
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_item_news_time);
            viewHolder.iv_pic = (ImageView) convertView.findViewById(R.id.iv_item_news_pic);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (viewHolder != null) {
            viewHolder.tv_title.setText(data.get(position).getTitle());
            viewHolder.tv_date.setText(data.get(position).getTime());
//            ImageUtils.loadNetResource(context, data.get(position).getPic(), viewHolder.iv_pic);
            final String picUrl = data.get(position).getPic();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap bitmap = Glide.with(context).load(picUrl).asBitmap().centerCrop().into(500,500).get();
                        imageCache.addBitmap2Cache(picUrl,bitmap);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            viewHolder.iv_pic.setImageBitmap(imageCache.getBitmapFromCache(picUrl));
        }
        return convertView;
    }
    class ViewHolder {
        TextView tv_title;
        TextView tv_date;
        ImageView iv_pic;
    }
}
