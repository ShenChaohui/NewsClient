package com.geniuses.newsclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.activity.NewsDetailActivity;
import com.geniuses.newsclient.adapter.NewsRecyclerViewAdapter;
import com.geniuses.newsclient.entity.NewsModel;
import com.geniuses.newsclient.util.GlobalValue;
import com.geniuses.newsclient.util.GsonUtil;
import com.mingle.widget.LoadingView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

public class NewsListFragment extends Fragment {
    private final int SUCCESS = 0X01;
    private final int ERROR = 0X02;
    private final int CANCELLED = 0x03;
    private String TAG;
    private RecyclerView mRecyclerView;
    private String types[] = {"头条", "新闻", "财经", "体育", "娱乐", "军事", "教育", "科技", "NBA", "股票", "星座", "女性", "健康", "育儿"};
    private NewsRecyclerViewAdapter adapter;
    private ArrayList<NewsModel> data;
    private LoadingView mLoadingView;
    private RefreshLayout refreshLayout;
    private int start;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    adapter.notifyDataSetChanged();
                    mLoadingView.setVisibility(View.GONE);
                    refreshLayout.finishLoadmore();
                    refreshLayout.finishRefresh();
                    break;
                case ERROR:
                    mLoadingView.setVisibility(View.GONE);
                    break;
                case CANCELLED:
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
        View view = inflater.inflate(R.layout.fragment_newslist, null);
        TAG = types[getArguments().getInt("index")];
        mLoadingView = view.findViewById(R.id.loadView);
        data = new ArrayList<>();
        adapter = new NewsRecyclerViewAdapter(getActivity(), data);
        mRecyclerView = view.findViewById(R.id.rv_newslist);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        adapter.setOnRecyclerItemClickListener(new NewsRecyclerViewAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsModel newsModel = data.get(position);
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("newsModel", newsModel);
                startActivity(intent);
            }
        });
        start = 0;
        getNewsData();

        mLoadingView.setVisibility(View.VISIBLE);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshNews();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                loadMoreNews();
            }
        });
        return view;
    }

    public void getNewsData() {
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
                    if (start == 0) {//用来判断是刷新还是加载更多
                        data.clear();
                    }
                    JSONObject obj = new JSONObject(result);
                    String code = obj.getString("code");
                    if (code.equals("10000")) {
                        JSONObject obj01 = obj.getJSONObject("result");
                        JSONObject obj02 = obj01.getJSONObject("result");
                        JSONArray array = obj02.getJSONArray("list");
                        ArrayList<NewsModel> newsModels = (ArrayList<NewsModel>) GsonUtil.parseJsonArrayWithGson(array.toString(), NewsModel.class);
                        data.addAll(newsModels);
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
                Log.e(TAG, "error:" + ex.toString());
                Message msg = new Message();
                msg.what = ERROR;
                handler.sendMessage(msg);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Message msg = new Message();
                msg.what = CANCELLED;
                handler.sendMessage(msg);
            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void refreshNews() {
        start = 0;
        getNewsData();
    }

    private void loadMoreNews() {
        start = start + 20;
        getNewsData();
    }
}
