package com.gionee.practices.crash;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Author: wsq
 * Date: 18-5-11
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class CrashCollector implements CrashListener {
    private static final String TAG = "CrashCollector";
    private Context mContext;
    private String mFilePath;
    private File mLogFile;
    public CrashCollector(Context context, String filePath) {
        mContext = context;
        mFilePath = filePath;
        mLogFile = new File(filePath , "crash.log");
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.addCrashListener(this);
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }

    @Override
    public void onCrashHappen(String crashMessage) {
        Log.d(TAG, "onCrashHappen: ");
        CrashFileUtils.writeLogToFile(mLogFile, crashMessage);
        //TODO:上传到远程
    }
}
