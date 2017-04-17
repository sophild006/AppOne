package com.wx.myproject.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wx.myproject.R;

/**
 * Created by Administrator on 2017/4/17.
 */

public class SearchFragment extends BaseFragment implements View.OnClickListener {
    private Toolbar searchToolBar;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchToolBar = (Toolbar) view.findViewById(R.id.serach_toolbar);
        searchToolBar.setNavigationIcon(R.drawable.ic_back);
        searchToolBar.inflateMenu(R.menu.serchtoolbar);
        searchToolBar.setNavigationOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_search, container, false);
    }


    @Override
    public void onClick(View v) {
        showAndHideNewFragment((FragMainActivity) getActivity(), new MainFragment(), this);
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
