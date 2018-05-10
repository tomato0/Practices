package com.gionee.practices.rxjava.rx_operation_method;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author: wsq
 * Date: 18-5-10
 * Email: wangshaoqiang@gionee.com
 * function:
 * map操作符
 * 将一个事件对象转换为另一个对象
 */

public class MapOperationMethod {

    public void eg_map_method() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return String.valueOf(integer);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                /*由ObservableEmitter（被观察者发射器发射出来的对象）
                * 经过map操作符 由object1----》变为object2
                * subject(订阅者)接收到的数据为转化后的object2
                * */
            }
        });
    }
}
