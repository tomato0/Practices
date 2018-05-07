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

public class AdMainView implements IBaseView, ViewPager.OnPageChangeListener {
    private static final String TAG = "AdMainView";

    private View mView;
    private Context mContext;
    private ViewPager mViewPager;
    private List<AdEntity> mAds;
    private AdPagerAdapter mAdPagerAdapter;
    private IndicatorView mIndicatorView;

    @Override
    public void initView(LayoutInflater inflater, ViewGroup viewGroup) {
        mView = inflater.inflate(R.layout.activity_ad, viewGroup);
        mViewPager = (ViewPager) mView.findViewById(R.id.ad_viewpager);
        mIndicatorView = (IndicatorView) mView.findViewById(R.id.indicator_view);
        mAds = new ArrayList<>();
        mContext = mView.getContext();
        Log.d(TAG, "initView: " +Thread.currentThread());
        mViewPager.addOnPageChangeListener(this);

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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "onPageSelected: " + position);
        mIndicatorView.setSelect(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
