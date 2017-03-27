package com.wx.myproject.mvp.impl;

import com.wx.myproject.bean.main.MainBean;

import java.util.List;

/**
 * Created by wwq on 2017/3/22.
 */

public interface BaseImpl {

    interface View {
        void showProgress();

        void hideProgress();

        void updateData(List<MainBean> object);
        void deleteItem(int position);
    }

    interface PresenterImpl {
        void loadData(int type);
        void deleteItem(int position);
    }
}
