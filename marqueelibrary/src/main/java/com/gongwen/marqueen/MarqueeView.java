package com.gongwen.marqueen;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * Created by GongWen on 16/12/20.
 */

public class MarqueeView extends ViewFlipper {
    //interval 必须大于 animDuration，否则视图将会重叠
    private int interval = 2500;//Item翻页时间间隔
    private int animDuration = 500;//Item动画执行时间
    private Animation animIn, animOut;//进出动画
    private int animInRes = R.anim.bottom_in;
    private int animOutRes = R.anim.top_out;

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeView, 0, 0);
        interval = a.getInt(R.styleable.MarqueeView_marqueeInterval, interval);
        animInRes = a.getResourceId(R.styleable.MarqueeView_marqueeAnimIn, animInRes);
        animOutRes = a.getResourceId(R.styleable.MarqueeView_marqueeAnimOut, animOutRes);
        animDuration = a.getInt(R.styleable.MarqueeView_marqueeAnimDuration, animDuration);
        a.recycle();

        setFlipInterval(interval);
        animIn = AnimationUtils.loadAnimation(getContext(), animInRes);
        animIn.setDuration(animDuration);
        setInAnimation(animIn);
        animOut = AnimationUtils.loadAnimation(getContext(), animOutRes);
        animOut.setDuration(animDuration);
        setOutAnimation(animOut);
    }

    public void setMarqueeFactory(MarqueeFactory factory) {
        factory.setAttachedToMarqueeView(this);
        removeAllViews();
        List<View> mViews = factory.getMarqueeViews();
        if (mViews != null) {
            for (int i = 0; i < mViews.size(); i++) {
                addView(mViews.get(i));
            }
        }
    }

    public void setInterval(int interval) {
        this.interval = interval;
        setFlipInterval(interval);
    }

    public void setAnimDuration(int animDuration) {
        this.animDuration = animDuration;
        animIn.setDuration(animDuration);
        setInAnimation(animIn);
        animOut.setDuration(animDuration);
        setOutAnimation(animOut);
    }

    public void setAnimInAndOut(Animation animIn, Animation animOut) {
        this.animIn = animIn;
        this.animOut = animOut;
        setInAnimation(animIn);
        setOutAnimation(animOut);
    }

    public void setAnimInAndOut(int animInId, int animOutID) {
        animIn = AnimationUtils.loadAnimation(getContext(), animInId);
        animOut = AnimationUtils.loadAnimation(getContext(), animOutID);
        animIn.setDuration(animDuration);
        animOut.setDuration(animDuration);
        setInAnimation(animIn);
        setOutAnimation(animOut);

    }

    public Animation getAnimIn() {
        return animIn;
    }

    public Animation getAnimOut() {
        return animOut;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopFlipping();
    }
}
