package com.geniuses.newsclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.entity.ChatMsgModel;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/5.
 */

public class ChatListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    ArrayList<ChatMsgModel> data;

    public ChatListAdapter(Context context, ArrayList<ChatMsgModel> data) {
        this.context = context;
        this.data = data;
        this.inflater = inflater.from(context);
    }

    public void updata(ArrayList<ChatMsgModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getType() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            if (getItemViewType(i) == 0) {
                view = inflater.inflate(R.layout.item_chatbox_system_msg, null);
                viewHolder.tv_msg = view.findViewById(R.id.tv_chatbox_system_msg);
            } else if (getItemViewType(i) == 1) {
                view = inflater.inflate(R.layout.item_chatbox_my_msg, null);
                viewHolder.tv_msg = view.findViewById(R.id.tv_chatbox_my_msg);
            }
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_msg.setText(data.get(i).getMsg());
        return view;
    }

    class ViewHolder {
        TextView tv_msg;
    }
}
