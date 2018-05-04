package com.gionee.practices.ad;

import android.content.Context;

import java.util.List;

/**
 * Author: wsq
 * Date: 18-5-4
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class AdPresenter {

    private AdMainView mView;

    public AdPresenter(AdMainView view) {
        mView = view;
    }

    public void loadAdData(Context context) {
        AdDataManager adManager = AdDataManager.getInstance();
        adManager.init(context);
        adManager.loadAdData(new IAdNetCallBack() {
            @Override
            public void onSuccess(List<AdEntity> adEntities) {
                mView.initAd(adEntities);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }
}
