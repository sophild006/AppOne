package com.wx.myproject.test.login;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wx.myproject.R;

/**
 * Created by Administrator on 2017/4/14.
 */

public class SignContainer extends RelativeLayout {
    private Context context;
    private CardView mFirNameCv;
    private CardView mLastNameCv;
    private CardView mEmailCv;
    private CardView mPassWordCv;
    private CardView mConfirmCv;
    private EditText mFirNameEt;
    private EditText mLastNameEt;
    private EditText mEmailEt;
    private EditText mPassWordEt;
    private TextView mConfirmTv;
    private LinearLayout mLoginContainer;
    private LinearLayout mNameContainer;
    private static final int ANIM_DURATION = 2000;

    private int mLoginHeaderHeight;


    private ValueAnimator mContainerAnim;
    private ValueAnimator mFirCvAnim;


    public SignContainer(Context context) {
        this(context, null);
    }

    public SignContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SignContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
        initAnim();
    }

    private void initAnim() {
        mContainerAnim = ValueAnimator.ofInt(0, (int) mLoginHeaderHeight)
                .setDuration(ANIM_DURATION);
        mContainerAnim.setInterpolator(new OvershootInterpolator());
        mContainerAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int factor = (int) animation.getAnimatedValue();
                mLoginContainer.setTranslationY(factor);
            }
        });

        mFirCvAnim = ValueAnimator.ofFloat(0, 1)
                .setDuration(ANIM_DURATION);
        mFirCvAnim.setInterpolator(new AccelerateInterpolator());
        mFirCvAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float factor = (float) animation.getAnimatedValue();
                mFirNameCv.setAlpha(factor);
                mFirNameCv.setScaleX(factor);
                mFirNameCv.setScaleY(factor);
                mLastNameCv.setAlpha(factor);
                mLastNameCv.setScaleX(factor);
                mLastNameCv.setScaleY(factor);
            }
        });
    }


    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.sign_up_container, this, true);
        mFirNameCv = (CardView) findViewById(R.id.first_name_card_v);
        mLastNameCv = (CardView) findViewById(R.id.last_name_card_v);
        mEmailCv = (CardView) findViewById(R.id.email_card_v);
        mPassWordCv = (CardView) findViewById(R.id.password_card_v);
        mConfirmCv = (CardView) findViewById(R.id.confirm_card_v);
        mFirNameEt = (EditText) findViewById(R.id.first_name_edit_input);
        mLastNameEt = (EditText) findViewById(R.id.last_name_edit_input);
        mEmailEt = (EditText) findViewById(R.id.email_edit_input);
        mPassWordEt = (EditText) findViewById(R.id.password_edit_input);
        mConfirmTv = (TextView) findViewById(R.id.confirm_edit);
        mLoginContainer = (LinearLayout) findViewById(R.id.login_container);
        mNameContainer = (LinearLayout) findViewById(R.id.name_container);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mLoginHeaderHeight = mNameContainer.getMeasuredHeight();
                initAnim();
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mLoginContainer.setTranslationY(mLoginHeaderHeight);
            }
        });
    }

    public void setContainerY(int delta) {
        mLoginContainer.setTranslationY(delta);
    }

    public void setAnimProportion(int timeProportion) {
        float fraction = (float) (timeProportion) / 100f*2;
        Log.d("wwq", "fraction: " + fraction);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            mContainerAnim.setCurrentFraction(fraction);
            mFirCvAnim.setCurrentFraction(fraction);
        } else {
            mContainerAnim.setCurrentPlayTime((long) (fraction * ANIM_DURATION));
            mFirCvAnim.setCurrentPlayTime((long) (fraction * ANIM_DURATION));
        }
    }

}
