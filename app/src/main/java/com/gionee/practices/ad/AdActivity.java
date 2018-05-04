package com.gionee.practices.ad;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class AdActivity extends BaseActivity<AdMainView> {

    private AdPresenter mAdPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdPresenter = new AdPresenter(mView);
        mAdPresenter.loadAdData(this);
    }

    @Override
    public Class<AdMainView> getViewClass() {
        return AdMainView.class;
    }
}
