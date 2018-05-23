package com.gionee.practices.rxjava.rx_operation_method;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author: wsq
 * Date: 18-5-10
 * Email: wangshaoqiang@gionee.com
 * function:
 * 某个Observable1将事件对象object1发送给------》flatmap
 * faltmap每次再次创建一个ObservableN，通过该ObservableN将重新转化出的对象通过发射器发到一个内部维护的Observable，
 * 该Observable会接受所有来自ObservableN的转化的对象，最终由Observable将所有的对象发射到subscribe上
 *
 * 将一个发送事件的上游Observable变换为多个发送事件的Observables，然后将他们的发射的事件放到一个单一的Observable（该
 * Observable由RxJava自己内部维护）
 * flatmap无法保证事件的顺序，如果事件需要按照严格的顺序执行，可以使用concatmap
 */

public class FlatMapOperationMethod {
    public void eg_faltmap_method() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
                //发送到flatmap
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> newData = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    newData.add("object : " + String.valueOf(integer) + " changed : " + String.valueOf(i));
                }
                return Observable.fromIterable(newData);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //object：1 cchanged1~5
                //object：2 cchanged1~5
                //object：3 cchanged1~5
                //object：4 cchanged1~5
            }
        });
    }

    public void eg_concatmap_method() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(3);
                emitter.onNext(5);
                emitter.onNext(7);
                emitter.onNext(9);
                emitter.onComplete();
            }
        }).concatMap(new Function<Integer, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(Integer integer) throws Exception {
                List<Integer> newData = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    newData.add(integer * i);
                }
                return Observable.fromIterable(newData);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        });
    }
}
