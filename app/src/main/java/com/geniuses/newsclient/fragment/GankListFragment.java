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
import android.widget.Toast;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.adapter.GankListAdapter;
import com.geniuses.newsclient.entity.GankModel;
import com.geniuses.newsclient.manager.GsonManager;
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

/**
 * Created by Sch on 2018/3/14.
 */

public class GankListFragment extends Fragment {
    private final int SUCCESS = 0X01;
    private final int ERROR = 0X02;
    private final int CANCELLED = 0x03;
    private String type;
    private int count;//请求第几页
    private String[] types = {"Android", "iOS", "前端"};
    private ListView mListView;
    private LoadingView mLoadingView;
    private RefreshLayout refreshLayout;
    private ArrayList<GankModel> data;
    private GankListAdapter adapter;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    adapter.notifyDataSetChanged(data);
                    mLoadingView.setVisibility(View.GONE);
                    refreshLayout.finishLoadmore();
                    refreshLayout.finishRefresh();
                    Log.e(type,"成功");
                    break;
                case ERROR:
                    mLoadingView.setVisibility(View.GONE);
                    Log.e(type,"失败");
                    break;
                case CANCELLED:
                    mLoadingView.setVisibility(View.GONE);
                    break;
            }
        }
    };
    public static GankListFragment newInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        GankListFragment fragment = new GankListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ganklist, null);
        type = types[getArguments().getInt("index")];
        mLoadingView = view.findViewById(R.id.loadView);
        mListView = view.findViewById(R.id.lv_ganklist);
        data = new ArrayList<>();
        adapter = new GankListAdapter(getActivity(),data);
        mListView.setAdapter(adapter);
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
        getGank();
        return view;
    }
    private void refreshNews() {
        count = 1;
        getGank();
    }

    private void loadMoreNews() {
        count++;
        getGank();
    }
    public void getGank() {
        String url = "http://gank.io/api/data/"+type+"/10/"+ count;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray results = object.getJSONArray("results");
                    for(int i = 0;i<results.length();i++){
                        GankModel gankModel = GsonManager.getGson().fromJson(results.get(i).toString(),GankModel.class);
                        data.add(gankModel);
                    }
                    Message msg = new Message();
                    msg.what = SUCCESS;
                    handler.sendMessage(msg);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
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
}
