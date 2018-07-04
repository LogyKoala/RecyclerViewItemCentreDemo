package com.izxjf.liao.recyclerviewitemcentredemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * =======================================
 * 创建日期:2018/7/3 on 09:27
 * 作   者:张辽
 * 邮   箱:Zl13484407109@sina.com
 * 描   述:
 * =======================================
 */

public class SmallVideoRL extends RelativeLayout {
    public SmallVideoRL(Context context) {
        this(context,null);
    }

    public SmallVideoRL(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SmallVideoRL(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int width =displayMetrics.widthPixels;

        int childCount = getChildCount();
        for(int i =0;i<childCount;i++){
            View childAt = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            layoutParams.width =width;
            layoutParams.height = (int) (width*0.5);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
