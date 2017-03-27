package com.wx.myproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wx.myproject.R;
import com.wx.myproject.bean.main.MainBean;
import com.wx.myproject.bean.main.User;
import com.wx.myproject.mvp.presenter.Presenter;

/**
 * Created by wwq on 2017/3/26.
 */

public class MainAdapter extends BaseRecycleViewAdapter {

    private Presenter presenter;
    private Context context;
    public final static int TYPE_HEAD = 0;
    public final static int TYPE_URL = 1;
    public final static int TYPE_IMG = 2;

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public MainAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        }
        int viewtype = 0;
        Log.d("wwq","datas: "+datas.size());
        MainBean bean = (MainBean) datas.get(position-1);
        Log.d("wwq","bean: "+bean.toString());
        if (MainBean.TYPE_IMG.equalsIgnoreCase(bean.getType())) {
            viewtype = TYPE_IMG;
        } else if (MainBean.TYPE_URL.equalsIgnoreCase(bean.getType())) {
            viewtype = TYPE_URL;
        }
        return viewtype;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        Log.d("wwq","viewType: "+viewType);
        if (viewType == TYPE_HEAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.head_view, parent, false);
            holder = new HeaderViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
            if (viewType == TYPE_IMG) {
                holder = new ImageHolder(view, viewType);
            } else if (viewType == TYPE_URL) {
                holder = new UrlViewHolder(view, viewType);
            }
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewholder, final int position) {
        if (getItemViewType(position) == TYPE_HEAD) {
            Log.d("wwq","TYPE_HEAD: "+TYPE_HEAD);
        } else {
            int resetPosition = position - 1;//因为多了一个无用的Header的位置；
            MainHolder holder = (MainHolder) viewholder;
            MainBean bean = (MainBean) datas.get(resetPosition);
            User uerName = bean.getUser();
            String userName = uerName.getName();
            Log.d("wwq","userName: "+userName);
            String userImg = uerName.getHeadUrl();
            holder.userName.setText(userName);
            Glide.with(context).load(userImg).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.userImg);


            holder.deleteTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.deleteItem(position);
                }
            });
        }

    }
    public class HeaderViewHolder extends RecyclerView.ViewHolder{

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
    @Override
    public int getItemCount() {
        return datas.size() + 1;//有head需要加1
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

}
