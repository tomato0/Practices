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
 * faltmap内会再次创建一个Observable2，通过该Observable2将重新转化出的对象通过发射器发到一个内部维护的Observable，
 * 该Observable会接受所有来自Observable2的转化的对象，最终由Observable将所有的对象发射到subscribe上
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
}
