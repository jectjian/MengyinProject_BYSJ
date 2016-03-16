package com.example.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jectjian on 2016/3/15.
 * Email : jectjian@126.com
 * 设置RecycleView的item之间的间距
 */
public class MySpaceItemWidth extends RecyclerView.ItemDecoration {
    private int space;

    public MySpaceItemWidth(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

//        if(parent.getChildPosition(view) != 0){//从第二个item开始
//            outRect.right = space;//设置每个item距离右边的距离
//        }

        outRect.right = space;//设置每个item距离右边的距离
    }

}
