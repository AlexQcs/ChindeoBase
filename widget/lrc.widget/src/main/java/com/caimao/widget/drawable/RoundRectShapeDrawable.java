/**
 * 项目名:LazyLibs
 * 包  名:com.lazy2b.lrc.core.app.ui.view
 * 文件名:RoundRectDradable.java
 * 创  建:2015-11-17上午12:42:12
 * Copyright © 2015, lazy2b.com All Rights Reserved.
 */
package com.caimao.widget.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.RoundRectShape;

/**
 * 类名: RoundRectDradable <br/>
 *
 * @author E-mail:jack.lin@qq.com
 * @version $Id: RoundRectDrawable.java 14 2015-11-17 08:39:39Z lazy2b $
 */
public class RoundRectShapeDrawable extends Drawable {
    private static final float DEFAULT_RADIUS = -1.01f;
    private Paint mPaint = new Paint();
    private RoundRectShape mShape;
    private float[] mOuter;
    private int mColor;
    private int mPressColor;
    private float mTopLeftRadius1 = DEFAULT_RADIUS;
    private float mTopRightRadius1 = DEFAULT_RADIUS;
    private float mBottomLeftRadius1 = DEFAULT_RADIUS;
    private float mBottomRightRadius1 = DEFAULT_RADIUS;
    private float mTopLeftRadius2 = DEFAULT_RADIUS;
    private float mTopRightRadius2 = DEFAULT_RADIUS;
    private float mBottomLeftRadius2 = DEFAULT_RADIUS;
    private float mBottomRightRadius2 = DEFAULT_RADIUS;

    public RoundRectShapeDrawable() {
        mColor = Color.WHITE;
        mPressColor = Color.WHITE;
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
    }


    public RoundRectShapeDrawable setRadius(float topLeft1, float topLeft2, float topRight1, float topRight2,
                                            float bottomLeft1, float bottomLeft2, float bottomRight1, float bottomRight2) {
        setTopLeftRadius1(topLeft1);
        setTopRightRadius1(topRight1);
        setBottomLeftRadius1(bottomLeft1);
        setBottomRightRadius1(bottomRight1);
        setTopLeftRadius2(topLeft2);
        setTopRightRadius2(topRight2);
        setBottomLeftRadius2(bottomLeft2);
        setBottomRightRadius2(bottomRight2);
        return this;
    }

    public RoundRectShapeDrawable setRadius(float topLeft, float topRight, float bottomLeft, float bottomRight) {
        setTopLeftRadius1(topLeft);
        setTopRightRadius1(topRight);
        setBottomLeftRadius1(bottomLeft);
        setBottomRightRadius1(bottomRight);
        setTopLeftRadius2(topLeft);
        setTopRightRadius2(topRight);
        setBottomLeftRadius2(bottomLeft);
        setBottomRightRadius2(bottomRight);
        return this;
    }

    public float getTopLeftRadius1() {
        return mTopLeftRadius1;
    }

    public void setTopLeftRadius1(float topLeftRadius) {
        this.mTopLeftRadius1 = topLeftRadius;
    }

    public float getTopRightRadius1() {
        return mTopRightRadius1;
    }

    public void setTopRightRadius1(float topRightRadius) {
        this.mTopRightRadius1 = topRightRadius;
    }

    public float getBottomLeftRadius1() {
        return mBottomLeftRadius1;
    }

    public void setBottomLeftRadius1(float bottomLeftRadius) {
        this.mBottomLeftRadius1 = bottomLeftRadius;
    }

    public float getBottomRightRadius1() {
        return mBottomRightRadius1;
    }

    public void setBottomRightRadius1(float bottomRightRadius) {
        this.mBottomRightRadius1 = bottomRightRadius;
    }

    public int getPressColor() {
        return mPressColor;
    }

    public void setPressColor(int pressColor) {
        this.mPressColor = pressColor;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        refreshShape();
        mShape.resize(bounds.right - bounds.left, bounds.bottom - bounds.top);
    }

    private void refreshShape() {
        mOuter = new float[]{mTopLeftRadius1, mTopLeftRadius2, mTopRightRadius1, mTopRightRadius2, mBottomRightRadius1,
                mBottomRightRadius2, mBottomLeftRadius1, mBottomLeftRadius2};
        mShape = new RoundRectShape(mOuter, null, null);
    }

    public void setColor(int color) {
        mColor = color;
        mPaint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        mShape.draw(canvas, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return mPaint.getAlpha();
    }

    public float getTopLeftRadius2() {
        return mTopLeftRadius2;
    }

    public RoundRectShapeDrawable setTopLeftRadius2(float mTopLeftRadius2) {
        this.mTopLeftRadius2 = mTopLeftRadius2;
        return this;
    }

    public float getTopRightRadius2() {
        return mTopRightRadius2;
    }

    public RoundRectShapeDrawable setTopRightRadius2(float mTopRightRadius2) {
        this.mTopRightRadius2 = mTopRightRadius2;
        return this;
    }

    public float getBottomLeftRadius2() {
        return mBottomLeftRadius2;
    }

    public RoundRectShapeDrawable setBottomLeftRadius2(float mBottomLeftRadius2) {
        this.mBottomLeftRadius2 = mBottomLeftRadius2;
        return this;
    }

    public float getBottomRightRadius2() {
        return mBottomRightRadius2;
    }

    public RoundRectShapeDrawable setBottomRightRadius2(float mBottomRightRadius2) {
        this.mBottomRightRadius2 = mBottomRightRadius2;
        return this;
    }
}