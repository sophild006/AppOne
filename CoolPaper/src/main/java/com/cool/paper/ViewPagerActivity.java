package com.cool.paper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ViewPagerActivity extends BaseActivity {
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        viewPager= (ViewPager) findViewById(R.id.viewPager);

    }
}
