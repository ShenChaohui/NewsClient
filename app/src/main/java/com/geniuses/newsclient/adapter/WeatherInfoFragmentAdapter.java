package com.geniuses.newsclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Sch on 2018/1/25.
 */

public class WeatherInfoFragmentAdapter extends FragmentStatePagerAdapter {
    public List<Fragment> list;

    public WeatherInfoFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public WeatherInfoFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
