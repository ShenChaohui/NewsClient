package com.geniuses.newsclient.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.geniuses.newsclient.entity.AppBean;
import com.geniuses.newsclient.fragment.NewsListFragment;
import com.geniuses.newsclient.R;
import com.geniuses.newsclient.adapter.NewsListFragmentAdapter;
import com.geniuses.newsclient.manager.GsonManager;
import com.geniuses.newsclient.util.CommonUtils;
import com.geniuses.newsclient.util.SharedPreferencesUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

import static com.pgyersdk.update.UpdateManagerListener.startDownloadTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    private String types[] = {"头条", "新闻", "财经", "体育", "娱乐", "军事", "教育", "科技", "NBA", "股票", "星座", "女性", "健康", "育儿"};
    private List<Fragment> mFragments;
    private List<String> mTitles;

    private TabLayout mTabLyout;
    private ViewPager mViewPager;
    private NewsListFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RxPermissions.getInstance(this)
                // 申请权限
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if(granted){
                        } else {
                        }
                    }
                });
        boolean isFirst = SharedPreferencesUtil.getBoolean(this, "isFirst", true);
        if (isFirst) {
            addShortcut();
        }
        isFirst = false;
        SharedPreferencesUtil.saveBoolean(this, "isFirst", isFirst);
        updateApp();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mTabLyout = findViewById(R.id.tl_main);
        mViewPager = findViewById(R.id.vp_main);
        mTitles = new ArrayList<>();
        mFragments = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            mTitles.add(types[i]);
            mFragments.add(NewsListFragment.newInstance(i));
        }
        adapter = new NewsListFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(adapter);
        mTabLyout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_weather) {
            startActivity(new Intent(MainActivity.this, WeatherListActivity.class));

        } else if (id == R.id.nav_chat) {
            startActivity(new Intent(MainActivity.this, ChatbotActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addShortcut() {
        Intent addShortcutIntent = new Intent(ACTION_ADD_SHORTCUT);

        // 不允许重复创建
        addShortcutIntent.putExtra("duplicate", false);

        // 名字
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, R.string.app_name);

        // 图标
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(MainActivity.this,
                        R.mipmap.ic_logo));

        // 设置关联程序
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN);
        launcherIntent.setClass(MainActivity.this, MainActivity.class);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        addShortcutIntent
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);
        // 发送广播
        sendBroadcast(addShortcutIntent);
        Toast.makeText(this, "快捷方式已创建", Toast.LENGTH_SHORT).show();
    }

    private void updateApp() {
        RequestParams params = new RequestParams("https://www.pgyer.com/apiv2/app/check");
        params.addParameter("_api_key", "35eed4ed7dcb639ea522268bfb34c0eb");
        params.addParameter("appKey", "f1ff7ac246ed6bebece66a0de952ac22");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                final AppBean appBean = GsonManager.getGson().fromJson(result,AppBean.class);
                if (Integer.valueOf(appBean.getData().getBuildVersionNo())== Integer.valueOf(CommonUtils.getLocalVersion(MainActivity.this))) {
                    Log.e("update","最新版本");
                } else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("更新")
                            .setMessage("有新的版本，请下载更新")
                            .setNegativeButton(
                                    "确定",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                            startDownloadTask(
                                                    MainActivity.this,
                                                    appBean.getData().getDownloadURL());
                                        }
                                    }).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("update",ex.toString());
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
