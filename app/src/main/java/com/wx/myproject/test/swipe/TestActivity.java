package com.wx.myproject.test.swipe;

import android.os.Handler;

import com.wx.myproject.R;
import com.wx.myproject.base.BaseActivity;

/**
 * Created by Administrator on 2017/3/27.
 */

public class TestActivity extends BaseActivity {
    private BoosterPullLayout mBoosterPullLayout;

    @Override
    protected int generateId() {
        return R.layout.fragment_tab_booster;
    }

    @Override
    public void initView() {
        mBoosterPullLayout = (BoosterPullLayout) findViewById(R.id.layout_collapse);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBoosterPullLayout.scrollToBottom();
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBoosterPullLayout.scrollToTop();
            }
        }, 5000);

    }
}
