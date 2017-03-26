package com.wx.myproject.anim;

import android.animation.Keyframe;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;

/**
 * Created by wwq on 2017/3/23.
 */

public class AnimationUtil {

    public static void startAnimScalX(final View view,int duration, float... floats){
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(floats).setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value= (float) animation.getAnimatedValue();
                view.setScaleX(value);
            }
        });
        valueAnimator.start();
    }

}
