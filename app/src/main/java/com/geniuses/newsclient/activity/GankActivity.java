package com.geniuses.newsclient.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.fragment.GankListFragment;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;

/**
 * Created by Sch on 2018/3/14.
 */

public class GankActivity extends BasicActivity {
    private ViewPager vp;
    private SpaceTabLayout st;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        st.initialize(vp, getSupportFragmentManager(), fragments, savedInstanceState);
    }

    @Override
    public int getActivity() {
        return R.layout.activity_gank;
    }

    @Override
    protected void initView() {
        initTitle();
        vp = findViewById(R.id.vp_gank);
        st = findViewById(R.id.st_gank);
    }


    @Override
    protected void main() {
        fragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fragments.add(GankListFragment.newInstance(i));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        st.saveState(outState);
        super.onSaveInstanceState(outState);
    }
}
