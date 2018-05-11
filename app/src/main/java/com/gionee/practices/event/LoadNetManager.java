package com.gionee.practices.event;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Author: wsq
 * Date: 18-5-11
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class LoadNetManager {
    private static final String TAG = "LoadNetManager";
    private static LoadNetManager instance = null;
    private final NetService mNetService;

    public static LoadNetManager getInstance() {
        if (null == instance) {
            synchronized (LoadNetManager.class) {
                if (null == instance) {
                    instance = new LoadNetManager();
                }
            }
        }
        return instance;
    }

    private LoadNetManager() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl("http://route.showapi.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        mNetService = retrofit.create(NetService.class);
    }

    public void loadNetWordData(Observer<Word> observer) {
        Observable<String> mNetServiceEffectiveWord = mNetService.getEffectiveWord(10);
        mNetServiceEffectiveWord
                .flatMap(new Function<String, ObservableSource<Word>>() {
                    @Override
                    public ObservableSource<Word> apply(String s) throws Exception {
                        Log.d(TAG, "apply: " + s);
                        return Observable.fromIterable(parseWords(s));
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private List<Word> parseWords(String s) {
        List<Word> words = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject body = jsonObject.getJSONObject("showapi_res_body");
            JSONArray data = body.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject object = data.getJSONObject(i);
                Word word = new Word();
                word.setChinese(object.getString("chinese"));
                word.setEnglish(object.getString("english"));
                words.add(word);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return words;
    }
}
