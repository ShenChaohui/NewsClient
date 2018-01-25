package com.geniuses.newsclient.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.adapter.NewsListAdapter;
import com.geniuses.newsclient.entity.NewsModel;
import com.geniuses.newsclient.manager.GsonManager;
import com.geniuses.newsclient.util.GlobalValue;
import com.mingle.widget.LoadingView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Sch on 2018/1/25.
 */

public class NewsListFragment extends Fragment {
    private final int SUCCESS = 1;

    private ListView mListView;
    private String types[] = {"头条", "新闻", "财经", "体育", "娱乐", "军事", "教育", "科技", "NBA", "股票", "星座", "女性", "健康", "育儿"};
    private NewsListAdapter adapter;
    private ArrayList<NewsModel> data;
    private RecyclerView.LayoutManager mLayoutManager;

    private LoadingView mLoadingView;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    adapter.notifyDataSetChanged(data);
                    mLoadingView.setVisibility(View.GONE);
                    break;
            }
        }
    };


    public static NewsListFragment newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("Fragment"+getArguments().getInt("index"),"onCreateView");
        View view = inflater.inflate(R.layout.fragment_newslist, null);
        mLoadingView = view.findViewById(R.id.loadView);
        mLoadingView.setVisibility(View.VISIBLE);
        data = new ArrayList<>();
        adapter = new NewsListAdapter(getActivity(), data);
        mListView = view.findViewById(R.id.lv_newslist);
        mListView.setAdapter(adapter);
        getData();

        return view;
    }

    public void getData() {
        int index = getArguments().getInt("index");
        RequestParams params = new RequestParams(GlobalValue.NEWS);
        params.addParameter("channel", types[index]);
        params.addParameter("num", 20);
        params.addParameter("start", 0);
        params.addParameter("appkey", GlobalValue.APPKEY);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    String code = obj.getString("code");
                    if (code.equals("10000")) {
                        JSONObject obj01 = obj.getJSONObject("result");
                        JSONObject obj02 = obj01.getJSONObject("result");
                        JSONArray array = obj02.getJSONArray("list");
                        for (int i = 0; i < array.length(); i++) {
                            NewsModel newsModel = GsonManager.getGson().fromJson(array.get(i).toString(), NewsModel.class);
                            data.add(newsModel);
                        }
                        Message msg = new Message();
                        msg.what = SUCCESS;
                        handler.sendMessage(msg);
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Fragment"+getArguments().getInt("index"),"onCreate");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Fragment"+getArguments().getInt("index"),"onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Fragment"+getArguments().getInt("index"),"onResume");
    }
}
