package com.wx.myproject;

import android.app.Application;
import android.content.Intent;

import com.wx.myproject.service.MainService;
import com.wx.myproject.util.GlobalContext;

/**
 * Created by wwq on 2017/3/22.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalContext.setContext(getApplicationContext());
        startService(new Intent(getApplicationContext(), MainService.class));
    }
}
