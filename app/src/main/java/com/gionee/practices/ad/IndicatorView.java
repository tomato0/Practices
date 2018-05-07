package com.gionee.practices.ad;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gionee.practices.R;

/**
 * Author: wsq
 * Date: 18-5-7
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class IndicatorView extends RadioGroup {
    private static final String TAG = "IndicatorView";

    private int mTabNum = 4;

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        Log.d(TAG, "initView: ");
        for (int i = 0; i < mTabNum; i++) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setButtonDrawable(R.drawable.drawable_indicator);
            radioButton.setWidth(60);
            radioButton.setEnabled(false);
//            radioButton.setChecked(true);
            this.addView(radioButton);
        }
    }

    public void setSelect(int index) {
        Log.d(TAG, "setSelect: " + getChildCount());
        Log.d(TAG, "setSelect: " + index % getChildCount());
//        Log.d(TAG, "setSelect: " + index % getChildCount());
        check(getChildAt(index % getChildCount()).getId());
    }
}
