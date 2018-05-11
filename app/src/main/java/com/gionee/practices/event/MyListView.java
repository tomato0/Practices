package com.gionee.practices.event;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Author: wsq
 * Date: 18-5-11
 * Email: wangshaoqiang@gionee.com
 * function:ScrollView嵌套listView，导致listView无法显示，需要重新计算listView的高度
 */

public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureSpecH = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, measureSpecH);
    }
}
