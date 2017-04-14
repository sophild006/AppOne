package com.cool.paper.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.paper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> implements ItemTouchHelperAdapter{
    private Context context;
    private List<PhotoBean> mList;

    public MyAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
    }

    public void setData(List<PhotoBean> list) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.clear();
        mList.addAll(list);
        Log.d("wwq", "size: " + mList.size());
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        Log.d("wwq", "position: " + position+" title: "+mList.get(position).getTitle());
        mList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        Glide.with(context).load(mList.get(position).getUrl()).into(holder.imageView);
        holder.tvTitle.setText("" + mList.get(position).getTitle());
    }

    private void removeAnimation(final View itemView, final int position) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, -context.getResources().getDisplayMetrics().widthPixels);
        valueAnimator.setDuration(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                itemView.setX(value);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeItem(position);
            }
        });
        valueAnimator.start();
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        PhotoBean prev = mList.remove(fromPosition);
        mList.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView tvTitle;
        public ImageView ivClose;

        public MyHolder(final View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_download_image);
            tvTitle = (TextView) itemView.findViewById(R.id.item_download_title);
            ivClose = (ImageView) itemView.findViewById(R.id.item_download_closeBtn);
            ivClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    removeAnimation(itemView, getAdapterPosition());
                    mList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }
}
