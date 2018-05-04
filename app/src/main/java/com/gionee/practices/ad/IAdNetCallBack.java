package com.gionee.practices.ad;

import java.util.List;

/**
 * Author: wsq
 * Date: 18-5-4
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public interface IAdNetCallBack {
    void onSuccess(List<AdEntity> adEntities);
    void onError(String errorMessage);
}
