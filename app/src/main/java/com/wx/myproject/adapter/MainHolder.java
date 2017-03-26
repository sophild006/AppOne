package com.wx.myproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.wx.myproject.R;

/**
 * Created by wwq on 2017/3/26.
 */

public abstract class MainHolder extends RecyclerView.ViewHolder {
    public final static int TYPE_URL = 1;
    public final static int TYPE_IMAGE = 2;
    public ImageView userImg;
    public TextView userName;
    public TextView shareLink;


    public TextView timeTv;
    public TextView deleteTv;
    public int viewType;

    public MainHolder(View itemView, int viewType) {
        super(itemView);
        this.viewType = viewType;
        ViewStub viewStub = (ViewStub) itemView.findViewById(R.id.viewStub);
        initViewStub(itemView, viewStub);
        userImg = (ImageView) itemView.findViewById(R.id.userImg);
        userName = (TextView) itemView.findViewById(R.id.userName);
        shareLink = (TextView) itemView.findViewById(R.id.shareLink);
        timeTv = (TextView) itemView.findViewById(R.id.timeTv);
        deleteTv = (TextView) itemView.findViewById(R.id.delete);
    }

    public abstract void initViewStub(View itemView, ViewStub viewStub);
}
