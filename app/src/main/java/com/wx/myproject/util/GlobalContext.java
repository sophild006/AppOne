package com.wx.myproject.util;

import android.content.Context;

/**
 * Created by wwq on 2017/3/23.
 */

public class GlobalContext {

    private static Context context;

    public static void setContext(Context mContext) {
        context = mContext;
    }

    public static Context getContext() {
        return context;
    }
}
