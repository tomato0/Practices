package com.gionee.practices.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: wsq
 * Date: 18-5-5
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class PointView extends View {
    private static final float DEFAULT_RADIUS = 100;
    private Point currentPoint;
    private float radius = DEFAULT_RADIUS;
    private Paint mPaint;

    public PointView(Context context) {
        this(context, null, 0);
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        currentPoint = new Point(radius, radius);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(currentPoint.getX(), currentPoint.getY(), radius, mPaint);
        super.onDraw(canvas);
    }

    public void setLocation(Point point) {
        currentPoint = point;
        invalidate();
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void setColor(int color) {
        mPaint.setColor(color);
        invalidate();
    }
}
