package com.gongwen.marqueen;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.gongwen.marqueen.util.Util;

import java.util.List;

/**
 * Created by GongWen on 17/9/15.
 * <p>
 * 属性(继承自父View的属性＋自身特有属性)
 * SimpleMarqueeView_smvTextColor
 * SimpleMarqueeView_smvTextSize
 * SimpleMarqueeView_smvTextGravity
 */

public class SimpleMarqueeView extends MarqueeView {
    private ColorStateList textColor = null;
    private float textSize = 15;
    private int smvTextGravity = Gravity.NO_GRAVITY;

    public SimpleMarqueeView(Context context) {
        this(context, null);
    }

    public SimpleMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleMarqueeView, 0, 0);
        textColor = a.getColorStateList(R.styleable.SimpleMarqueeView_smvTextColor);
        if (a.hasValue(R.styleable.SimpleMarqueeView_smvTextSize)) {
            textSize = a.getDimension(R.styleable.SimpleMarqueeView_smvTextSize, textSize);
            textSize = Util.px2Sp(getContext(), textSize);
        }
        smvTextGravity = a.getInt(R.styleable.SimpleMarqueeView_smvTextGravity, smvTextGravity);
        a.recycle();
    }

    public void setMarqueeFactory(SimpleMF factory) {
        super.setMarqueeFactory(factory);
        List<TextView> views = factory.getMarqueeViews();
        for (TextView textView : views) {
            textView.setTextSize(textSize);
            textView.setGravity(smvTextGravity);
            if (textColor != null) {
                textView.setTextColor(textColor);
            }
        }
    }

    public void setTextSize(float textSize) {
        if (this.textSize != textSize) {
            this.textSize = textSize;
            if (factory != null) {
                for (Object obj : factory.getMarqueeViews()) {
                    ((TextView) obj).setTextSize(textSize);
                }
            }
        }
    }

    public void setTextColor(@ColorInt int color) {
        setTextColor(ColorStateList.valueOf(color));
    }

    public void setTextColor(ColorStateList colors) {
        this.textColor = colors;
        if (factory != null) {
            for (Object obj : factory.getMarqueeViews()) {
                ((TextView) obj).setTextColor(textColor);
            }
        }
    }

    public void setTextGravity(int gravity) {
        if (this.smvTextGravity != gravity) {
            this.smvTextGravity = gravity;
            if (factory != null) {
                for (Object obj : factory.getMarqueeViews()) {
                    ((TextView) obj).setGravity(smvTextGravity);
                }
            }
        }
    }
}
