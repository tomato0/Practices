package com.gionee.practices.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.gionee.practices.R;

/**
 * Author: wsq
 * Date: 18-5-12
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class RefreshLayout extends ViewGroup {
    private static final String TAG = "RefreshLayout";
    private final int mHeadHeight = 500;

    private View mHeadRefreshView;
    private Scroller mScroller;
    private int mInitScrollY;
    private float mLastY;
    private float mOffsetY;

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mScroller = new Scroller(context);

        mHeadRefreshView = LayoutInflater.from(context).inflate(R.layout.refresh_head_layout, this, false);
        mHeadRefreshView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, mHeadHeight));
        addView(mHeadRefreshView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            height += child.getMeasuredHeight();
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = getPaddingLeft();
        int top = getPaddingTop();
        for (int i = 0; i < getChildCount(); i++) {
            Log.d(TAG, "onLayout: " + top);
            View child = getChildAt(i);
            child.layout(left, top, child.getMeasuredWidth(), child.getMeasuredHeight() + top);
            top += child.getMeasuredHeight();
        }

        mInitScrollY = mHeadRefreshView.getMeasuredHeight() + getPaddingTop();
        scrollTo(0, mInitScrollY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        Log.d(TAG, "onTouchEvent: " + action);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float currentY = event.getY();
                mOffsetY = currentY - mLastY;
                scrollBy(0, -(int) mOffsetY);
                mLastY = currentY;
                Log.d(TAG, "onTouchEvent: "+ currentY+", " + mOffsetY);
                break;
            case MotionEvent.ACTION_UP:
//                requestDisallowInterceptTouchEvent(true);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent: "+ ev);
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onInterceptTouchEvent: "+ isTop());
                Log.d(TAG, "onInterceptTouchEvent b: " + getChildAt(1).getY());
                return false;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return false;
    }

    private boolean isTop() {

        Log.d(TAG, "isTop: "+getScrollY());
        return false;
    }
}
