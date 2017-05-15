package com.wx.myproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.wx.myproject.R;
import com.wx.myproject.view.MultiImageView;

/**
 * Created by wwq on 2017/3/26.
 */
public class ImageHolder extends MainHolder {
    public MultiImageView mMultImageView;

    public ImageHolder(View view, int viewType) {
        super(view, viewType);
    }

    @Override
    public void initViewStub(View itemView, ViewStub viewStub) {

        if (viewStub == null) {
            throw new IllegalArgumentException("viewStub is null...");
        }
        viewStub.setLayoutResource(R.layout.layout_img_body);
        View inflate = viewStub.inflate();


    }
}
