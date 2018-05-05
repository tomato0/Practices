package com.gionee.practices.animation;

import android.animation.TimeInterpolator;

/**
 * Author: wsq
 * Date: 18-5-5
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class DecelerateAccelerateInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        // 参数说明
        // input值值变化范围是0-1，且随着动画进度（0% - 100% ）均匀变化
        // 即动画开始时，input值 = 0；动画结束时input = 1
        // 而中间的值则是随着动画的进度（0% - 100%）在0到1之间均匀增加

        // 插值器的计算逻辑
        //sin函数实现先减速后加速
        if (input <= 0.5) {
            //正值
            return (float) Math.sin(Math.PI * input) / 2;
        }
        return (float) (2 - Math.sin(Math.PI * input)) / 2;
    }
}
