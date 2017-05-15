package com.wx.myproject.apptest.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

import com.wx.myproject.util.DensityUtil;

/**
 * Created by wwq on 2017/5/15.
 */

public class MyCustomFloatActionbutton extends FloatingActionButton {
    private ObjectAnimator hideAnimator;
    private ObjectAnimator showAnimator;
    private int mDiffer;
    private boolean isHide = false;

    public MyCustomFloatActionbutton(Context context) {
        this(context, null);
    }

    public MyCustomFloatActionbutton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCustomFloatActionbutton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDiffer = DensityUtil.dip2px(getContext(), 120);
        hideAnimator = ObjectAnimator.ofFloat(this, "translationY", 0, mDiffer).setDuration(300);
        showAnimator = ObjectAnimator.ofFloat(this, "translationY", mDiffer, 0).setDuration(300);

    }

    public void hideY() {
        isHide = true;
        hideAnimator.start();
    }

    public void showY() {
        isHide = false;
        showAnimator.start();
    }

    public boolean isHide() {
        return isHide;
    }

    public void setIsHide(boolean isHide){
        this.isHide=isHide;
    }
}
