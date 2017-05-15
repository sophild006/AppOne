package com.wx.myproject.apptest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wx.myproject.R;
import com.wx.myproject.base.BaseActivity;
import com.wx.myproject.constant.Constant;
import com.wx.myproject.util.PreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wwq on 2017/5/14.
 */

public class SplashActivity extends BaseActivity {
    ImageView mBgimg;
    private String defaultUrl = "https://images.unsplash.com/photo-1494122474412-aeaf73d11da8?w=1080&h=1920";


    @Override
    protected int generateId() {
        return R.layout.layout_gank;
    }

    @Override
    public void initView() {
        mBgimg= (ImageView) findViewById(R.id.bgIv);
        String url = getSplashImg();
        if (!TextUtils.isEmpty(url)) {
            show(url);
            // 开启个Intentservice后台请求数据，供下次显示加载；
            // 开这个service的好处是关闭activity后台也可以继续请求，
            // 而不会终止，网络慢点也可以不受影响；
            startService(new Intent(this,SplashService.class));
        }else{
            show(defaultUrl);
        }
    }

    private void show(String url) {
        Glide.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(mBgimg);
        ObjectAnimator.ofFloat(mBgimg, "alpha", 0.2f, 1f).setDuration(2000).start();
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(1,1.2f).setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value= (float) animation.getAnimatedValue();
                mBgimg.setScaleX(value);
                mBgimg.setScaleY(value);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                SplashActivity.this.startActivity(new Intent(SplashActivity.this,GankActivity.class));
                SplashActivity.this.finish();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public String getSplashImg() {
        String splashImg = PreferenceHelper.getString(Constant.SP_KEY_SPLASH_IMG_URL);
        return splashImg;
    }
}
