package com.geniuses.newsclient.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.geniuses.newsclient.view.refreshlayout.RefreshLayout;
import com.geniuses.newsclient.view.refreshlayout.viewholder.StickinessRefreshViewHolder;
import com.mingle.widget.LoadingView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

public class NewsListFragment extends Fragment implements RefreshLayout.RefreshLayoutDelegate {
    private final int SUCCESS = 1;

    private ListView mListView;
    private String types[] = {"头条", "新闻", "财经", "体育", "娱乐", "军事", "教育", "科技", "NBA", "股票", "星座", "女性", "健康", "育儿"};
    private NewsListAdapter adapter;
    private ArrayList<NewsModel> data;
    private LoadingView mLoadingView;
    private RefreshLayout refreshLayout;

    private int start;
    private boolean isLoading;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    adapter.notifyDataSetChanged(data);
                    mLoadingView.setVisibility(View.GONE);
                    isLoading = false;
                    refreshLayout.endLoadingMore();
                    refreshLayout.endRefreshing();
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
        View view = inflater.inflate(R.layout.fragment_newslist, null);
        mLoadingView = view.findViewById(R.id.loadView);
        data = new ArrayList<>();
        adapter = new NewsListAdapter(getActivity(), data);
        mListView = view.findViewById(R.id.lv_newslist);
        mListView.setAdapter(adapter);
        //下拉刷新相关
        refreshLayout = view.findViewById(R.id.refresh_view_newslist);
        refreshLayout.setDelegate(this);
        StickinessRefreshViewHolder stickinessRefreshViewHolder = new StickinessRefreshViewHolder(getActivity(),true);
        stickinessRefreshViewHolder.setStickinessColor(R.color.colorPrimary);
        stickinessRefreshViewHolder.setRotateImage(R.mipmap.bg_refresh_stickiness);
        refreshLayout.setRefreshViewHolder(stickinessRefreshViewHolder);

        start = 0;
        isLoading = false;
        getData();
        mLoadingView.setVisibility(View.VISIBLE);
        return view;
    }

    public void getData() {
        isLoading = true;
        int index = getArguments().getInt("index");
        RequestParams params = new RequestParams(GlobalValue.NEWS);
        params.addParameter("channel", types[index]);
        params.addParameter("num", 20);
        params.addParameter("start", start);
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
    public void onRefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
        start = 0;
        data.clear();
        getData();
    }

    @Override
    public boolean onRefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        start = start+20;
        if(!isLoading){
            getData();
        }
        return false;
    }
}
