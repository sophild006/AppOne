package com.wx.myproject.mvp.model;

import android.os.AsyncTask;

import com.wx.myproject.listener.BaseListener;

/**
 * Created by wwq on 2017/3/23.
 */

public class BaseModel {

    public BaseModel() {
    }


    public void loadData(int type, final BaseListener listener) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "success";
            }

            @Override
            protected void onPostExecute(String s) {
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
