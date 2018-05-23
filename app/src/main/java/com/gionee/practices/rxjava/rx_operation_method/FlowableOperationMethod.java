package com.gionee.practices.rxjava.rx_operation_method;

import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;

/**
 * Author: wsq
 * Date: 18-5-21
 * Email: wangshaoqiang@gionee.com
 * function:
 *
 * 关键参数配置
 *
 * BackpressureStrategy.ERROR         --------》发送和接收不均衡就会跑出异常
 * BackpressureStrategy.BUFFER        --------》无限制，与Observable效果一致
 * BackpressureStrategy.DROP        --------》丢弃存储不下的事件
 * BackpressureStrategy.LATEST        --------》下游跟不上只保留最近事件
 *
 */

public class FlowableOperationMethod {

    public void eg_flowable_Method() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
            }
        }, BackpressureStrategy.ERROR)
                .subscribe(new FlowableSubscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);//此方法用来指定FlowableSubscriber能够接收事件的数量
                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
