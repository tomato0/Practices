package com.gionee.practices.ad;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gionee.practices.R;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: wsq
 * Date: 18-5-4
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class AdPagerAdapter extends PagerAdapter {
    private static final String TAG = "AdPagerAdapter";
    private List<AdEntity> mAds;
    private Context mContext;
    private LinkedList<View> mCacheView;

    public AdPagerAdapter(List<AdEntity> ads, Context context) {
        mAds = ads;
        mContext = context;
        mCacheView = new LinkedList<>();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewHolder holder;
        View view;
        Log.d(TAG, "instantiateItem cachesize: " + mAds.get(position % mAds.size()));
        if (mCacheView.size() <= 0) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.ad_pager_layout, null, false);
            holder.img = (ImageView) view.findViewById(R.id.ad_img);
            holder.title = (TextView) view.findViewById(R.id.ad_title);
            view.setTag(holder);
        } else {
            view = mCacheView.removeFirst();
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(mAds.get(position % mAds.size()).getTitle());
        Picasso.get().load(mAds.get(position % mAds.size()).getImgUrl())
                .config(Bitmap.Config.ARGB_8888)
                .into(holder.img);
        container.addView(view);
        Log.d(TAG, "instantiateItem: ");
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
        mCacheView.add(view);
        Log.d(TAG, "destroyItem: " + mCacheView.size());
    }

    private static class ViewHolder {
        private ImageView img;
        private TextView title;
    }
}
