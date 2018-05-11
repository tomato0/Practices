package com.gionee.practices;

import android.app.Application;

import com.gionee.practices.crash.CrashCollector;

/**
 * Author: wsq
 * Date: 18-5-11
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class PracticeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new CrashCollector(this, getFilesDir().getAbsolutePath());
    }
}
