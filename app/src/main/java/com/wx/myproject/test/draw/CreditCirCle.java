package com.wx.myproject.test.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/4/14.
 */

public class CreditCirCle extends View {

    // 圆环的信用等级文本
    String[] sesameStr = new String[]{
            "350", "较差",
            "550", "中等",
            "600", "良好",
            "650", "优秀",
            "700", "极好",
            "950"
    };


    //外层矩形
    private RectF mMiddleRect;
    //内层矩形
    private RectF mInnerRect;


    //外层圆环画笔
    private Paint mMiddleArcPaint;
    //内层圆环画笔
    private Paint mInnerArcPaint;


    //信用等级文本画笔
    private Paint mTextPaint;

    // 默认宽高值
    private int defaultSize;

    // 距离圆环的值
    private int arcDistance;

    // view宽度
    private int width;

    // view高度
    private int height;
    //半径
    private int radius;

    // 默认Padding值
    private final static int defaultPadding = 20;


    //  圆环起始角度
    private final static float mStartAngle = 165f;

    // 圆环结束角度
    private final static float mEndAngle = 210f;

    public CreditCirCle(Context context) {
        this(context, null);
    }

    public CreditCirCle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CreditCirCle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        defaultSize = dp2px(250);
        arcDistance = dp2px(14);


        //外层圆环画笔
        mMiddleArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMiddleArcPaint.setStrokeWidth(8);
        mMiddleArcPaint.setColor(Color.WHITE);
        mMiddleArcPaint.setStyle(Paint.Style.STROKE);
        mMiddleArcPaint.setAlpha(80);



        //内层圆环画笔
        mInnerArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerArcPaint.setStrokeWidth(30);
        mInnerArcPaint.setColor(Color.GREEN);
        mInnerArcPaint.setAlpha(80);
        mInnerArcPaint.setStyle(Paint.Style.STROKE);



        //正中间字体画笔
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        radius = width / 2;

        mMiddleRect = new RectF(
                defaultPadding, defaultPadding,
                width - defaultPadding, height - defaultPadding);

        mInnerRect = new RectF(
                defaultPadding + arcDistance,
                defaultPadding + arcDistance,
                width - defaultPadding - arcDistance,
                height - defaultPadding - arcDistance);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMiddleArc(canvas);
        drawInnerArc(canvas);
        drawCenterText(canvas);
//        canvas.drawRect(mInnerRect,mInnerArcPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(resolveMeasure(widthMeasureSpec),
                resolveMeasure(heightMeasureSpec));
    }

    /**
     * 根据传入的值进行测量
     */
    public int resolveMeasure(int measureSpec) {

        int result = 0;
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (MeasureSpec.getMode(measureSpec)) {

            case MeasureSpec.UNSPECIFIED:
                result = defaultSize;
                break;

            case MeasureSpec.AT_MOST:
                //设置warp_content时设置默认值
                result = Math.min(specSize, defaultSize);
                break;
            case MeasureSpec.EXACTLY:
                //设置math_parent 和设置了固定宽高值
                break;
            default:
                result = defaultSize;
        }

        return result;
    }


    /**
     * 绘制外层圆环
     */
    private void drawMiddleArc(Canvas canvas) {
        canvas.drawArc(mMiddleRect, mStartAngle, mEndAngle, false, mMiddleArcPaint);
    }

    /**
     * 绘制内层圆环
     */
    private void drawInnerArc(Canvas canvas) {

        canvas.drawArc(mInnerRect, mStartAngle, mEndAngle, false, mInnerArcPaint);
    }

    /**
     * 绘制中间文本
     */
    private void drawCenterText(Canvas canvas) {
        //绘制Logo
        mTextPaint.setTextSize(40);
        canvas.drawText("BETA", radius, radius-dp2px(60), mTextPaint);

        //绘制信用分数
        mTextPaint.setTextSize(200);
        mTextPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText(String.valueOf(300), radius, radius + 70, mTextPaint);
//
//        //绘制信用级别
//        mTextPaint.setTextSize(80);
//        canvas.drawText(sesameLevel, radius, radius + 160, mTextPaint);
//
//        //绘制评估时间
//        mTextPaint.setTextSize(30);
//        canvas.drawText(evaluationTime, radius, radius + 205, mTextPaint);
    }























    /**
     * dp2px
     */
    public int dp2px(int values) {

        float density = getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }

}
