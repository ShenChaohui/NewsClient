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
    public ChatListAdapter(Context context, ArrayList<ChatMsgModel> data){
        this.context = context;
        this.data = data;
        this.inflater= inflater.from(context);
    }
    public void updata(ArrayList<ChatMsgModel> data){
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            if (data.get(i).getType() == 1) {
                view = inflater.inflate(R.layout.item_chatbox_system_msg,viewGroup, false);
                TextView tv_msg = (TextView) view.findViewById(R.id.tv_my_ask_content_system_msg);
                tv_msg.setText(data.get(i).getMsg());
            }else if(data.get(i).getType() == 0){
                view = inflater.inflate(R.layout.item_chatbox_my_msg, viewGroup,false);
                TextView tv_msg  = (TextView) view.findViewById(R.id.tv_my_ask_content_my_msg);
                tv_msg.setText(data.get(i).getMsg());
            }
        }
        return view;
    }
}
