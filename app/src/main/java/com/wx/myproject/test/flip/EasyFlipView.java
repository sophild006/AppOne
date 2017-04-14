package com.wx.myproject.test.flip;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.wx.myproject.R;
import com.wx.myproject.anim.AnimationUtil;

/**
 * Created by Administrator on 2017/4/14.
 */

public class EasyFlipView extends FrameLayout {
    //翻转默认时长
    private static final int DEFAULT_FLIP_DURATION = 400;

    //翻转枚举正面还是背面
    private enum TYPE {
        FRONT, BACKGROUND
    }

    private Context context;
    private TYPE mType = TYPE.FRONT;


    private int duration;
    private boolean filpTouch;
    private boolean enabled;


    private View mFontView;
    private View mBackView;

    private AnimatorSet outSet;
    private AnimatorSet intSet;

    public EasyFlipView(Context context) {
        this(context, null);
    }

    public EasyFlipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyFlipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        duration = DEFAULT_FLIP_DURATION;
        filpTouch = true;
        enabled = true;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EasyFlipView);
            try {
                duration = a.getInt(R.styleable.EasyFlipView_duration, DEFAULT_FLIP_DURATION);
                filpTouch = a.getBoolean(R.styleable.EasyFlipView_onTouch, true);
                enabled = a.getBoolean(R.styleable.EasyFlipView_enabled, true);
            } catch (Exception e) {
            } finally {
                a.recycle();
            }

        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 2) {
            throw new IllegalStateException("EasyFlipView must be only 2 child view");
        }
        findView();
        loadAnims();
    }

    private void loadAnims() {
        outSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.animation_flip_out);
        intSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.animation_flip_in);

    }

    private void findView() {

        mFontView = getChildAt(1);
        mBackView = getChildAt(0);
        if (mType == TYPE.FRONT) {
            mBackView.setVisibility(GONE);
            mFontView.setVisibility(VISIBLE);
        }
    }

    private float x1;
    private float y1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (filpTouch && enabled) {
            this.getParent().requestDisallowInterceptTouchEvent(true);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x1 = event.getX();
                    y1 = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    if (mType == TYPE.FRONT) {
                        backAnim();
                        mType = TYPE.BACKGROUND;
                    }else{
                        frontAnim();
                        mType = TYPE.FRONT;
                    }

                    break;
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void backAnim() {
        mFontView.setVisibility(VISIBLE);
        mBackView.setVisibility(VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mFontView, "rotationY", 0, 180).setDuration(duration);
        objectAnimator.start();
        Log.d("wwq", "flipView");
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mFontView, "alpha", 1, 0).setDuration(duration);
        alpha.start();
        alpha.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mFontView.setVisibility(GONE);
                mBackView.setVisibility(VISIBLE);
            }
        });

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(mFontView, "rotationY", -180, 0).setDuration(duration);
        objectAnimator1.start();
        Log.d("wwq", "flipView");
        ObjectAnimator alpha2 = ObjectAnimator.ofFloat(mFontView, "alpha", 1, 0).setDuration(duration);
        alpha2.start();
        ObjectAnimator alpha1 = ObjectAnimator.ofFloat(mFontView, "alpha", 0, 1).setDuration(duration);
        alpha1.start();
        alpha.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }
        });
    }

    private void frontAnim() {
        mFontView.setVisibility(VISIBLE);
        mBackView.setVisibility(VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBackView, "rotationY", 0, 180).setDuration(duration);
        objectAnimator.start();
        Log.d("wwq", "flipView");
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mBackView, "alpha", 1, 0).setDuration(duration);
        alpha.start();
        alpha.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(mBackView, "rotationY", -180, 0).setDuration(duration);
        objectAnimator1.start();
        Log.d("wwq", "flipView");
        ObjectAnimator alpha2 = ObjectAnimator.ofFloat(mBackView, "alpha", 1, 0).setDuration(duration);
        alpha2.start();
        ObjectAnimator alpha1 = ObjectAnimator.ofFloat(mBackView, "alpha", 0, 1).setDuration(duration);
        alpha1.start();
        alpha.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mFontView.setVisibility(VISIBLE);
                mBackView.setVisibility(GONE);
            }
        });
    }
}
