package com.gionee.practices.animation;

import android.animation.TypeEvaluator;

/**
 * Author: wsq
 * Date: 18-5-5
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class DoubleTypeEvaluator implements TypeEvaluator<Double> {
    @Override
    public Double evaluate(float fraction, Double startValue, Double endValue) {
        // 初始值 过渡 到结束值 的算法是：
        // 1. 用结束值减去初始值，算出它们之间的差值
        // 2. 用上述差值乘以fraction系数
        // 3. 再加上初始值，就得到当前动画的值
        // fraction:表示当前动画的完成度
        double startDouble = startValue;
        return startDouble + fraction * (endValue - startDouble);
    }
}
