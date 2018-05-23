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
 * 将一个事件对象转换为另一个对象,上游发送的每一个事件通过函数，使得每一个事件按照指定的函数进行变化
 *
 * 再众多操作符中存在一个问题，
 * 当事件发送和订阅处于同一个线程时（即同步），Observable每发送一个事件，需要subscribe处理完该事件后，Observable才能继续发送事件；
 * 当事件发送和订阅处于不同的线程时（即异步），因为涉及到线程的通信，Observable不需要等待Subscribe将上一个事件处理完，所以当Observable
 * 不停的发送事件，而subscribe来不及马上处理，众多事件堆积在内存中，容易造成OOM
 * 解决的办法：
 *      自行优化：1.从数量上控制，减少发送的事件；2.速度上处理，减缓发送事件的速度
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
                /*
                * 事件流转换----事件A----》转换事件B
                * 由ObservableEmitter（被观察者发射器发射出来的对象）
                * 经过map操作符 由object1----》变为object2
                * subject(订阅者)接收到的数据为转化后的object2
                *
                */
            }
        });

    }
}
