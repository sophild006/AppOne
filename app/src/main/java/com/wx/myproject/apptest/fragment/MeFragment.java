package com.wx.myproject.apptest.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by wwq on 2017/5/15.
 */
public class MeFragment extends Fragment {
    private static MeFragment instance;

    public static MeFragment getInstance() {
        if(instance==null){
            instance=new MeFragment();
        }
        return instance;
    }
}
