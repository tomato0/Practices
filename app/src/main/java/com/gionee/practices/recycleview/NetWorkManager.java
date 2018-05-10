package com.gionee.practices.recycleview;

import android.util.Log;

import com.gionee.practices.ad.AdEntity;
import com.gionee.practices.Common;
import com.gionee.practices.ad.IAdNetCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author: wsq
 * Date: 18-5-8
 * Email: wangshaoqiang@gionee.com
 * function:
 */

enum NetWorkManager {
    INSTANCE;
    private static final String TAG = "NetWorkManager";

    public void getDataByOKHttp() {
        //同步，需要在线程中执行
        String url = Common.AD_URL;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(url).get().build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String s = request.body().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void postNetWorkByOkHttp(final IAdNetCallBack callBack) {
        //异步
        String url = Common.AD_URL;
        //通过builder构建client，new OkHttpClient()直接创建的client为默认配置
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        FormBody.Builder bodyBuilder = new FormBody.Builder().add("", "").add("", "");
        FormBody body = bodyBuilder.build();
        Request request = builder
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                try {
                    List<AdEntity> adEntities = parseAdData(new JSONObject(str));
                    callBack.onSuccess(adEntities);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<AdEntity> parseAdData(JSONObject jsonObject) {
        Log.d(TAG, "parseAdData: ");
        List<AdEntity> ads = new ArrayList<>();
        try {
            JSONArray trailers = jsonObject.getJSONArray("trailers");
            for (int i = 0; i < trailers.length(); i++) {
                AdEntity adEntity = new AdEntity();
                JSONObject jsonObj = trailers.getJSONObject(i);
                adEntity.setTitle(jsonObj.getString("movieName"));
                adEntity.setContent(jsonObj.getString("summary"));
                adEntity.setImgUrl(jsonObj.getString("coverImg"));
                adEntity.setContentUrl(jsonObj.getString("hightUrl"));
                ads.add(adEntity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "parseAdData: ");
        return ads;
    }
}
