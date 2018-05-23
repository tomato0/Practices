package com.gionee.practices;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Author: wsq
 * Date: 18-5-18
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class ClickUtils {
    

    static class ClickInvokeHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }
}
