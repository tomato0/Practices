package com.gionee.practices.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Author: wsq
 * Date: 18-5-15
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class LabelLayout extends ViewGroup {
    public LabelLayout(Context context) {
        this(context, null);
    }

    public LabelLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //支持子view设置margin


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return super.generateLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }
}
