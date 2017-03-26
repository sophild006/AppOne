package com.wx.myproject.mvp.model;

import android.os.AsyncTask;

import com.wx.myproject.bean.main.MainBean;
import com.wx.myproject.listener.BaseListener;
import com.wx.myproject.test.GenerateData;

import java.util.List;

/**
 * Created by wwq on 2017/3/23.
 */

public class BaseModel {

    public BaseModel() {
    }


    public void loadData(int type, final BaseListener listener) {
        new AsyncTask<Void, Void, List<MainBean>>() {
            @Override
            protected List<MainBean> doInBackground(Void... params) {
                List<MainBean> beans=null;
                try {
                    Thread.sleep(2000);
                    beans= GenerateData.createData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return beans;
            }

            @Override
            protected void onPostExecute(List<MainBean> s) {
                super.onPostExecute(s);
                if (listener != null) {
                    listener.onLoadSuccess(s);
                }
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
