package com.wx.myproject.test.login;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wx.myproject.R;
import com.wx.myproject.base.BaseActivity;

/**
 * Created by Administrator on 2017/4/14.
 */

public class LoginActivity extends BaseActivity {
    private SignContainer signContainer;
    private SlideScollView slideScrollView;
    private int width = 0;

    @Override
    protected int generateId() {
        return R.layout.activity_login_main;
    }

    @Override
    public void initView() {
        width = getResources().getDisplayMetrics().widthPixels;
        Log.d("wwq", "width=" + width);
        signContainer = (SignContainer) findViewById(R.id.signContainer);
        slideScrollView = (SlideScollView) findViewById(R.id.slideScrollView);
        slideScrollView.setIScrollCallBack(new SlideScollView.IScrollCallBack() {
            @Override
            public void onScrollProcess(int process, boolean isLeft) {
                if (Math.abs(process) > 1) {
                    int process1 = Math.abs(process) > width / 2 ? 100 : (int) ((float) (process) / (width / 2) * 100);
                    Log.d("wwq", "process= " + process + "   process1=" + Math.abs(process1));
                    signContainer.setAnimProportion(Math.abs(process1));
                }
            }
        });

    }
}
