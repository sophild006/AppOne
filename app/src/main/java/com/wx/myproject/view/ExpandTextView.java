package com.wx.myproject.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wx.myproject.R;

/**
 * Created by wwq on 2017/3/26.
 */

public class ExpandTextView extends LinearLayout {
    private TextView NormalTv;
    private TextView PlusTv;
    private int DEFAULT_LINE = 3;
    private ExpandStatusChanged mExpandStatusChanged;

    public ExpandTextView(Context context) {
        this(context, null);

    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_magic_textview, this);
        NormalTv = (TextView) findViewById(R.id.normal_tv);
        PlusTv = (TextView) findViewById(R.id.plus_tv);
        NormalTv.setMaxLines(DEFAULT_LINE);
        PlusTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String plusContent = PlusTv.getText().toString().trim();
                if (plusContent.equalsIgnoreCase("全文")) {
                    PlusTv.setText("收起");
                    NormalTv.setMaxLines(Integer.MAX_VALUE);
                    setExpand(true);
                } else if (plusContent.equalsIgnoreCase("收起")) {
                    PlusTv.setText("全文");
                    NormalTv.setMaxLines(DEFAULT_LINE);
                    setExpand(false);
                }
                if (mExpandStatusChanged != null) {
                    mExpandStatusChanged.statusChanged(isExpand());
                }
            }
        });
    }

    public void setText(CharSequence charSequence) {

        NormalTv.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                //避免重复监听
                NormalTv.getViewTreeObserver().removeOnPreDrawListener(this);
                int lineCount = NormalTv.getLineCount();
                Log.d("wwq", "lineCount: " + lineCount);
                if (lineCount > DEFAULT_LINE) {
                    if (isExpand) {
                        NormalTv.setMaxLines(Integer.MAX_VALUE);
                        PlusTv.setText("收起");
                    } else {
                        NormalTv.setMaxLines(DEFAULT_LINE);
                        PlusTv.setText("全文");
                    }
                    PlusTv.setVisibility(VISIBLE);
                } else {
                    PlusTv.setVisibility(GONE);
                }
                return true;
            }
        });
        NormalTv.setText(charSequence);
    }

    private boolean isExpand=false;

    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpandStatusChanged(ExpandStatusChanged mChanged) {
        this.mExpandStatusChanged = mChanged;
    }

    public static interface ExpandStatusChanged {
        void statusChanged(boolean isExpand);
    }
}
