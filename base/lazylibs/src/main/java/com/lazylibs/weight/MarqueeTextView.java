package com.lazylibs.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class MarqueeTextView extends AppCompatTextView {

    private boolean isMarqueeEnable = false;

    private OnMarqueeCompleteListener marqueeCompleteListener;

    private long mTime = 0;
    private long longs = 0;

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setMarqueeEnable(boolean enable) {
        if (isMarqueeEnable != enable) {
            isMarqueeEnable = enable;
            if (enable) {
                setEllipsize(TextUtils.TruncateAt.MARQUEE);
            } else {
                setEllipsize(TextUtils.TruncateAt.END);
            }
            onWindowFocusChanged(enable);
        }
    }

    public boolean isMarqueeEnable() {
        return isMarqueeEnable;
    }

    @Override
    public boolean isFocused() {
        return isMarqueeEnable;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(isMarqueeEnable, direction, previouslyFocusedRect);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(isMarqueeEnable);
    }

    // 开始监听
    public void setOnMarqueeCompleteListener(OnMarqueeCompleteListener marqueeCompleteListener) {
        this.marqueeCompleteListener = marqueeCompleteListener;
        // 避免一些机子反应慢，postDelayed一下
        postDelayed(() -> {
            mTime = -1;
            longs = -1;
        }, 3000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mTime == -1) {
            mTime = System.currentTimeMillis();
        } else if (longs == -1) {
            long time = System.currentTimeMillis();
            longs = time - mTime;
            mTime = time;
            if (null != marqueeCompleteListener) {
                marqueeCompleteListener.onStart();
            }
        } else if (longs != 0) {
            long time = System.currentTimeMillis();
            long thisLong = time - mTime;
            mTime = time;
            if (thisLong > 10 * longs) {
                if (null != marqueeCompleteListener) {
                    marqueeCompleteListener.onMarqueeComplete();
                }
            }
        }
    }

    public interface OnMarqueeCompleteListener {

        void onStart();

        void onMarqueeComplete();

    }

}
