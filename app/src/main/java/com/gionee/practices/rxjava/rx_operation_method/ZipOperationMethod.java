package com.gionee.practices.rxjava.rx_operation_method;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Author: wsq
 * Date: 18-5-10
 * Email: wangshaoqiang@gionee.com
 * function:
 * zip操作符作用，将多个Observable1 和 Observable2 发射的对象 object1, object2 ,合并为object3
 *
 * 最终返回数量由Observable数量少的个数决定。
 */

public class ZipOperationMethod {
    public void eg_zip_method() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onComplete();
            }
        });

        Observable<Integer> observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onComplete();
            }
        });

        /*
        * 将多个事件流进行处理，转化为某一个事件流输出
        */
        Observable.zip(observable1, observable2, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer1, Integer integer2) throws Exception {
                return integer1 * integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                //1
                //2
                //9
                //16
                //25
            }
        });
    }
}
