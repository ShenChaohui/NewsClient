package com.geniuses.newsclient.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.adapter.WeatherInfoFragmentAdapter;
import com.geniuses.newsclient.datebase.DatabaseOpenHelper;
import com.geniuses.newsclient.entity.WeatherModel;
import com.geniuses.newsclient.fragment.WeatherInfoFragment;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;

/**
 * Created by Sch on 2018/2/7.
 */

public class WeatherInfoActivity extends BasicActivity {
    private ViewPager mViewPager;
    private ArrayList<WeatherModel> mData;
    private ArrayList<Fragment> mFragments;
    private WeatherInfoFragmentAdapter adapter;
    private int position;
    @Override
    public int getActivity() {
        return R.layout.activity_weather_info;
    }

    @Override
    protected void initView() {
        mViewPager = findViewById(R.id.vp_weather_info);
    }

    @Override
    protected void main() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        DbManager db = DatabaseOpenHelper.getInstance();
        try {
          mData = (ArrayList<WeatherModel>) db.findAll(WeatherModel.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        mFragments = new ArrayList<>();
        if(mData != null){
            for(int i = 0;i<mData.size();i++){
                mFragments.add(WeatherInfoFragment.newInstance(i));
            }
        }
        adapter = new WeatherInfoFragmentAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(position);
    }
    public ArrayList<WeatherModel> getWeatherModelList(){
        return mData;
    }
}
