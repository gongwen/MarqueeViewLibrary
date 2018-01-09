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
    protected List<T> mViews;
    protected List<E> dataList;
    private MarqueeView mMarqueeView;

    public MarqueeFactory(Context mContext) {
        this.mContext = mContext;
    }

    protected abstract T generateMarqueeItemView(E data);

    protected List<T> getMarqueeViews() {
        return mViews != null ? mViews : Collections.EMPTY_LIST;
    }

    public void setData(List<E> dataList) {
        if (dataList == null) {
            return;
        }
        this.dataList = dataList;
        mViews = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            E data = dataList.get(i);
            T mView = generateMarqueeItemView(data);
            mViews.add(mView);
        }
        notifyDataChanged();
    }


    public List<E> getData() {
        return dataList;
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