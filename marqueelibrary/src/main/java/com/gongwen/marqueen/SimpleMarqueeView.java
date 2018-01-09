package com.gongwen.marqueen;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
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

public class SimpleMarqueeView<E> extends MarqueeView<TextView, E> {
    private ColorStateList smvTextColor = null;
    private float smvTextSize = 15;
    private int smvTextGravity = Gravity.NO_GRAVITY;
    private boolean smvTextSingleLine = false;
    private TextUtils.TruncateAt smvTextEllipsize;

    public SimpleMarqueeView(Context context) {
        this(context, null);
    }

    public SimpleMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        int ellipsize = -1;
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleMarqueeView, 0, 0);
            smvTextColor = a.getColorStateList(R.styleable.SimpleMarqueeView_smvTextColor);
            if (a.hasValue(R.styleable.SimpleMarqueeView_smvTextSize)) {
                smvTextSize = a.getDimension(R.styleable.SimpleMarqueeView_smvTextSize, smvTextSize);
                smvTextSize = Util.px2Sp(getContext(), smvTextSize);
            }
            smvTextGravity = a.getInt(R.styleable.SimpleMarqueeView_smvTextGravity, smvTextGravity);
            smvTextSingleLine = a.getBoolean(R.styleable.SimpleMarqueeView_smvTextSingleLine, smvTextSingleLine);
            ellipsize = a.getInt(R.styleable.SimpleMarqueeView_smvTextEllipsize, ellipsize);
            a.recycle();
        }
        if (smvTextSingleLine && ellipsize < 0) {
            ellipsize = 3; // END
        }

        switch (ellipsize) {
            case 1:
                smvTextEllipsize = TextUtils.TruncateAt.START;
                break;
            case 2:
                smvTextEllipsize = TextUtils.TruncateAt.MIDDLE;
                break;
            case 3:
                smvTextEllipsize = TextUtils.TruncateAt.END;
                break;
        }
    }

    @Override
    protected void refreshChildViews() {
        super.refreshChildViews();
        List<TextView> views = factory.getMarqueeViews();
        for (TextView textView : views) {
            textView.setTextSize(smvTextSize);
            textView.setGravity(smvTextGravity);
            if (smvTextColor != null) {
                textView.setTextColor(smvTextColor);
            }
            textView.setSingleLine(smvTextSingleLine);
            textView.setEllipsize(smvTextEllipsize);
        }
    }

    public void setTextSize(float textSize) {
        this.smvTextSize = textSize;
        if (factory != null) {
            for (TextView textView : factory.getMarqueeViews()) {
                textView.setTextSize(textSize);
            }
        }
    }

    public void setTextColor(@ColorInt int color) {
        setTextColor(ColorStateList.valueOf(color));
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.smvTextColor = colorStateList;
        if (factory != null) {
            for (TextView textView : factory.getMarqueeViews()) {
                textView.setTextColor(smvTextColor);
            }
        }
    }

    public void setTextGravity(int gravity) {
        this.smvTextGravity = gravity;
        if (factory != null) {
            for (TextView textView : factory.getMarqueeViews()) {
                textView.setGravity(smvTextGravity);
            }
        }
    }

    public void setTextSingleLine(boolean singleLine) {
        this.smvTextSingleLine = singleLine;
        if (factory != null) {
            for (TextView textView : factory.getMarqueeViews()) {
                textView.setSingleLine(smvTextSingleLine);
            }
        }
    }

    public void setTextEllipsize(TextUtils.TruncateAt where) {
        if (where == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("The type MARQUEE is not supported!");
        }
        this.smvTextEllipsize = where;
        if (factory != null) {
            for (TextView textView : factory.getMarqueeViews()) {
                textView.setEllipsize(where);
            }
        }
    }
}
