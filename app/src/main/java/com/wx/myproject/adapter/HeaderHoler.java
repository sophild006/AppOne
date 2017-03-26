package com.wx.myproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;

import com.wx.myproject.R;

/**
 * Created by wwq on 2017/3/26.
 */
public class HeaderHoler extends MainHolder {
    public HeaderHoler(View itemView, int viewType) {
        super(itemView, viewType);

    }

    @Override
    public void initViewStub(View itemView, ViewStub viewStub) {

        if (viewStub == null) {
            throw new IllegalArgumentException("viewStub is null...");
        }
        viewStub.setLayoutResource(R.layout.head_view);
        View view = viewStub.inflate();
    }
}
