package com.gionee.practices.animation;

import android.animation.TypeEvaluator;

/**
 * Author: wsq
 * Date: 18-5-5
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class PointTypeEvaluator implements TypeEvaluator<Point> {

    private Point mPoint = new Point(0, 0);
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        float startX = startValue.getX();
        float startY = startValue.getY();
        mPoint.setX(startX + fraction * (endValue.getX()));
        mPoint.setY(startY + fraction * (endValue.getY()));

        return mPoint;
    }
}
