package com.izxjf.liao.recyclerviewitemcentredemo;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MainActivity extends Activity {

    private MyAdapter mMyAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mMyAdapter = new MyAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyAdapter);

        mMyAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position, View itemLayout) {
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int heightPixels = displayMetrics.heightPixels;
                int widthPixels = displayMetrics.widthPixels;
                int[] outLocation = new int[2];
                itemLayout.getLocationOnScreen(outLocation);
                int itemLayoutHeight = outLocation[1] - getStatusBarHeight(MainActivity.this);
                int centreHeight = (int) ((heightPixels / 2) - ((widthPixels * 0.5) / 2));

                RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
                if (layoutManager != null && layoutManager instanceof LinearLayoutManager) {
                    final LinearLayoutManager mLayoutManager = (LinearLayoutManager) layoutManager;
                    if (centreHeight != itemLayoutHeight) {
                        ValueAnimator valueAnimator = ValueAnimator.ofInt(itemLayoutHeight, centreHeight);
                        valueAnimator.setDuration(500);
                        valueAnimator.setInterpolator(new LinearInterpolator());
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int animatedValue = (int) animation.getAnimatedValue();
                                ((LinearLayoutManager) mLayoutManager).scrollToPositionWithOffset(position, animatedValue);

                            }
                        });
                        valueAnimator.start();
                    }
                }
            }
        });

    }

    //获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        //先获取资源id，根据id获取资源
        Resources resources = context.getResources();
        int statusBarHeightId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelOffset(statusBarHeightId);
    }
}
