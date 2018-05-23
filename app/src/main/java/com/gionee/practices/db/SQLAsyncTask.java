package com.gionee.practices.db;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: wsq
 * Date: 18-5-16
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public abstract class SQLAsyncTask<T> {

    private ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    private final static Handler mHandler = new Handler(Looper.getMainLooper());

    public final void execute() {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                postResult(doInBackground());
            }
        });
    }

    private void postResult(final T result) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onPostExecute(result);
            }
        });
    }

    public abstract T doInBackground();

    public abstract void onPostExecute(T result);
}
