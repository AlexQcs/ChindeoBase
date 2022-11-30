package com.caimao.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * 项目名: HighFrequencyLotteryBox
 * 包 名: cn.sscbox.view
 * Copyright © 2017, CAIMAO.COM All Rights Reserved.
 * $Id$
 */

public class DrawableLeftCenterTextView extends androidx.appcompat.widget.AppCompatTextView {
    public DrawableLeftCenterTextView(Context context) {
        super(context);
    }

    public DrawableLeftCenterTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public DrawableLeftCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = this.getCompoundDrawables();
        Drawable drawableLeft = drawables[0];
        if (drawableLeft != null) {
            float textWidth = this.getPaint().measureText(this.getText().toString());
            int drawablePadding = this.getCompoundDrawablePadding();
            int drawableWidth = drawableLeft.getIntrinsicWidth();
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            int paddingLeft = (int) ((getWidth() - bodyWidth) / 2);
            this.setPadding(paddingLeft + drawablePadding, getPaddingTop(), 0, getPaddingBottom());
        }
        super.onDraw(canvas);
    }
}
