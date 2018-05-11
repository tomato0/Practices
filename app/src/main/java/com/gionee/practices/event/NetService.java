package com.gionee.practices.event;

import com.gionee.practices.Common;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author: wsq
 * Date: 18-5-11
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public interface NetService {
    //http://route.showapi.com/1211-1?count=&showapi_appid=64493&showapi_sign=c0e9dc2595334f38b0f35b1e08ddc5a7
    @GET("1211-1?showapi_appid="+Common.APP_ID+"&showapi_sign="+Common.APP_KEY)
    Observable<String> getEffectiveWord(@Query("count") int count);
}
