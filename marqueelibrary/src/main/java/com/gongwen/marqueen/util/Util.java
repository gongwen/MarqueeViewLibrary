package com.gongwen.marqueen.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by GongWen on 17/9/11.
 * dp,px,sp之间的相互转换
 * <p>
 * 备注说明:
 * 在例子中有许多地方执行了X+0.5的操作
 * 1 因为我们传入的参数是均为float类型,返回值却都是int类型
 * 在float转换为int是都会将小数去掉.比如:
 * float 19.15f--->int 19
 * float 19.99f--->int 19
 * 2 density和scaledDensity均为float类型
 * <p>
 * 所以为了尽可能准确地取整数,我们执行了这样的操作.即:
 * float 19.15f--->float (19.15+0.5)f=float 19.65f--->int 19
 * float 19.99f--->float (19.99+0.5)f=float 20.49f--->int 20
 *
 *
 * TypedValue.applyDimension
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
}
