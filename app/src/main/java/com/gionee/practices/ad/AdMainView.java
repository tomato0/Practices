package com.gionee.practices.ad;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gionee.practices.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wsq
 * Date: 18-5-4
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class AdMainView implements IBaseView {
    private static final String TAG = "AdMainView";

    private View mView;
    private Context mContext;
    private ViewPager mViewPager;
    private List<AdEntity> mAds;
    private AdPagerAdapter mAdPagerAdapter;

    @Override
    public void initView(LayoutInflater inflater, ViewGroup viewGroup) {
        mView = inflater.inflate(R.layout.activity_ad, viewGroup);
        mViewPager = (ViewPager) mView.findViewById(R.id.ad_viewpager);
        mAds = new ArrayList<>();
        mContext = mView.getContext();
        Log.d(TAG, "initView: " +Thread.currentThread());

    }

    @Override
    public View getView() {
        return mView;
    }

    public void initAd(List<AdEntity> adEntities) {
        if (adEntities == null) {
            adEntities = new ArrayList<>();
        }
        Log.d(TAG, "initAd1: " +Thread.currentThread());
        mAds.clear();
        mAds.addAll(adEntities);
        mAdPagerAdapter = new AdPagerAdapter(mAds, mContext);
        mViewPager.setAdapter(mAdPagerAdapter);
        mViewPager.setCurrentItem(mAds.size()*10);
        Log.d(TAG, "initAd2: " +Thread.currentThread());
    }
}
