package com.cool.paper;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.cool.paper.adapter.MyAdapter;
import com.cool.paper.adapter.PhotoBean;
import com.cool.paper.adapter.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * Created by Administrator on 2017/4/11.
 */

public class TestActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private List<PhotoBean> beans = new ArrayList<>();
    private String[] url = new String[]{"https://images.unsplash.com/photo-1491852807958-4326560208e9?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=4102d95d1158284abf5caed6c664c287",
            "https://images.unsplash.com/photo-1491832136260-6db1d35247e9?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=e9aa3e9899d1e62d8e6993359e171f98", "https://images.unsplash.com/photo-1491833167315-f2f6c7c5deab?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=6f51b9f1f942e23d752c77f42b69652e"
    };
    private LinearLayout toast;
    //    private Toolbar toolbar;
    private ItemTouchHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_scroll);
//        toolbar = (Toolbar) findViewById(R.id.activity_download_manage_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        toast = (LinearLayout) findViewById(R.id.toast);
//        toolbar.inflateMenu(R.menu.toolbar_menu);
//        toolbar.setOnMenuItemClickListener(this);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mAdapter = new MyAdapter(this);
        recyclerView.setAdapter(mAdapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
        for (int m = 0; m < 3; m++) {
            PhotoBean bean = new PhotoBean();
            bean.setId(m);
            bean.setTitle("bean " + m);
            bean.setUrl(url[m]);
            beans.add(bean);
        }
        for (int m = 0; m < 3; m++) {
            PhotoBean bean = new PhotoBean();
            bean.setId(m);
            bean.setTitle("bean " + m);
            bean.setUrl(url[m]);
            beans.add(bean);
        }
        for (int m = 0; m < 3; m++) {
            PhotoBean bean = new PhotoBean();
            bean.setId(m);
            bean.setTitle("bean " + m);
            bean.setUrl(url[m]);
            beans.add(bean);
        }
        for (int m = 0; m < 3; m++) {
            PhotoBean bean = new PhotoBean();
            bean.setId(m);
            bean.setTitle("bean " + m);
            bean.setUrl(url[m]);
            beans.add(bean);
        }
        mAdapter.setData(beans);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animator mToastAnimator = createToastAnimator();
                mToastAnimator.start();
            }
        }, 5000);
    }

    private AnimatorSet createToastAnimator() {
        AnimatorSet toastSet = new AnimatorSet();
        Animator appearAnimator = ObjectAnimator.ofFloat(toast, "alpha", 0.0f, 1.0f).setDuration(500);
        ValueAnimator delayAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(2 * 1000);
        Animator disappearAnimator = ObjectAnimator.ofFloat(toast, "alpha", 1.0f, 0f).setDuration(400);
        ObjectAnimator tranAnimator = ObjectAnimator.ofFloat(recyclerView, "translationY", dip2px(40),dip2px(0)).setDuration(400);
        toastSet.playSequentially(appearAnimator, delayAnimator, disappearAnimator,tranAnimator);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(toastSet,createUpAnimator());
        return set;
    }

    private Animator createUpAnimator() {
        ValueAnimator upAnimator = ValueAnimator.ofFloat(0f, 1f);
        upAnimator.addListener(new WrapAnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                toast.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                toast.setVisibility(View.GONE);
            }
        });
        upAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                toast.setTranslationY(value);
            }
        });
        upAnimator.setDuration(1400).setRepeatCount(1);
        return upAnimator;
    }
//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_path:
//                break;
//            case R.id.action_detele:
//                break;
//        }
//        return true;
//    }

    public static int dip2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }
}
