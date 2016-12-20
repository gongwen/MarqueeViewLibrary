package com.gw.marquee;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.gongwen.marqueen.MarqueeFactory;

public class ComplexViewMF extends MarqueeFactory<RelativeLayout, String> {
    private LayoutInflater inflater;

    public ComplexViewMF(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RelativeLayout generateMarqueeItemView(String data) {
        RelativeLayout mView = (RelativeLayout) inflater.inflate(R.layout.complex_view, null);
        return mView;
    }
}