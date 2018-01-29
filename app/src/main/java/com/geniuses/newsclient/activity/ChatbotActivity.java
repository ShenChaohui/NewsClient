package com.geniuses.newsclient.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.adapter.ChatListAdapter;
import com.geniuses.newsclient.entity.ChatMsgModel;
import com.geniuses.newsclient.util.GlobalValue;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 聊天机器人
 * Created by Sch on 2018/1/29.
 */

public class ChatbotActivity extends BasicActivity implements View.OnClickListener {
    private EditText et;
    private Button btn;
    private ListView lv;
    private ChatListAdapter adapter;
    private ArrayList<ChatMsgModel> data;

    @Override
    public int getActivity() {
        return R.layout.activity_chatbox;
    }

    @Override
    protected void initView() {
        et = findViewById(R.id.et_chatbox);
        btn = findViewById(R.id.btn_chatbox);
        lv = findViewById(R.id.lv_chatbox);
        btn.setOnClickListener(this);
    }

    @Override
    protected void main() {
        data = new ArrayList<>();
        adapter = new ChatListAdapter(this,data);
        lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_chatbox){
            if(et.getText().toString().length()!=0){
                data.add(new ChatMsgModel(0,et.getText().toString()));
                adapter.updata(data);
            }
            RequestParams params = new RequestParams(GlobalValue.CHATBOX);
            params.addParameter("info",et.getText().toString());
            params.addParameter("appkey",GlobalValue.APPKEY);
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.e(TAG,result);
                    try {
                        JSONObject object = new JSONObject(result);
                        String code = object.getString("code");
                        if(code.equals("10000")){
                            JSONObject obj = object.getJSONObject("result");
                            String text = obj.getString("text");
                            data.add(new ChatMsgModel(1,text));
                            adapter.updata(data);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
    }
}
