package com.gionee.practices.net;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author: wsq
 * Date: 18-5-21
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class OkHttpTest {

    public void get_method(String url) {
        OkHttpClient httpClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder
                .method("GET", null)
                .url(url)
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            String result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }

}
