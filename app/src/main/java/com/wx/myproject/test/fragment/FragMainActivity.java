package com.wx.myproject.test.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.FrameLayout;

import com.wx.myproject.R;
import com.wx.myproject.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d("wwq", "-----onKeyDown");
            if (getTopFragment() instanceof SearchFragment) {
                showAndHideNewFragment(this, new MainFragment(), new SearchFragment());
            } else {
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public List<BaseFragment> getFragments() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        List<BaseFragment> mList = new ArrayList<>();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (fragmentList.get(i) instanceof BaseFragment && !fragmentList.get(i).isHidden()) {
                mList.add((BaseFragment) fragmentList.get(i));
            }
        }
        return mList;
    }

    public BaseFragment getTopFragment() {
        List<BaseFragment> fragments = getFragments();
        if (fragments != null) {
            if (fragments.size() > 0) {
                return fragments.get(fragments.size() - 1);
            } else {
                return null;
            }
        }
        return null;
    }

    public void showAndHideNewFragment(FragMainActivity a, BaseFragment newF, BaseFragment oldF) {
        a.getSupportFragmentManager()
                .beginTransaction()
                .hide(oldF)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.activity_main_fragment, newF)
                .show(newF)
                .commit();
    }

}
