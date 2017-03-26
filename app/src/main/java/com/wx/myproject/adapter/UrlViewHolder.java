package com.wx.myproject.adapter;

import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wx.myproject.R;

/**
 * Created by wwq on 2017/3/26.
 */

public class UrlViewHolder extends MainHolder {
    private LinearLayout urlBody;
    private ImageView urlIcon;
    private TextView urlContent;

    public UrlViewHolder(View itemView, int viewType) {
        super(itemView, viewType);
    }

    @Override
    public void initViewStub(View itemView, ViewStub viewStub) {
        if (viewStub == null) {
            throw new IllegalArgumentException("viewStub is null...");
        }
        viewStub.setLayoutResource(R.layout.layout_url_body);
        View view = viewStub.inflate();
        if (view != null) {
            urlBody = (LinearLayout) view.findViewById(R.id.urlBody);
            urlIcon = (ImageView) view.findViewById(R.id.urlIcon);
            urlContent = (TextView) view.findViewById(R.id.urlContent);
        }
    }
}
