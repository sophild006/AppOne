package com.wx.myproject.test.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.telecom.DisconnectCause;

import com.wx.myproject.R;

/**
 * Created by Administrator on 2017/4/17.
 */

public class HomeFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] TITLES;
    private Fragment[] fragments;

    public HomeFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        TITLES = mContext.getResources().getStringArray(R.array.fragtitles);
        fragments = new Fragment[TITLES.length];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments[position];
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = LiveFragment.newInstance();
                    break;
                case 1:
                    fragment = CommandFragment.newInstance();
                    break;
                case 2:
                    fragment = FocusFragment.newInstance();
                    break;

                case 3:
                    fragment = DisCoveryFragment.newInstance();
                    break;
                case 4:
                    fragment = DisCoveryFragment.newInstance();
                    break;
                case 5:
                    fragment = DisCoveryFragment.newInstance();
                    break;
                case 6:
                    fragment = LiveFragment.newInstance();
                    break;
                case 7:
                    fragment = LiveFragment.newInstance();
                    break;
                case 8:
                    fragment = LiveFragment.newInstance();
                    break;
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return TITLES[position];
    }
}
