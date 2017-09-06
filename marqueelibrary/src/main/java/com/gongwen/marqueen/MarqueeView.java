package com.gongwen.marqueen;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AnimRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

import com.gongwen.marqueen.util.AnimationListenerAdapter;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by GongWen on 16/12/20.
 * 属性
 * ViewAnimator_inAnimation
 * ViewAnimator_outAnimation
 * ViewAnimator_animateFirstView
 * ViewFlipper_flipInterval
 * ViewFlipper_autoStart
 * MarqueeView_marqueeAnimDuration
 * <p>
 * 注意：
 * interval 必须大于 animDuration，否则视图将会重叠
 */

public class MarqueeView extends ViewFlipper implements Observer {
    protected MarqueeFactory factory;
    private final int DEFAULT_ANIM_RES_IN = R.anim.in_bottom;
    private final int DEFAULT_ANIM_RES_OUT = R.anim.out_top;

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (getInAnimation() == null || getOutAnimation() == null) {
            setInAnimation(getContext(), DEFAULT_ANIM_RES_IN);
            setOutAnimation(getContext(), DEFAULT_ANIM_RES_OUT);
        }
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeView);
        if (a.hasValue(R.styleable.MarqueeView_marqueeAnimDuration)) {
            int animDuration = a.getInt(R.styleable.MarqueeView_marqueeAnimDuration, -1);
            getInAnimation().setDuration(animDuration);
            getOutAnimation().setDuration(animDuration);
        }
        a.recycle();
    }

    public void setAnimDuration(long animDuration) {
        if (getInAnimation() != null) {
            getInAnimation().setDuration(animDuration);
        }
        if (getOutAnimation() != null) {
            getOutAnimation().setDuration(animDuration);
        }
    }

    public void setInAndOutAnim(Animation inAnimation, Animation outAnimation) {
        setInAnimation(inAnimation);
        setOutAnimation(outAnimation);
    }

    public void setInAndOutAnim(@AnimRes int inResId, @AnimRes int outResId) {
        setInAnimation(getContext(), inResId);
        setOutAnimation(getContext(), outResId);
    }

    public void setMarqueeFactory(MarqueeFactory factory) {
        this.factory = factory;
        factory.attachedToMarqueeView(this);
        refreshChildViews();
    }

    private void refreshChildViews() {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        List<View> mViews = factory.getMarqueeViews();
        for (int i = 0; i < mViews.size(); i++) {
            addView(mViews.get(i));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) return;
        if (MarqueeFactory.COMMAND_UPDATE_DATA.equals(arg.toString())) {
            Animation animation = getInAnimation();
            if (animation != null && animation.hasStarted()) {
                animation.setAnimationListener(new AnimationListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        refreshChildViews();
                        if (animation != null) {
                            animation.setAnimationListener(null);
                        }
                    }
                });
            } else {
                refreshChildViews();
            }
        }
    }
}
