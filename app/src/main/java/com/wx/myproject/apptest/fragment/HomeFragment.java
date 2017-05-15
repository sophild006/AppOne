package com.wx.myproject.apptest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wx.myproject.R;
import com.wx.myproject.apptest.listener.HomeFragListener;
import com.wx.myproject.apptest.view.MyCustomFloatActionbutton;
import com.wx.myproject.mvp.impl.BaseImpl;

/**
 * Created by wwq on 2017/5/15.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private HomeFragListener mListener;
    private static HomeFragment instance;
    private MyCustomFloatActionbutton myCustomButton;
    private View view;

    public static HomeFragment getInstance() {
        instance = new HomeFragment();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_frag_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        myCustomButton = (MyCustomFloatActionbutton) view.findViewById(R.id.myCustomButton);
        myCustomButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myCustomButton:
                if (myCustomButton.isHide()) {
//                    myCustomButton.showY();
                    myCustomButton.setIsHide(false);
                    mListener.showBottom();
                } else {
//                    myCustomButton.hideY();
                    myCustomButton.setIsHide(true);
                    mListener.hideBottom();
                }
                Toast.makeText(getActivity(), "---", Toast.LENGTH_LONG).show();
                break;

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeFragListener) {
            if (mListener == null) {
                mListener = (HomeFragListener) context;
            }
        }
    }
}
