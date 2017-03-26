package com.wx.myproject.listener;

/**
 * Created by wwq on 2017/3/23.
 */

public interface BaseListener {
    void onLoadSuccess(Object object);

    void onLoadFailed(Object object);
}
