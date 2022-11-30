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

public class DrawableRightCenterTextView extends androidx.appcompat.widget.AppCompatTextView {
    public DrawableRightCenterTextView(Context context) {
        super(context);
    }

    public DrawableRightCenterTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public DrawableRightCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = this.getCompoundDrawables();
        Drawable drawableRight = drawables[2];
        if (drawableRight != null) {
            float textWidth = this.getPaint().measureText(this.getText().toString());
            int drawablePadding = this.getCompoundDrawablePadding();
            int drawableWidth = drawableRight.getIntrinsicWidth();

            float bodyWidth = textWidth + drawableWidth + drawablePadding;

            int paddingRight = (int) ((getWidth() - bodyWidth) / 2);

            this.setPadding(paddingRight, getPaddingTop(), paddingRight, getPaddingBottom());
        }
        super.onDraw(canvas);
    }
}
