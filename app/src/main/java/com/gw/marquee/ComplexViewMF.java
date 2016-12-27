package com.gw.marquee;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gongwen.marqueen.MarqueeFactory;

public class ComplexViewMF extends MarqueeFactory<RelativeLayout, ComplexItemEntity> {
    private LayoutInflater inflater;

    public ComplexViewMF(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RelativeLayout generateMarqueeItemView(ComplexItemEntity data) {
        RelativeLayout mView = (RelativeLayout) inflater.inflate(R.layout.complex_view, null);
        ((TextView) mView.findViewById(R.id.title)).setText(data.getTitle());
        ((TextView) mView.findViewById(R.id.secondTitle)).setText(data.getSecondTitle());
        ((TextView) mView.findViewById(R.id.time)).setText(data.getTime());
        return mView;
    }
}