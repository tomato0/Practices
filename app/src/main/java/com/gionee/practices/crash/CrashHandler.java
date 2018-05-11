package com.gionee.practices.crash;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Author: wsq
 * Date: 18-5-11
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private static final Thread.UncaughtExceptionHandler sHandler = Thread.getDefaultUncaughtExceptionHandler();
    private static final ExecutorService THREAD_POLL = Executors.newSingleThreadExecutor();
    private CrashListener mCrashListener;
    private Future<?> mFuture;

    private CrashHandler() {}

    private static class CrashHandlerHolder {
        private static final CrashHandler INSTANCE = new CrashHandler();
    }

    public static CrashHandler getInstance() {
        return CrashHandlerHolder.INSTANCE;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        StringWriter writer = new StringWriter();
        PrintWriter print = new PrintWriter(writer);

        if (e != null) {
            e.printStackTrace(print);
        }
        String exceptionMessage = writer.toString();
        notifyCrashHappen(exceptionMessage);
        Log.d(TAG, "uncaughtException: " + exceptionMessage);
        print.close();
        sHandler.uncaughtException(t, e);
    }

    private void notifyCrashHappen(final String exceptionMessage) {
        if (null != mCrashListener) {
            mFuture = THREAD_POLL.submit(new Runnable() {
                @Override
                public void run() {
                    mCrashListener.onCrashHappen(exceptionMessage);
                }
            });
        }
    }

    public void addCrashListener(CrashListener listener) {
        mCrashListener = listener;
    }
}
