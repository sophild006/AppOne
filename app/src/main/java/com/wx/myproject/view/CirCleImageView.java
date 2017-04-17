package com.wx.myproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

import com.wx.myproject.R;

/**
 * Created by Administrator on 2017/4/17.
 */

public class CirCleImageView extends ImageView {
    private Paint mBorderPaint;
    private int mBorderColor;
    private int DEFAULT_COLOR = Color.WHITE;
    private int mBorderWidth;

    public CirCleImageView(Context context) {
        this(context, null);
    }

    public CirCleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirCleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.circleStyle);
        mBorderColor = a.getColor(R.styleable.circleStyle_bordercolor, DEFAULT_COLOR);
        mBorderWidth = 1;
        a.recycle();
        init();
    }

    private void init() {
        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
    }

    private BitmapShader mShader;
    private final Paint mBitmapPaint = new Paint();
    private Matrix mMatrix = new Matrix();
    int radius;
    Bitmap rawBitmap;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rawBitmap = getBitmapFromDrawable(getDrawable());
        Log.d("wwq", "getwidth(): " + getWidth() + "   getHeigth(): " + getHeight());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int viewMinSize = Math.min(viewWidth, viewHeight);
        float dstWidth = viewMinSize;
        float dstHeight = viewMinSize;
        if (mShader == null) {
            mShader = new BitmapShader(rawBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        }
        if (mShader != null) {
            mMatrix.setScale((dstWidth - mBorderWidth * 2) / rawBitmap.getWidth(), (dstHeight - mBorderWidth * 2) / rawBitmap.getHeight());
            mShader.setLocalMatrix(mMatrix);
        }

        mBitmapPaint.setShader(mShader);
        float radius = viewMinSize / 2.0f;
        canvas.drawCircle(radius, radius, radius - mBorderWidth / 2.0f, mBorderPaint);
        canvas.translate(mBorderWidth, mBorderWidth);
        canvas.drawCircle(radius - mBorderWidth, radius - mBorderWidth, radius - mBorderWidth, mBitmapPaint);
    }

//            canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius / 2, mBitmapPaint);


    private Bitmap getBitmapFromDrawable(Drawable drawable) {

        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(2, 2,
                        Bitmap.Config.ARGB_8888);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                        Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
}
