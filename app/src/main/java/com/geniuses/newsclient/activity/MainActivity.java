package com.geniuses.newsclient.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.adapter.NewsListFragmentAdapter;
import com.geniuses.newsclient.entity.AppBean;
import com.geniuses.newsclient.fragment.NewsListFragment;
import com.geniuses.newsclient.util.CommonUtils;
import com.geniuses.newsclient.util.GsonUtil;
import com.geniuses.newsclient.util.SharedPreferencesUtil;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    private String types[] = {"头条", "新闻", "财经", "体育", "娱乐", "军事", "教育", "科技", "NBA", "股票", "星座", "女性", "健康", "育儿"};
    private List<Fragment> mFragments;
    private List<String> mTitles;

    private TabLayout mTabLyout;
    private ViewPager mViewPager;
    private NewsListFragmentAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndPermission.with(this).runtime().permission(Permission.Group.STORAGE).start();
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_weather) {
            startActivity(new Intent(MainActivity.this, WeatherListActivity.class));

        } else if (id == R.id.nav_gank) {
            startActivity(new Intent(MainActivity.this, GankActivity.class));
        }
//        else if (id == R.id.nav_chat) {
//            startActivity(new Intent(MainActivity.this, ChatbotActivity.class));
//        }


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
                final AppBean appBean = GsonUtil.parseJsonWithGson(result, AppBean.class);
                final String updatePath = CommonUtils.getAppDirPath(MainActivity.this) + "/UpdateApk/掌上新闻" + appBean.getData().getBuildVersion() + ".apk";
                if (Integer.valueOf(appBean.getData().getBuildVersionNo()) == Integer.valueOf(CommonUtils.getLocalVersion(MainActivity.this))) {
                    Log.e("update", "最新版本");
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
                                            DownloadUpdateApp(appBean.getData().getDownloadURL(), updatePath);
                                        }
                                    }).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("update", ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    private void DownloadUpdateApp(String url, final String updatePath) {
        progressDialog = new ProgressDialog(this);
        FileDownloader.setup(this);
        FileDownloader.getImpl().create(url)
                .setPath(updatePath)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog.setMessage("亲，努力下载中。。。");
                        progressDialog.setCancelable(false);//设置进度条是否可以按退回键取消
                        progressDialog.setCanceledOnTouchOutside(false);//设置进度条是否可以按其他区域取消
                        progressDialog.show();
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        progressDialog.setMax(totalBytes / 1024);
                        progressDialog.setProgress(soFarBytes / 1024);
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        progressDialog.dismiss();
                        File file = new File(updatePath);
                        AndPermission.with(MainActivity.this).install().file(file).start();
//                        Uri apkUri;
//                        if (Build.VERSION.SDK_INT >= 25) {
//                            apkUri =
//                                    FileProvider.getUriForFile(MainActivity.this, "com.geniuses.newsclient.provider", file);
//                        } else {
//                            apkUri = Uri.parse("file://" + updatePath);
//                        }
//
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        // 由于没有在Activity环境下启动Activity,设置下面的标签
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        //添加这一句表示对目标应用临时授权该Uri所代表的文件
//                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
//                        MainActivity.this.startActivity(intent);
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "下载失败。", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {

                    }
                })
                .start();
    }
}
