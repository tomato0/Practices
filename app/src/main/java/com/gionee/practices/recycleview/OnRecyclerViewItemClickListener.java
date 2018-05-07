package com.gionee.practices.recycleview;

/**
 * Author: wsq
 * Date: 18-5-7
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public interface OnRecyclerViewItemClickListener<T> {
    void onItemClickListener(int position, T itemData);
}
