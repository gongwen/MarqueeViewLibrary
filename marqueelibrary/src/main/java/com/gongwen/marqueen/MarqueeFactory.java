package com.gongwen.marqueen;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Created by GongWen on 16/12/20.
 * 负责解析提供数据及事件监听
 */

public abstract class MarqueeFactory<T extends View, E> extends Observable {
    public final static String COMMAND_UPDATE_DATA = "UPDATE_DATA";

    protected Context mContext;
    private OnItemClickListener onItemClickListener;
    protected List<T> mViews;
    protected List<E> datas;
    private MarqueeView mMarqueeView;

    public MarqueeFactory(Context mContext) {
        this.mContext = mContext;
    }

    protected abstract T generateMarqueeItemView(E data);

    protected List<T> getMarqueeViews() {
        return mViews != null ? mViews : Collections.EMPTY_LIST;
    }

    public void setData(List<E> datas) {
        if (datas == null) {
            return;
        }
        this.datas = datas;
        mViews = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            E data = datas.get(i);
            T mView = generateMarqueeItemView(data);
            mView.setTag(new ViewHolder(mView, data, i));
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickListener((ViewHolder<T, E>) view.getTag());
                    }
                }
            });
            mViews.add(mView);
        }
        notifyDataChanged();
    }

    public void setOnItemClickListener(OnItemClickListener<T, E> mOnItemClickListener) {
        this.onItemClickListener = mOnItemClickListener;
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

    private boolean isAttachedToMarqueeView() {
        return this.mMarqueeView != null;
    }

    protected void attachedToMarqueeView(MarqueeView marqueeView) {
        if (!isAttachedToMarqueeView()) {
            this.mMarqueeView = marqueeView;
            this.addObserver(marqueeView);
            return;
        }
        throw new IllegalStateException(String.format("The %s has been attached to the %s!", toString(), mMarqueeView.toString()));
    }

    private void notifyDataChanged() {
        if (isAttachedToMarqueeView()) {
            setChanged();
            notifyObservers(COMMAND_UPDATE_DATA);
        }
    }
}