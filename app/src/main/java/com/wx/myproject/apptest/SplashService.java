package com.wx.myproject.apptest;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by wwq on 2017/5/14.
 */
public class SplashService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SplashService(String name) {
        super(name);
    }

    public SplashService() {
        super("SplashService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        //TODO 请求数据


    }
}
