package com.gionee.practices.recycleview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Author: wsq
 * Date: 18-5-7
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class ItemDividerDecoration extends RecyclerView.ItemDecoration {
    private int mOrientation = LinearLayout.HORIZONTAL;

    public ItemDividerDecoration(int orientation) {
        if (orientation != LinearLayout.HORIZONTAL || orientation != LinearLayout.VERTICAL) {
            throw new IllegalArgumentException("pleasure set right orientation");
        }
        mOrientation = orientation;
        //TODO:初始化操作
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //TODO:为每个childView decoration，绘制图层在ItemView以下
        switch (mOrientation) {
            case LinearLayout.VERTICAL:
                //TODO:
                break;
            case LinearLayout.HORIZONTAL:
                //TODO:
                break;
        }
    }

    /*作用：同样是绘制内容，但与onDraw（）的区别是：绘制在图层的最上层*/
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    /*
    * 设置内嵌偏移长度
    * 内嵌偏移长度 是指：该矩形（outRect）与 ItemView的间隔
    *
    * ______________________
    * |   ______________   |
    * |   |            |   |
    * |   |            |   |
    * |   |  IteView   |   |
    * |   |            |   |
    * |   ——————————————   |
    * |______outRect_______|
    */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

}
