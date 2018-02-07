package com.geniuses.newsclient.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Genius on 2017/7/4/0004.
 */

public class DialogUtils {

    /**
     * 确定的点击事件传入的null
     *
     * @param context
     * @param message
     */
    public static void showCommonAlertDialog(final Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("系统提示");
        builder.setMessage(message);
        builder.setPositiveButton("确定", null);
        builder.show();
    }

    /**
     * 含有确定的按钮的普通Dialog
     *
     * @param context  上下文
     * @param message  消息
     * @param listener 确定的监听事件
     */
    public static void showCommonAlertDialog(Context context, String message, DialogInterface.OnClickListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("系统提示");
        builder.setMessage(message);
        builder.setPositiveButton("确定", listener);
        builder.show();

    }

    /**
     * 两个按钮，取消已传入dismiss
     *
     * @param context
     * @param message
     * @param listener
     */
    public static void showDialog(Context context, String message, DialogInterface.OnClickListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("系统提示");
        builder.setMessage(message);
        builder.setPositiveButton("确定", listener);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.show();
        dialog.setCancelable(false);
    }

    public static void showDialogCancelFinishActivity(final Context context, String message, DialogInterface.OnClickListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("系统提示");
        builder.setMessage(message);
        builder.setPositiveButton("确定", listener);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((AppCompatActivity) context).finish();
            }
        });
        builder.show();
    }

    public static void showDialogCancelFinishActivity(String positiveText, String negativeTest, final Context context, String message, DialogInterface.OnClickListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("系统提示");
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, listener);
        builder.setNegativeButton(negativeTest, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((AppCompatActivity) context).finish();
            }
        });
        builder.show();
    }

    /**
     * 确定里面触发finish
     *
     * @param context
     * @param message
     */
    public static void showDialog(final Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("系统提示");
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((AppCompatActivity) context).finish();
            }
        });
        builder.show();
    }

}
