package com.geniuses.newsclient.util;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.geniuses.newsclient.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by SpadesTen on 2017/4/6.
 */

public class CommonUtils {

    public static void showToast(Context context, String toast){
        Toast.makeText(context,toast, Toast.LENGTH_LONG).show();
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * 获取应用程序数据包根目录
     * @return
     */
    public static String getAppDirPath(Context context){
        String _SDPath = getSDPath();
        String dirPathString = 	_SDPath+ "/"
                + context.getString(R.string.app_name);
        File file = new File(dirPathString);
        if (!file.exists()) {
            file.mkdir();
        }
        return dirPathString;
    }
    /**
     * 获取sd卡地址
     * @return
     */
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取根目录
            return sdDir.toString();
        }else{
            return "-1";
        }
    }
    public static boolean isEmpty(Object object){
        if (object instanceof String){
            if ("".equals(object) || object == null)
                return true;
        }
        if (object instanceof List){
            if (((ArrayList) object).size() == 0 || object == null)
                return true;
        }
        if (object instanceof Map){
            if (((Map) object).size() == 0 || object == null){
                return true;
            }
        }
        return false;
    }
    public static void tagToDo(String string){

    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dpTopx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int pxTodp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

        private static long lastClickTime;
        public static boolean isFastDoubleClick() {
            long time = System.currentTimeMillis();
            long timeD = time - lastClickTime;
            if ( 0 < timeD && timeD < 800) {
                return true;
            }
            lastClickTime = time;
            return false;
        }
        public static String isExistDir(String saveDir) throws IOException {
            // 下载位置
            File downloadFile = new File(saveDir);
            if (!downloadFile.mkdirs()) {
                downloadFile.createNewFile();
            }
            String savePath = downloadFile.getAbsolutePath();
            return savePath;
        }
}
