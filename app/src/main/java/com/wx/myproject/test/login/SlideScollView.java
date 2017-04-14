package com.wx.myproject.test.login;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.wx.myproject.R;

/**
 * Created by Administrator on 2017/4/14.
 */

public class SlideScollView extends LinearLayout {
    private UnderlineDevider mUnderlineDevider;

    public SlideScollView(Context context) {
        this(context, null);
    }

    public SlideScollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideScollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.title_scroll_layout, this, true);
        mUnderlineDevider = (UnderlineDevider) findViewById(R.id.underline_v);
        mUnderlineDevider.setIMoveCallBack(new UnderlineDevider.IMoveCallBack() {
            @Override
            public void onMove(int process, boolean isLeft) {
                if (mIScrollCallBack != null) {
                    mIScrollCallBack.onScrollProcess(process, isLeft);
                }
            }
        });
    }

    private int downX;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(downX - ev.getX()) > 20) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int x = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (mIScrollCallBack != null) {
                    int nowDisplacement = x - downX;
//                    mUnderlineDevider.move(nowDisplacement);
                    mIScrollCallBack.onScrollProcess(nowDisplacement, true);
                }
                break;
            case MotionEvent.ACTION_UP:
                mUnderlineDevider.updateAnimFinally();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void upEvent() {


    }

    private IScrollCallBack mIScrollCallBack;

    public interface IScrollCallBack {
        void onScrollProcess(int process, boolean isLeft);
    }

    public void setIScrollCallBack(IScrollCallBack l) {
        mIScrollCallBack = l;
    }

}
