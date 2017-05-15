package com.wx.myproject.apptest;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.wx.myproject.R;
import com.wx.myproject.apptest.bottom.MyBottomView;
import com.wx.myproject.apptest.listener.HomeFragListener;
import com.wx.myproject.base.BaseActivity;
import com.wx.myproject.constant.Constant;
import com.wx.myproject.util.DensityUtil;

/**
 * Created by wwq on 2017/5/15.
 */

public class GankActivity extends BaseActivity implements HomeFragListener {
    private MyBottomView mBottomView;
    private FragmentUtil fragmentUtil;
    BottomNavigationView.OnNavigationItemSelectedListener myOnNavigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentUtil.initFragment(Constant.HOME);
                    return true;
                case R.id.navigation_more:
                    fragmentUtil.initFragment(Constant.MORE);
                    return true;
                case R.id.navigation_user:
                    fragmentUtil.initFragment(Constant.ME);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected int generateId() {
        return R.layout.gank_layout_main;
    }

    @Override
    public void initView() {
        mBottomView = (MyBottomView) findViewById(R.id.bottom_view);
        mBottomView.setOnNavigationItemSelectedListener(myOnNavigationListener);
        fragmentUtil = new FragmentUtil(this);
        fragmentUtil.initFragment(Constant.HOME);
    }

    @Override
    public void showBottom() {
        Log.d("wwq", "showBottom");
        if(mBottomView.isHidden()){
            mBottomView.show();
            ObjectAnimator.ofFloat(mBottomView,"translationY",DensityUtil.dip2px(this,56),0).setDuration(400).start();

        }
    }

    @Override
    public void hideBottom() {
        Log.d("wwq", "hideBottom9090");
        if (!mBottomView.isHidden()) {
            mBottomView.hide();
            ObjectAnimator.ofFloat(mBottomView,"translationY",0, DensityUtil.dip2px(this,56)).setDuration(400).start();
        }

    }
}
