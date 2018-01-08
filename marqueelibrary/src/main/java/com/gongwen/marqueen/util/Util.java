package com.gongwen.marqueen.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import java.util.List;

/**
 * Created by GongWen on 17/9/11.
 */
public class Util {

    //将pixel转换成dip(dp)
    public static int px2Dp(Context context, float px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }

    //将dip(dp)转换成pixel
    public static int dp2Px(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    //将pixel转换成sp
    public static int px2Sp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scaledDensity + 0.5f);
    }

    //将sp转换成pixel
    public static int sp2Px(Context context, float sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scaledDensity + 0.5f);
    }

    // 屏幕宽度（像素）
    public static int getWindowWidth(Activity context) {
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    // 屏幕高度（像素）
    public static int getWindowHeight(Activity context) {
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }

    public static boolean isEmpty(List mList) {
        return mList == null || mList.size() == 0;
    }
}
