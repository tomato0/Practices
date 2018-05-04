package com.gionee.practices.ad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: wsq
 * Date: 18-5-4
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public interface IBaseView {
    void initView(LayoutInflater inflater, ViewGroup viewGroup);
    View getView();
}
