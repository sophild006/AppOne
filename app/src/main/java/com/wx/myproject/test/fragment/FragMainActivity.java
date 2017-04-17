package com.wx.myproject.test.fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.FrameLayout;

import com.wx.myproject.R;
import com.wx.myproject.base.BaseActivity;

/**
 * Created by Administrator on 2017/4/17.
 */

public class FragMainActivity extends BaseActivity {

    private FrameLayout fragmentContainer;

    @Override
    protected int generateId() {
        return R.layout.activity_main_frag;

    }

    @Override
    public void initView() {
        fragmentContainer = (FrameLayout) findViewById(R.id.activity_main_fragment);
        replaceFragment(this, new MainFragment());
    }



    private void replaceFragment(BaseActivity a, BaseFragment f) {
        a.getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.activity_main_fragment, f)
                .commit();
    }

}
