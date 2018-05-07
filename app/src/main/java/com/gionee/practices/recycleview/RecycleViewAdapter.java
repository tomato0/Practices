package com.gionee.practices.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gionee.practices.R;
import com.gionee.practices.ad.AdEntity;

import java.util.List;

/**
 * Author: wsq
 * Date: 18-5-7
 * Email: wangshaoqiang@gionee.com
 * function:
 * RecyclerView需要自己实现点击事件
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecyclerHolder> {

    private List<AdEntity> mAdEntities;
    private Context mContext;
    private OnRecyclerViewItemClickListener<AdEntity> mItemClickListener;

    public RecycleViewAdapter(Context context, List<AdEntity> adEntities) {
        mAdEntities = adEntities;
        mContext = context;
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<AdEntity> listener) {
        mItemClickListener = listener;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO:加载视图
        View view = LayoutInflater.from(mContext).inflate(R.layout.ad_pager_layout, parent, false);
        final RecyclerHolder holder = new RecyclerHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemClickListener(holder.getAdapterPosition(), mAdEntities.get(holder.getAdapterPosition()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, final int position) {
        //TODO:绑定数据
    }

    @Override
    public int getItemCount() {
        return mAdEntities.size();
    }

    private void notifyItemClickListener(int position, AdEntity entity) {
        if (null != mItemClickListener) {
            mItemClickListener.onItemClickListener(position, entity);
        }
    }

    static class RecyclerHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        public RecyclerHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.ad_img);
        }
    }
}
