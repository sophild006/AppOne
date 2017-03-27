package com.wx.myproject;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.wx.myproject.adapter.MainAdapter;
import com.wx.myproject.base.BaseActivity;
import com.wx.myproject.bean.main.MainBean;
import com.wx.myproject.mvp.impl.BaseImpl;
import com.wx.myproject.mvp.presenter.Presenter;
import com.wx.myproject.view.DivItemDecoration;
import com.wx.myproject.view.ExpandTextView;
import com.wx.myproject.view.SnsPopupWindow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BaseImpl.View {

    private Presenter presenter;
    private ExpandTextView mExpandView;
    private LinearLayoutManager manager;
    private SuperRecyclerView superRecyvleView;
    private MainAdapter mAdapter;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private List<MainBean> mList = new ArrayList<>();

    @Override
    protected int generateId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        presenter = new Presenter(this);
        superRecyvleView = (SuperRecyclerView) findViewById(R.id.superRecyvleView);
        manager = new LinearLayoutManager(this);
        superRecyvleView.setLayoutManager(manager);
        superRecyvleView.addItemDecoration(new DivItemDecoration(2, true));
        superRecyvleView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        mAdapter = new MainAdapter(this);
        mAdapter.setPresenter(presenter);
        superRecyvleView.setAdapter(mAdapter);
        presenter.loadData(1);

        superRecyvleView.getSwipeToRefresh().post(new Runnable() {
            @Override
            public void run() {
                superRecyvleView.setRefreshing(true);//执行下拉刷新的动画
                refreshListener.onRefresh();//执行数据加载操作
            }
        });
        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.loadData(0);
                    }
                }, 2000);
            }
        };
        superRecyvleView.setRefreshListener(refreshListener);


        superRecyvleView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(MainActivity.this).resumeRequests();
                } else {
                    Glide.with(MainActivity.this).pauseRequests();
                }

            }
        });
        superRecyvleView.setupMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                presenter.loadData(1);

            }
        }, 1);

//        mExpandView = (ExpandTextView) findViewById(R.id.expandTv);
//        mExpandView.setText("清明节又叫踏青节，在仲春与暮春之交，也就是冬至后的第108天。是中国传统节日，也是最重要的祭祀节日之一，是祭祖和扫墓的日子。中华民族传统的清明节大约始于周代，距今已有二千五百多年的历史。清明最早只是一种节气的名称，其变成纪念");
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void updateData(List<MainBean> object) {
        if (object != null) {
            mList = object;
            superRecyvleView.setRefreshing(false);
            Log.d("wwq", "data.size(): " + object.size());
            mAdapter.setDatas(mList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deleteItem(String position) {
        Toast.makeText(MainActivity.this, "position: " + position, Toast.LENGTH_SHORT).show();
        List<MainBean> datas = mAdapter.getDatas();
        for (int i=0;i<datas.size();i++){
            if(position.equalsIgnoreCase(datas.get(i).getId())){
                datas.remove(i);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.recycle();
        }

    }
}
