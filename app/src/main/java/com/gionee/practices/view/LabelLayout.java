package com.gionee.practices.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: wsq
 * Date: 18-5-15
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class LabelLayout extends ViewGroup {
    private static final String TAG = "LabelLayout";
    public LabelLayout(Context context) {
        this(context, null);
    }

    public LabelLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        int height = 0;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            Log.d(TAG, "w1: " + width + childView.getMeasuredWidth() + "size: " + size);
            if (width + childView.getMeasuredWidth() > size) {
                height += childView.getMeasuredHeight() + 10;
                width = childView.getMeasuredWidth();
            } else {
                width += childView.getMeasuredWidth() + 10;
//                height = Math.max(height, lastH + childView.getMeasuredHeight());
            }
            Log.d(TAG, "onMeasure: w: " + width + "h: " + height);
        }
        setMeasuredDimension(size, height);
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
        int count = getChildCount();
        int left = 0;
        int top = 0;
        int right = getMeasuredWidth();
        int bottom = 0;
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            if (right + childView.getMeasuredWidth() > getMeasuredWidth()) {
                Log.d(TAG, "1: " + right + childView.getMeasuredWidth());
                Log.d(TAG, "2: " + getMeasuredWidth());
                left = 0;
                top = bottom + 10;
                right = childView.getMeasuredWidth();
                bottom = top + childView.getMeasuredHeight();
            } else {
                left = right + 10;
                right = left + childView.getMeasuredWidth();
                bottom = top + childView.getMeasuredHeight();
            }
            Log.d(TAG, "left: " + left + "---top: " + top+"-----right: " +right + "----bottom: " + bottom);
            childView.layout(left, top, right, bottom);
        }
    }
}
