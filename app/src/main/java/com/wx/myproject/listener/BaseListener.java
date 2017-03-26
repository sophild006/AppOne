package com.wx.myproject.listener;

import com.wx.myproject.bean.main.MainBean;

import java.util.List;

/**
 * Created by wwq on 2017/3/23.
 */

public interface BaseListener {
    void onLoadSuccess(List<MainBean> object);

    void onLoadFailed(Object object);
}
