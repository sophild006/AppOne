package com.wx.myproject.test.swipe;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import com.wx.myproject.R;
import com.wx.myproject.anim.WrapAnimatorListener;

/**
 * Created by wangjun on 16/1/28.
 */
public class BoosterPullLayout extends FrameLayout {

    private final String TAG = this.getClass().getSimpleName();
    //    private Context mContext;
    private ViewGroup mPullView;
    //    private View mHintView;
    private RecyclerView mListView;
    //    private ImageView mArrowView;
    private TextView mStateLabelTextView;
    private TextView mStateTextView;
    private View mStateCollapseLabelTextView;
    private View mStateCollapseTextView;
    private View mRecommendTextView;
    private View mStateCollapseLayout;
    private View mTopFooterLayout;
    private View mToastLayout;
    private View mCenterLayout;
    private View mBottomLayout;


    private Rect mStateLabelRect;
    private Rect mStateRect;
    private Rect mStateCollapseLabelRect;
    private Rect mStateCollapseRect;
    private float mToastHeight;

    private static final int TOUCH_STATE_REST = 0x00;
    private static final int TOUCH_STATE_SCROLLING = 0x01;

    private int mTouchState = TOUCH_STATE_REST;
    private static final int MAX_SETTLE_DURATION = 600; // ms

    private static final int SNAP_VELOCITY = 400;

    //    private int mTouchSlop;
//    private float mDownMotionX,mDownMotionY;
    private float mLastMotionX, mLastMotionY;
//    private boolean mIsBeingDragged;

    private boolean mIsCollapse;

    private int mPullExpandHeight;
    private int mPullCollapseHeight;
    private int mScrollerDiff;

    private boolean mTouchAtPullView;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    private boolean mShowedToast;
    private int mShowCount = 2;
    private Animator mToastAnimator;
    private ImageView mToastStorageImageView;
    private int mToastCpuLayoutHeight = 0;
    private int mToastCpuImageViewHeight = 0;

    public BoosterPullLayout(Context context) {
        super(context);
        init(context);
    }

    public BoosterPullLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
//        final ViewConfiguration configuration = ViewConfiguration.get(context);
//        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
//        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mScroller = new Scroller(context);
//        mContext = context;
        mToastHeight = getResources().getDimension(R.dimen.booster_toast_height);
        mShowedToast = mShowCount < 3;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int height = paddingTop;
        View firstView = getChildAt(0);
//        int firstViewHeight = firstView.getMeasuredHeight();
        firstView.layout(paddingLeft, height, firstView.getMeasuredWidth() + paddingRight, firstView.getMeasuredHeight() + height);

        height += firstView.getMeasuredHeight();
        View secondView = getChildAt(1);
        secondView.layout(paddingLeft, height, firstView.getMeasuredWidth() + paddingRight, secondView.getMeasuredHeight() + height);

//        height +=secondView.getMeasuredHeight();
        View thirdView = getChildAt(2);
        int diff = firstView.getMeasuredHeight() - (mScrollerDiff == 0 ? (height * 2 / 3) : mScrollerDiff);
        int lastViewHeight = thirdView.getMeasuredHeight() - diff;
        thirdView.layout(paddingLeft, height, thirdView.getMeasuredWidth() + paddingRight, height + lastViewHeight);

        View loadingView = getChildAt(3);
        loadingView.layout(paddingLeft, height, thirdView.getMeasuredWidth() + paddingRight, height + lastViewHeight);

        //
        View arrawView = getChildAt(4);
        int left = (getMeasuredWidth() - arrawView.getMeasuredWidth()) / 2;
        LayoutParams layoutParams = (LayoutParams) arrawView.getLayoutParams();
        if (mIsCollapse) {
            int top = getMeasuredHeight() + mScrollerDiff - arrawView.getMeasuredHeight();
            arrawView.layout(left, top, left + arrawView.getMeasuredWidth(), top + arrawView.getMeasuredHeight() - layoutParams.bottomMargin);
        } else {
            int top = getMeasuredHeight() - arrawView.getMeasuredHeight();
            arrawView.layout(left, top, left + arrawView.getMeasuredWidth(), top + arrawView.getMeasuredHeight() - layoutParams.bottomMargin);
        }

//        View topFloatActionButton = getChildAt(5);
//        int topFabLeft = paddingLeft + getMeasuredWidth() - topFloatActionButton.getMeasuredWidth();
//        topFloatActionButton.layout(topFabLeft, paddingTop, topFabLeft + topFloatActionButton.getMeasuredWidth(), paddingTop + topFloatActionButton.getMeasuredHeight());
//
//        View bottomFloatActionButton = getChildAt(6);
//        if (bottomFloatActionButton.getVisibility() == GONE) {
//            bottomFloatActionButton.layout(0, 0, 0, 0);
//        } else {
//            int bottomFabTop = getMeasuredHeight() - bottomFloatActionButton.getMeasuredHeight();
//            int bottomFabLeft = paddingLeft + getMeasuredWidth() - bottomFloatActionButton.getMeasuredWidth();
//            bottomFloatActionButton.layout(bottomFabLeft, bottomFabTop, bottomFabLeft + bottomFloatActionButton.getMeasuredWidth(), bottomFabTop + bottomFloatActionButton.getMeasuredHeight());
//        }
//        else {
//            int bottomFabTop = getMeasuredHeight()-bottomFloatActionButton.getMeasuredHeight();
//            int bottomFabLeft = paddingLeft+getMeasuredWidth() * 3 / 5;
//            bottomFloatActionButton.layout(bottomFabLeft, bottomFabTop, bottomFabLeft + bottomFloatActionButton.getMeasuredWidth(), bottomFabTop+bottomFloatActionButton.getMeasuredHeight());
//        }
        //底部闪电按钮布局
//        int bottomFabTop = getMeasuredHeight()+mScrollerDiff-bottomFloatActionButton.getMeasuredHeight();
//        int bottomFabLeft = paddingLeft+getMeasuredWidth() * 3 / 5;
//        bottomFloatActionButton.layout(bottomFabLeft, bottomFabTop, bottomFabLeft + bottomFloatActionButton.getMeasuredWidth(), bottomFabTop+bottomFloatActionButton.getMeasuredHeight());

//        printDebug("onLayout " );

    }

    //    private Paint mPaint = new Paint();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        printDebug("onDraw ");
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_booster_background);
//        //draw corner
//        canvas.drawBitmap(bitmap,0,0,mPaint);
//        canvas.drawBitmap(bitmap,canvas.getWidth()-bitmap.getWidth(),0,mPaint);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        printDebug("onFinishInflate");
        mPullView = (ViewGroup) findViewById(R.id.top_layer);
        mStateLabelTextView = (TextView) findViewById(R.id.textview_state_label);
        mStateTextView = (TextView) findViewById(R.id.textview_state);
        mStateCollapseLabelTextView = findViewById(R.id.textview_collapse_state_label);
        mStateCollapseTextView = findViewById(R.id.textview_collapse_state);
        mRecommendTextView = findViewById(R.id.text_recommend);
        mStateCollapseLayout = findViewById(R.id.layout_collapse_state);
        mTopFooterLayout = findViewById(R.id.layout_top_footer);
        mToastLayout = findViewById(R.id.layout_toast);
        mCenterLayout = findViewById(R.id.layout_center);
        mBottomLayout = findViewById(R.id.layout_bottom);

        mToastStorageImageView = (ImageView) findViewById(R.id.iv_click_toast_storage);
        mListView = (RecyclerView) findViewById(R.id.gridview);
        mPullView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mPullExpandHeight = mPullView.getHeight();
                        mPullCollapseHeight = mCenterLayout.getHeight() + mBottomLayout.getHeight() + mTopFooterLayout.getHeight();
                        mScrollerDiff = mPullExpandHeight - mPullCollapseHeight;
                        printDebug("onGlobalLayout " + mPullExpandHeight + " " + mPullCollapseHeight);
                        getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mToastAnimator != null && (mToastAnimator.isRunning() || mToastAnimator.isStarted())) {
            mToastAnimator.cancel();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction() & MotionEventCompat.ACTION_MASK;
        if ((action == MotionEvent.ACTION_MOVE) && mTouchState == TOUCH_STATE_SCROLLING) {
            return true;
        }

        final float x = ev.getX();
        final float y = ev.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST : TOUCH_STATE_SCROLLING;
                int fixedY = (int) (y);
                if (isTouchInPullView((int) x, fixedY)) {
                    mTouchAtPullView = true;
                }
                mLastMotionY = y;
                mLastMotionX = x;
//                mDownMotionX = x;
//                mDownMotionY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                final float xDiff = Math.abs(mLastMotionX - x);
                final float yDiff = Math.abs(mLastMotionY - y);

//                GbLog.d(mIsCollapse, mTouchAtPullView, isGridViewScrollTop(), y-mLastMotionY, yDiff, mTouchSlop, xDiff);
                if (mIsCollapse && (mTouchAtPullView || isGridViewScrollTop())) {//折叠状态下 ，手势下移
                    if (y - mLastMotionY > 0 && yDiff * 0.5f > xDiff) {
                        mTouchState = TOUCH_STATE_SCROLLING;
                        requestDisallowInterceptTouchEvent(true);
                        printDebug("onInterceptTouchEvent will be Intercept 1");
                    }
                } else if (!mIsCollapse) {//展开状态下，手势上移
                    if (y - mLastMotionY < 0 && yDiff * 0.5f > xDiff) {
                        mTouchState = TOUCH_STATE_SCROLLING;
                        requestDisallowInterceptTouchEvent(true);
                        printDebug("onInterceptTouchEvent will be Intercept 2");
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mTouchState = TOUCH_STATE_REST;
                mTouchAtPullView = false;
                break;

        }
        return mTouchState == TOUCH_STATE_SCROLLING;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        final int action = event.getAction();
        final float x = event.getX();
        final float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
//                int deltaY = (int) (mLastMotionY - mDownMotionY);
//                setOffset(deltaY);
                int deltaY = (int) (mLastMotionY - y);
                scrollBy(0, deltaY);
                animLayout(getScrollY());
                ViewCompat.postInvalidateOnAnimation(this);
                mLastMotionX = x;
                mLastMotionY = y;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mVelocityTracker != null) {
                    final VelocityTracker velocityTracker = mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000);
                    int velocityY = (int) velocityTracker.getYVelocity();
                    if (velocityY > SNAP_VELOCITY) {//下划
                        smoothScrollTo(0, 0, velocityY);
                    } else if (velocityY < -SNAP_VELOCITY) {//上划
                        smoothScrollTo(0, mScrollerDiff, velocityY);
                    } else {
                        int delta = mScrollerDiff - getScrollY();
                        if (Math.abs(delta) < mScrollerDiff / 2 || delta < 0) {
                            smoothScrollTo(0, mScrollerDiff, velocityY);
                        } else {
                            smoothScrollTo(0, 0, velocityY);
                        }
                    }
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                mTouchState = TOUCH_STATE_REST;
                mTouchAtPullView = false;
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (!mScroller.isFinished() && mScroller.computeScrollOffset()) {
//            mHandleView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            animLayout(mScroller.getCurrY());
            ViewCompat.postInvalidateOnAnimation(this);
//            requestLayout();
        } else {
            completeScroll(true);
        }
    }

    private void completeScroll(boolean postEvents) {
        if (Math.abs(mScrollerDiff - getScrollY()) < mScrollerDiff / 10) {
            mIsCollapse = true;
            mCenterLayout.setBackgroundResource(R.mipmap.ic_booster_center_bg2);
            postToastEventIfNeeded();
        } else {
            mIsCollapse = false;
            //TODO button GONE
        }
//        if (postEvents) {
//            ViewCompat.postOnAnimation(this, mEndScrollRunnable);
//        } else {
//            mEndScrollRunnable.run();
//        }
//        Log.d("demo", TAG + " completeScroll " + getScrollY() + " " + mIsCollapse);
    }

    public void scrollToBottom() {
        mScroller.startScroll(0, getScrollY(), 0, mScrollerDiff - getScrollY(), 600);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private void animLayout(int scrollY) {
        if (mStateLabelRect == null) {
            mStateLabelRect = new Rect();
            mStateLabelTextView.getGlobalVisibleRect(mStateLabelRect);

            mStateRect = new Rect();
            mStateTextView.getGlobalVisibleRect(mStateRect);

            mStateCollapseLabelRect = new Rect();
            mStateCollapseLabelTextView.getGlobalVisibleRect(mStateCollapseLabelRect);

            mStateCollapseRect = new Rect();
            mStateCollapseTextView.getGlobalVisibleRect(mStateCollapseRect);

            printDebug("animLayout " + mStateLabelRect + " " + mStateRect + "  " + mStateCollapseLabelRect + " " + mStateCollapseRect + " " + mToastHeight);
        }
        float animtorValue = scrollY * 1.0f / mScrollerDiff;
//        printDebug("animLayout "+scrollY+" "+mScrollerDiff+"  "+mStateCollapseLabelRect);
        if (animtorValue < 0) {
            animtorValue = 0;
        }
        if (animtorValue > 1) {
            animtorValue = 1;
        }
        if (animtorValue < 0.01f) {//start
            mStateCollapseLayout.setVisibility(View.INVISIBLE);
            mRecommendTextView.setVisibility(View.VISIBLE);
            mStateLabelTextView.setVisibility(View.VISIBLE);
            mStateTextView.setVisibility(View.VISIBLE);
        } else if (animtorValue >= 0.99f) {//end
            mStateCollapseLayout.setVisibility(View.VISIBLE);
            mRecommendTextView.setVisibility(View.INVISIBLE);
            mStateLabelTextView.setVisibility(View.INVISIBLE);
            mStateTextView.setVisibility(View.INVISIBLE);
        } else {
            mStateCollapseLayout.setVisibility(View.INVISIBLE);
            mRecommendTextView.setVisibility(View.VISIBLE);
            mStateLabelTextView.setVisibility(View.VISIBLE);
            mStateTextView.setVisibility(View.VISIBLE);
            mCenterLayout.setBackgroundResource(R.mipmap.ic_booster_center_bg);
        }
        mRecommendTextView.setAlpha(1.0f - animtorValue);

        float stateLabelSize = getResources().getDimension(R.dimen.textview_state_label_size);
        float stateSize = getResources().getDimension(R.dimen.textview_state_size);
        float collapseStateLabelSize = getResources().getDimension(R.dimen.textview_collapse_state_label_size);
        float collapseStateSize = getResources().getDimension(R.dimen.textview_collapse_state_size);
        float scale = 1.0f - (stateLabelSize - collapseStateLabelSize) / stateLabelSize * animtorValue;
        mStateLabelTextView.setScaleX(scale);
        mStateLabelTextView.setScaleY(scale);
        float labelTransX = (mStateLabelRect.width() - mStateCollapseLabelRect.width()) * animtorValue / 2;
        float labelTransY = (mStateCollapseLabelRect.top - mStateLabelRect.top) * animtorValue;
        mStateLabelTextView.setTranslationX(-labelTransX);
        mStateLabelTextView.setTranslationY(labelTransY);

        float stateTranX = (mStateCollapseRect.left - mStateRect.left) * animtorValue;
        float stateTranY = (mStateCollapseRect.top - mStateRect.top) * animtorValue;
        float sizeDiffX = (mStateRect.width() - mStateCollapseRect.width()) * animtorValue / 2;
        float sizeDiffY = (mStateRect.height() - mStateCollapseRect.height()) * animtorValue / 2;
        mStateTextView.setTranslationX(stateTranX - sizeDiffX);
        mStateTextView.setTranslationY(stateTranY - sizeDiffY);

        float scale2 = 1.0f - (stateSize - collapseStateSize) / stateSize * animtorValue;
        mStateTextView.setScaleX(scale2);
        mStateTextView.setScaleY(scale2);

        float fabScale = 1.0f - animtorValue;
        //防止出现逆向放大现象
        if (fabScale >= 0) {
//            mTopBoosterFAB.setScaleX(fabScale);
//            mTopBoosterFAB.setScaleY(fabScale);
            //TODO button Scale
        }
        //TODO button
//        mBottomBoosterFAB.setTranslationY(scrollY);
//        if(animtorValue<=1) {
//            mBottomBoosterFAB.setVisibility(View.VISIBLE);
//            mBottomBoosterFAB.setScaleX(animtorValue);
//            mBottomBoosterFAB.setScaleY(animtorValue);
//        }
//        if(animtorValue==0){
//            mBottomBoosterFAB.setVisibility(View.GONE);
//        }

        if (isNeedShowToast()) {
            float listTranY = mToastHeight * animtorValue;
            mListView.setTranslationY(listTranY);
        }
//        printDebug("animLayout "+labelTransY+" "+stateTranX+" "+stateTranY+" "+animtorValue);
    }

    private void smoothScrollTo(int x, int y, int velocity) {
        int sx = getScrollX();
        int sy = getScrollY();
        int dx = x - sx;
        int dy = y - sy;
        if (dx == 0 && dy == 0) {
            //todo
            Log.d("demo", TAG + " smoothScrollTo >>>>>");
            completeScroll(false);
            return;
        }

        int duration = 0;
        velocity = Math.abs(velocity);
        if (velocity > 0) {
            int halfWidth = mScrollerDiff / 2;
            float distanceRatio = Math.min(1f, 1.0f * Math.abs(dx) / mScrollerDiff);
            distanceRatio -= 0.5f; // center the values about 0.
            distanceRatio *= 0.3f * Math.PI / 2.0f;
            final float distance = (float) (halfWidth + halfWidth * Math.sin(distanceRatio));
            duration = 4 * Math.round(1000 * Math.abs(distance / velocity));
        } else {
//            final float pageWidth = width * mAdapter.getPageWidth(mCurItem);
            final float pageDelta = (float) Math.abs(dx) / (mScrollerDiff * 1.0f);
            duration = (int) ((pageDelta + 1) * 100);
        }

        duration = Math.min(duration, MAX_SETTLE_DURATION);
        mScroller.startScroll(sx, sy, dx, dy, duration);
        ViewCompat.postInvalidateOnAnimation(this);
//        invalidate();
//        printDebug("smoothScrollTo velocity:" + velocity + " duration:" + duration);
    }

    private boolean isTouchInPullView(int x, int y) {
        Rect rect = new Rect();
        mPullView.getLocalVisibleRect(rect);
        rect.offset(-getScrollX(), -getScrollY());
        boolean result = rect.contains(x, y);
        if (result) {
            printDebug("isTouchInPullView event rect:" + rect + " x:" + x + " y:" + y);
        }
        return result;
    }

    private boolean isGridViewScrollTop() {
        GridLayoutManager layoutManager = (GridLayoutManager) mListView.getLayoutManager();
        View view = mListView.getChildAt(layoutManager.findFirstVisibleItemPosition());
        return (view != null) && (view.getTop() == 0);
    }

    private boolean isNeedShowToast() {
        return mShowedToast;
    }

    private void postToastEventIfNeeded() {
        if (isNeedShowToast()) {
            mShowedToast = false;
            mListView.setEnabled(false);
            mToastAnimator = createToastAnimator();
            mToastAnimator.addListener(new WrapAnimatorListener() {
                public void onAnimationStart(Animator animation) {
                    mToastLayout.setVisibility(View.VISIBLE);
                }

                public void onAnimationEnd(Animator animation) {
                    mToastLayout.setVisibility(View.GONE);
                    mListView.setEnabled(true);
                }
            });
            mToastAnimator.start();
        }
    }

    private AnimatorSet createToastAnimator() {
        AnimatorSet toastSet = new AnimatorSet();
        Animator appearAnimator = ObjectAnimator.ofFloat(mToastLayout, "alpha", 0.0f, 1.0f).setDuration(500);
        ValueAnimator delayAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(2 * 1000);
        Animator disappearAnimator = ObjectAnimator.ofFloat(mToastLayout, "alpha", 1.0f, 0f).setDuration(400);
        ObjectAnimator tranAnimator = ObjectAnimator.ofFloat(mListView, "translationY", mToastHeight, 0).setDuration(400);
        toastSet.playSequentially(appearAnimator, delayAnimator, disappearAnimator, tranAnimator);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(toastSet, createUpAnimator());
        return set;
    }

    private Animator createUpAnimator() {
        ValueAnimator upAnimator = ValueAnimator.ofFloat(0f, 1f);
        upAnimator.addListener(new WrapAnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mToastStorageImageView.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mToastStorageImageView.setVisibility(GONE);
            }
        });
        upAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                float ty = mToastCpuImageViewHeight - (mToastCpuLayoutHeight + mToastCpuImageViewHeight) * value;
                mToastStorageImageView.setTranslationY(ty);
            }
        });
        upAnimator.setDuration(1400).setRepeatCount(1);
        return upAnimator;
    }

    private void printDebug(String message) {
        Log.d("wwq", TAG + " " + message);
    }
}
