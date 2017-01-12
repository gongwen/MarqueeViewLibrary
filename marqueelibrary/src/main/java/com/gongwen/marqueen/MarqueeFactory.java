package com.gongwen.marqueen;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GongWen on 16/12/20.
 * 负责解析提供数据及事件监听
 */

public abstract class MarqueeFactory<T extends View, E> {
    protected Context mContext;
    protected OnItemClickListener onItemClickListener;
    protected List<T> mViews;
    protected List<E> datas;
    private boolean isOnItemClickRegistered;
    private MarqueeView mMarqueeView;

    public MarqueeFactory(Context mContext) {
        this.mContext = mContext;
    }

    public abstract T generateMarqueeItemView(E data);

    public void updateData(List<E> datas) {
        if (datas == null || datas.size() == 0) {
            return;
        }
        this.datas = datas;
        mViews = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            E data = datas.get(i);
            T mView = generateMarqueeItemView(data);
            mViews.add(mView);
        }
        registerOnItemClick();
        if (mMarqueeView != null) {
            mMarqueeView.setMarqueeFactory(this);
        }
    }

    public void setData(final List<E> datas) {
        if (datas == null || datas.size() == 0) {
            return;
        }
        if (mMarqueeView == null || (mMarqueeView != null && this.datas == null)) {
            updateData(datas);
        } else {
            //防止多次更新数据可能导致的叠影问题
            if (mMarqueeView.getInAnimation() != null) {
                mMarqueeView.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
                    boolean isAnimationStopped = false;

                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (!isAnimationStopped) {
                            updateData(datas);
                            isAnimationStopped = false;
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener<T, E> mOnItemClickListener) {
        this.onItemClickListener = mOnItemClickListener;
        registerOnItemClick();
    }

    public List<T> getMarqueeViews() {
        return mViews;
    }

    private void registerOnItemClick() {
        if (!isOnItemClickRegistered && onItemClickListener != null && datas != null) {
            for (int i = 0; i < datas.size(); i++) {
                T mView = mViews.get(i);
                E data = datas.get(i);
                mView.setTag(new ViewHolder(mView, data, i));
                mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClickListener((ViewHolder<T, E>) view.getTag());
                    }
                });
            }
            isOnItemClickRegistered = true;
        }
    }

    public interface OnItemClickListener<V extends View, E> {
        void onItemClickListener(ViewHolder<V, E> holder);
    }

    public static class ViewHolder<V extends View, P> {
        public V mView;
        public P data;
        public int position;

        public ViewHolder(V mView, P data, int position) {
            this.mView = mView;
            this.data = data;
            this.position = position;
        }
    }

    public void setAttachedToMarqueeView(MarqueeView marqueeView) {
        this.mMarqueeView = marqueeView;
    }
}