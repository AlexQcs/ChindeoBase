package com.caimao.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by lazy2b on 18/3/27.
 */
@SuppressLint("AppCompatCustomView")
public class DragFloatActionButton extends ImageView {

    private int parentHeight;
    private int parentWidth;
    private FloatViewClickListener mListener;
    private float mStartX;
    private float mStartY;
    private int mStartRawX;
    private int mStartRawY;
    private int offsetX;
    private int offsetY;

    public DragFloatActionButton(Context context) {
        super(context);
    }

    public DragFloatActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public DragFloatActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    private int lastX;
    private int lastY;

    private boolean isDrag;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                setPressed(true);

                mStartRawX = (int) event.getRawX();
                mStartRawY = (int) event.getRawY();
                isDrag = false;
                getParent().requestDisallowInterceptTouchEvent(true);
                lastX = rawX;
                lastY = rawY;
                ViewGroup parent;
                if (getParent() != null) {
                    parent = (ViewGroup) getParent();
                    parentHeight = parent.getHeight();
                    parentWidth = parent.getWidth();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (parentHeight <= 0 || parentWidth == 0) {
                    isDrag = false;
                    break;
                } else {
                    isDrag = true;
                }
                offsetX = rawX - lastX;
                offsetY = rawY - lastY;

                //这里修复一些华为手机无法触发点击事件
                int distance = (int) Math.sqrt(offsetX * offsetX + offsetY * offsetY);
                if (distance == 0) {
                    isDrag = false;
                    break;
                }
                float x = getX() + offsetX;
                float y = getY() + offsetY;
                //检测是否到达边缘 左上右下
                x = x < 0 ? 0 : x > parentWidth - getWidth() ? parentWidth - getWidth() : x;
                y = getY() < 0 ? 0 : getY() + getHeight() > parentHeight ? parentHeight - getHeight() : y;
                setX(x);
                setY(y);
                lastX = rawX;
                lastY = rawY;
                Log.i("aa", "isDrag=" + isDrag + "getX=" + getX() + ";getY=" + getY() + ";parentWidth=" + parentWidth);
                break;
            case MotionEvent.ACTION_UP:
                if (!isNotDrag()) {
                    //恢复按压效果
                    setPressed(false);


                    //Log.i("getX="+getX()+"；screenWidthHalf="+screenWidthHalf);
//                    if (rawX >= parentWidth / 2) {
//                        //靠右吸附
//                        animate().setInterpolator(new DecelerateInterpolator())
//                                .setDuration(500)
//                                .xBy(parentWidth - getWidth() - getX())
//                                .start();
//                    } else {
//                        //靠左吸附
//                        ObjectAnimator oa = ObjectAnimator.ofFloat(this, "x", getX(), 0);
//                        oa.setInterpolator(new DecelerateInterpolator());
//                        oa.setDuration(500);
//                        oa.start();
//                    }

                }
                if ((Math.abs(lastX - mStartRawX) < 10 && Math.abs(lastY - mStartRawY) < 10)) {
                    mListener.onClick();
                }

                break;
        }
        //如果是拖拽则消s耗事件，否则正常传递即可。
        return !isNotDrag() || super.onTouchEvent(event);
    }

    private boolean isNotDrag() {
        return !isDrag && (getX() == 0
                || (getX() == parentWidth - getWidth()));
    }

    public interface FloatViewClickListener{
        void onClick();
    }

    public void setFVClickListener(FloatViewClickListener listener){
        mListener = listener;
    }
}