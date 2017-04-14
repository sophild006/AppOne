package com.cool.paper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/7.
 */

public class CustomScrollView extends LinearLayout {
    private TextView title, desc;
    private RelativeLayout scrollView;
    private int height, width;
    private int titleY;

    public CustomScrollView(Context context) {
        this(context, null);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        title = (TextView) findViewById(R.id.title);
        desc = (TextView) findViewById(R.id.desc);
        scrollView = (RelativeLayout) findViewById(R.id.scollView);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = scrollView.getMeasuredHeight();
        width = scrollView.getMeasuredWidth();
        Log.d("wwq", "height: " + height);
        titleY = title.getHeight();
        Log.d("wwq", "height: " + height + "  titleY:　" + titleY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int m = 0; m < count; m++) {
            View child = getChildAt(m);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    int startY;
    int parStartY;
    private int yy = 0;
    private boolean isover = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (yy == 0) {
            isover = false;
            yy = (int) title.getY();
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) event.getY();
                parStartY = (int) event.getRawY();
                Log.d("wwq", "down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("wwq", "move");
                int distance = (int) (event.getRawY() - parStartY);
                float proportion = 1 - (Math.abs(distance) * 1.0f / height);
                Log.d("wwq", "distance: " + distance + "  proportion:  " + proportion);//>0向下滑动，<0向上滑动
                if (proportion <= 0) {
                    proportion = 0;
                }
                if (proportion >= 1) {
                    proportion = 1;
                }


                if (distance < 0) {
                    title.setScaleX(proportion);
                    title.setScaleY(proportion);
                    title.setAlpha(proportion);
                }
                int parDistance = (int) (event.getRawY() - parStartY);
                if (Math.abs(parDistance) > height / 3) {
                    isover = true;
                } else {
                    setTranslationY(parDistance);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isover) {
                    setTranslationY(-height / 2);
                } else {
                    setTranslationY(0);
                }
                Log.d("wwq", "up");
                title.setScaleX(1);
                title.setScaleY(1);
                title.setAlpha(1);
                title.setTranslationY(0);
                yy = 0;
                break;
        }
        return true;
    }

    private void init() {


    }

}
