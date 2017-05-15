package com.wx.myproject.apptest.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by wwq on 2017/5/15.
 */
public class MoreFragment extends Fragment {
    private static MoreFragment instance;

    public static MoreFragment getInstance() {
        if(instance==null){
            instance=new MoreFragment();
        }
        return instance;
    }
}
