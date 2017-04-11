package com.cool.paper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cool.paper.adapter.MyAdapter;
import com.cool.paper.adapter.PhotoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

public class TestActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private List<PhotoBean> beans = new ArrayList<>();
    private String[] url = new String[]{"https://images.unsplash.com/photo-1491852807958-4326560208e9?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=4102d95d1158284abf5caed6c664c287",
            "https://images.unsplash.com/photo-1491832136260-6db1d35247e9?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=e9aa3e9899d1e62d8e6993359e171f98", "https://images.unsplash.com/photo-1491833167315-f2f6c7c5deab?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=6f51b9f1f942e23d752c77f42b69652e"
    };
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content);
        toolbar = (Toolbar) findViewById(R.id.activity_download_manage_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(this);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mAdapter = new MyAdapter(this);
        recyclerView.setAdapter(mAdapter);
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
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_path:
                break;
            case R.id.action_detele:
                break;
        }
        return true;
    }
}
