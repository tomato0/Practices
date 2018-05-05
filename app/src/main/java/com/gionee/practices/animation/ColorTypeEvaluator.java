package com.gionee.practices.animation;

import android.animation.TypeEvaluator;
import android.graphics.Color;

/**
 * Author: wsq
 * Date: 18-5-5
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class ColorTypeEvaluator implements TypeEvaluator<Integer> {

    /*
    * public static ObjectAnimator ofObject(Object target, String propertyName,
    *        TypeEvaluator evaluator, Object... values) {
    *
    *        根据 propertyName 匹配  target中的set方法，自动设置属性
    *        eg :
    *        target含有----->setColor()
    *        propertyName----->"color" 则能匹配到setColor()
    */
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startR = Color.red(startValue);
        int startG = Color.green(startValue);
        int startB = Color.blue(startValue);
        int endR = Color.red(endValue);
        int endG = Color.green(endValue);
        int endB = Color.blue(endValue);

        int changeR = (int) (startR + fraction * (endR - startR));
        int changeG = (int) (startG + fraction * (endG - startG));
        int changeB = (int) (startB + fraction * (endB - startB));
        return Color.rgb(changeR, changeG, changeB);
    }
}
