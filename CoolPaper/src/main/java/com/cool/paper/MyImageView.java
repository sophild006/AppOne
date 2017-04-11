package com.cool.paper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/4/11.
 */

public class MyImageView extends ImageView {
    private GestureDetector mDetector;

    public MyImageView(Context context) {
        super(context);
        init();
    }


    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDetector = new GestureDetector(getContext(), listener);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return true;
    }

    private Runnable clickRunnable = new Runnable() {
        @Override
        public void run() {
            if (mListener != null) {
                mListener.onclick();
            }
        }
    };
    private GestureDetector.OnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("wwq", "onDown");
            removeCallbacks(clickRunnable);
            return super.onDown(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d("wwq", "onSingleTapUp");
            postDelayed(clickRunnable, 200);
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("wwq", "onDoubleTap");
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("wwq", "onFling");
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("wwq", "onScroll");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

    };
    private setonClick mListener;

    public void setListener(setonClick listener) {
        mListener = listener;
    }

    public interface setonClick {
        void onclick();
    }

}
