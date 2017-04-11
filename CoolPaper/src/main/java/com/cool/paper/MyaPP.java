package com.cool.paper;

import android.app.Application;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

public class MyApp extends Application {
    private List<BaseActivity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        activityList = new ArrayList<>();
    }

    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    public void addActivity(@NonNull BaseActivity a) {
        for (BaseActivity activity : activityList) {
            if (activity.equals(a)) {
                return;
            }
        }
        activityList.add(a);
    }

    public void removeActivity(BaseActivity a) {
        activityList.remove(a);
    }


}
