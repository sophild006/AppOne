package com.wx.myproject;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    public void deleteItem(int position) {
        Toast.makeText(MainActivity.this, "position: " + position, Toast.LENGTH_SHORT).show();
        mList.remove(position-1);
        mAdapter.setDatas(mList);
        mAdapter.notifyDataSetChanged();
    }
}
