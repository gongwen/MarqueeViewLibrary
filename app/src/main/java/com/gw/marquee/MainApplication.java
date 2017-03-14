package com.gw.marquee;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by GongWen on 17/3/14.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
