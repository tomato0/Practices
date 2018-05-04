package com.gionee.practices.ad;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Author: wsq
 * Date: 18-5-4
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public abstract class BaseActivity<V extends IBaseView> extends Activity {

    protected V mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            mView = getViewClass().newInstance();
            mView.initView(getLayoutInflater(), null);
            View view = mView.getView();
            setContentView(view);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
    }

    public abstract Class<V> getViewClass();
}
