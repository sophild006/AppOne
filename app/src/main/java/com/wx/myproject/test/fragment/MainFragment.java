package com.wx.myproject.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.wx.myproject.R;

/**
 * Created by Administrator on 2017/4/17.
 */

public class MainFragment extends BaseFragment implements Toolbar.OnMenuItemClickListener {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private Toolbar toolbar;
    private NoScrollViewPager viewPager;
    private SlidingTabLayout mSlidingTab;

    //toolBar滑动隐藏的关键是这个属性：app:layout_scrollFlags="scroll|enterAlways"
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar);
        toolbar.setOnMenuItemClickListener(this);
        viewPager = (NoScrollViewPager) view.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(5);
        mSlidingTab = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        viewPager.setAdapter(new HomeFragmentAdapter(getChildFragmentManager(), getActivity()));
        mSlidingTab.setViewPager(viewPager);
        viewPager.setCurrentItem(0);
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.game:
                //游戏中心
                break;

            case R.id.search:
                //离线缓存
                Log.d("wwq", "search...");
                showAndHideNewFragment((FragMainActivity) getActivity(), new SearchFragment(), this);
                break;
        }
        return true;
    }
}
