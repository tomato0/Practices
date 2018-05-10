package com.gionee.practices.rxjava;


import com.gionee.practices.Common;
import com.show.api.ShowApiRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Author: wsq
 * Date: 18-5-10
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class DataManager {
    private static DataManager mInstance = null;
    private DataManager() {}
    public static DataManager getInstance() {
        if (null == mInstance) {
            synchronized (DataManager.class) {
                if (null == mInstance) {
                    mInstance = new DataManager();
                }
            }
        }
        return mInstance;
    }

    public String getDataJsonString() {
        return new ShowApiRequest(Common.IMG_URL, Common.APP_ID, Common.APP_KEY).get();
    }

    public String parseImgUrl(String str) {
        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONObject body = jsonObject.getJSONObject("showapi_res_body");
            JSONObject data = body.getJSONObject("data");
            return data.getString("img_1920");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getDataByNet(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int length;
                byte[] bytes = new byte[102400];
                while((length = is.read(bytes)) != -1) {
                    bos.write(bytes, 0, length);
                }
                return bos.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "".getBytes();
    }
}
