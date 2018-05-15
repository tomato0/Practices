package com.gionee.practices.rxjava;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.ImageView;

import com.gionee.practices.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class RxJavaActivity extends Activity {
    private static final String TAG = "RxJavaActivity";

    private DataManager mDataManager;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        mImageView = (ImageView) findViewById(R.id.img_view);

        mDataManager = DataManager.getInstance();
        startNetWork();
    }

    private void startNetWork() {
        Observable.create(new ObservableOnSubscribe<byte[]>() {
            @Override
            public void subscribe(ObservableEmitter<byte[]> emitter) throws Exception {
                String string = mDataManager.getDataJsonString();
                emitter.onNext(mDataManager.getDataByNet(mDataManager.parseImgUrl(string)));
                emitter.onComplete();
                Log.d(TAG, "call: " + string);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<byte[], Bitmap>() {
                    @Override
                    public Bitmap apply(byte[] bytes) throws Exception {
                        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    }
                })
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        mImageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");
        outState.putInt("save", 1);
        //在onStop之前调用，可用于保存数据
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //在onStart之后调用，用于获取onSaveInstanceState保存的数据
        Log.d(TAG, "onRestoreInstanceState: " + savedInstanceState.getInt("save"));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
