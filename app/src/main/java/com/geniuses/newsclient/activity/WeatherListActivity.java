package com.geniuses.newsclient.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.geniuses.newsclient.R;
import com.geniuses.newsclient.adapter.WeatherListAdapter;
import com.geniuses.newsclient.datebase.DatabaseOpenHelper;
import com.geniuses.newsclient.entity.WeatherModel;
import com.geniuses.newsclient.util.DialogUtils;
import com.geniuses.newsclient.util.GlobalValue;
import com.zaaach.citypicker.CityPickerActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Sch on 2018/2/5.
 */

public class WeatherListActivity extends BasicActivity {
    private static final int REQUEST_CODE_PICK_CITY = 0;
    private ListView mListView;//城市列表
    private WeatherListAdapter adapter;
    private ArrayList<WeatherModel> mData;
    private Toolbar mToolbar;
    private FloatingActionButton fab;

    @Override
    public int getActivity() {
        return R.layout.activity_weather_list;
    }

    @Override
    protected void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setSubtitle(getTitle());
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherListActivity.this.finish();
            }
        });
        fab = findViewById(R.id.fab_weather_list);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(WeatherListActivity.this, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });
        mListView = findViewById(R.id.lv_weather);
    }

    @Override
    protected void main() {
        adapter = new WeatherListAdapter(getData(),this);
        mListView.setAdapter(adapter);
        for (WeatherModel weatherModel : mData) {
            getWeatherInfo(weatherModel);
        }
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context,WeatherInfoActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                DialogUtils.showDialog(context, "确定删除" + mData.get(position).getCityName() + "吗", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mData.remove(position);
                        adapter.updateData(mData);
                    }
                });
                return true;
            }
        });
    }

    private void getWeatherInfo(final WeatherModel weatherModel) {
        RequestParams params = new RequestParams(GlobalValue.WEATHER);
        params.addParameter("appkey", GlobalValue.APPKEY);
        params.addParameter("city", weatherModel.getCityName());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    JSONObject resultObj = object.getJSONObject("result");
                    JSONArray HeWeather5 = resultObj.getJSONArray("HeWeather5");
                    String weatherJson = HeWeather5.getString(0).toString();
                    weatherModel.setWeatherJson(weatherJson);
                    adapter.updateData(mData);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                WeatherModel weatherModel = new WeatherModel();
                weatherModel.setCityName(city);
                getWeatherInfo(weatherModel);
                mData.add(weatherModel);
                adapter.updateData(mData);
            }
        }
    }

    public ArrayList<WeatherModel> getData() {
        DbManager db = DatabaseOpenHelper.getInstance();
        try {
            mData = (ArrayList<WeatherModel>) db.findAll(WeatherModel.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (mData == null) {
            mData = new ArrayList<>();
        }
        return mData;
    }

    @Override
    protected void onPause() {
        super.onPause();
        DbManager db = DatabaseOpenHelper.getInstance();
        try {
            ArrayList<WeatherModel> oldData = (ArrayList<WeatherModel>) db.findAll(WeatherModel.class);
            if(oldData != null){
                db.delete(oldData);
            }
            db.save(mData);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
