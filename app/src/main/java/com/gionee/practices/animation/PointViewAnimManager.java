package com.gionee.practices.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;

/**
 * Author: wsq
 * Date: 18-5-5
 * Email: wangshaoqiang@gionee.com
 * function:
 *
 *
 * ValueAnimator:
 * 通过一段时间内不断的设置对象的属性刷新对象状态来实现动画效果
 * 属性的改变需要监听UpdateListener来手动设置
 *
 * ObjectAnimator：
 * 通过一段时间内不断的设置对象的属性刷新对象状态来实现动画效果
 * 不同的是ObjectAnimator通过propertyName（属性名称）来匹配对象的set方法，内部自动完成对象属性的设置
 *
 *
 */
    /*
    <-- 自动赋值给对象属性值的逻辑方法 ->>
    步骤1：初始化动画值
    private void setupValue(Object target, Keyframe kf) {
        if (mProperty != null) {
            kf.setValue(mProperty.get(target));
            // 初始化时，如果属性的初始值没有提供，则调用属性的get（）进行取值
        }
        kf.setValue(mGetter.invoke(target));
    }

    步骤2：更新动画值
   当动画下一帧来时（即动画更新的时候），setAnimatedValue（）都会被调用
    private void setAnimatedValue(Object target) {
        if (mProperty != null) {
            mProperty.set(target, getAnimatedValue());
            // 内部调用对象该属性的set（）方法，从而从而将新的属性值设置给对象属性
        }

    }
    */

public class PointViewAnimManager {
    private static final String TAG = "PointViewAnimManager";
    private PointView mPointView;
    private ValueAnimator mMoveAnimator;
    private ObjectAnimator mColorAnim;

    public PointViewAnimManager() {
    }

    public void bindView(PointView view) {
        this.mPointView = view;
    }

    public void startMoveAnim() throws Exception {
        if (mPointView == null) {
            throw new Exception("view is unbind");
        }
        PointTypeEvaluator evaluator = new PointTypeEvaluator();
        Point middlePoint = new Point(mPointView.getMeasuredWidth() - mPointView.getCurrentPoint().getX(),
                0);
        Point endPoint = new Point(mPointView.getMeasuredWidth() - mPointView.getCurrentPoint().getX() - middlePoint.getX(),
                mPointView.getMeasuredHeight() - mPointView.getCurrentPoint().getY() - middlePoint.getY());
        mMoveAnimator = ValueAnimator.ofObject(evaluator, mPointView.getCurrentPoint(), middlePoint, endPoint);
        mMoveAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mMoveAnimator.setInterpolator(new DecelerateAccelerateInterpolator());
        mMoveAnimator.setDuration(5000);
        mMoveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point point = (Point) animation.getAnimatedValue();
                mPointView.setLocation(point);
            }
        });
        mMoveAnimator.start();
    }

    public void startColorAnim() throws Exception {
        if (mPointView == null) {
            throw new Exception("view is unbind");
        }

        ColorTypeEvaluator colorTypeEvaluator = new ColorTypeEvaluator();
        mColorAnim = ObjectAnimator.ofObject(mPointView, "color", colorTypeEvaluator, Color.RED, Color.GREEN, Color.YELLOW);
        mColorAnim.setDuration(2000);
        mColorAnim.setRepeatCount(ValueAnimator.INFINITE);
        mColorAnim.start();
    }

    public void unBindView() {
        if (mMoveAnimator.isRunning()) {
            mMoveAnimator.cancel();
        }
        if (mColorAnim.isRunning()) {
            mColorAnim.cancel();
        }
        mPointView = null;
    }
}
