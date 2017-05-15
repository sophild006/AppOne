package com.wx.myproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wx.myproject.util.ActivityPool;

/**
 * Created by wwq on 2017/3/22.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPool.addActivity(this);
        setContentView(generateId());
        initView();
    }

    protected abstract int generateId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityPool.removeActivity(this);
    }

    public abstract void initView() ;
}
