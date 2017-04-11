package com.cool.paper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.getInstance().addActivity(this);
        if (ThemeManager.getInstance(this).isLightTheme()) {
            setTheme(R.style.MysplashTheme_dark);
        } else {
            setTheme(R.style.MysplashTheme_light);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApp.getInstance().removeActivity(this);
    }

}
