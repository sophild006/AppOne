package com.wx.myproject.mvp.presenter;

import com.wx.myproject.bean.main.MainBean;
import com.wx.myproject.listener.BaseListener;
import com.wx.myproject.mvp.impl.BaseImpl;
import com.wx.myproject.mvp.model.BaseModel;

import java.util.List;

/**
 * Created by wwq on 2017/3/22.
 */

public class Presenter implements BaseImpl.PresenterImpl, BaseListener {

    private BaseImpl.View mView;
    private BaseModel model;

    public Presenter(BaseImpl.View view) {
        this.mView = view;
        model = new BaseModel();
    }

    @Override
    public void loadData(int type) {
        model.loadData(type,this);
    }

    @Override
    public void onLoadSuccess(List<MainBean> object) {
        if (mView != null) {
            mView.hideProgress();
            mView.updateData(object);
        }
    }

    @Override
    public void onLoadFailed(Object object) {

    }
}
