package com.gionee.practices.ad;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gionee.practices.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wsq
 * Date: 18-5-4
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class AdDataManager {
    private static final String TAG = "AdDataManager";
    private boolean isFirst = true;
    private Context mContext;

    private AdDataManager() {

    }
    public static AdDataManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void init(Context context) {
        if (isFirst) {
            initFirst(context);
        }
    }

    private void initFirst(Context context) {
        mContext = context.getApplicationContext();
        isFirst = false;
    }

    private static class SingletonHolder {
        private static final AdDataManager INSTANCE = new AdDataManager();
    }

    public void loadAdData(final IAdNetCallBack callBack) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callBack.onSuccess(parseAdData(response));
            }

        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError(error.getMessage());
                Log.d(TAG, "onErrorResponse: " + error);
            }
        };
        JsonObjectRequest request = new JsonObjectRequest(Common.AD_URL, null, listener, errorListener);
        requestQueue.add(request);
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
