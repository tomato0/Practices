package com.gionee.practices.animation;

/**
 * Author: wsq
 * Date: 18-5-5
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class Point {
    private float mX;
    private float mY;

    public Point(float x, float y) {
        mX = x;
        mY = y;
    }

    public float getX() {
        return mX;
    }

    public void setX(float x) {
        mX = x;
    }

    public float getY() {
        return mY;
    }

    public void setY(float y) {
        mY = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "mX=" + mX +
                ", mY=" + mY +
                '}';
    }
}
