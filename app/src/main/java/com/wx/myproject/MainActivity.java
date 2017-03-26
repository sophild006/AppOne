package com.wx.myproject;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wx.myproject.base.BaseActivity;
import com.wx.myproject.mvp.impl.BaseImpl;
import com.wx.myproject.mvp.presenter.Presenter;
import com.wx.myproject.view.SnsPopupWindow;

public class MainActivity extends BaseActivity implements BaseImpl.View {

    private Presenter presenter;
    private TextView showWindow;
    private SnsPopupWindow snsPopupWindow;

    @Override
    protected int generateId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        presenter = new Presenter(this);
        presenter.loadData(1);
        snsPopupWindow=new SnsPopupWindow(this);
        snsPopupWindow.update();
        showWindow= (TextView) findViewById(R.id.showWindow);
        showWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snsPopupWindow.showPopupWindow(v);
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void updateData(Object object) {
        if (object != null) {
            Toast.makeText(MainActivity.this, "" + object.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
