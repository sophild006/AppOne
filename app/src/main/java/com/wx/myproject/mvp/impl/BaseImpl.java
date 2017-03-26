package com.wx.myproject.mvp.impl;

/**
 * Created by wwq on 2017/3/22.
 */

public interface BaseImpl {

    interface View {
        void showProgress();

        void hideProgress();

        void updateData(Object object);
    }

    interface PresenterImpl {
        void loadData(int type);
    }
}
