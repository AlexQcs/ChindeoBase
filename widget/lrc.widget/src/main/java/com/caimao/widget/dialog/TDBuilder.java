package com.caimao.widget.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.res.ColorStateList;
import android.graphics.Color;
import androidx.core.view.ViewCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caimao.widget.R;
import com.caimao.widget.SingleClickListener;
import com.caimao.widget.drawable.RoundRectDrawable;
import com.caimao.widget.drawable.StateRoundRectDrawable;
import com.lazylibs.util.ResUtils;
import com.lazylibs.util.SizeUtils;


@SuppressLint("NewApi")
public class TDBuilder {

    Dialog mDialog = null;

    public int[][] txtColorStates = new int[][]{{android.R.attr.state_pressed}, {}};
    public RoundRectDrawable bgTitle;
    public RoundRectDrawable bgBottom;
    public RoundRectDrawable bgNoTitleCxt;
    public StateRoundRectDrawable bgLeft;
    public StateRoundRectDrawable bgRight;
    public StateRoundRectDrawable bgSingleBtn;
    public ColorStateList txtColorLeft;
    public ColorStateList txtColorRight;

    protected int mCxtResId = -1;

    protected View mRoot;

    protected LayoutParams mLayoutParams;

    protected TDListener mListener;

    public interface TDListener {
        int BTN1_CLICK = 0101010;
        int BTN2_CLICK = 0101011;

        void onBtnClick(Dialog dialog, int which);
    }

    protected LinearLayout mCxtLl;
    protected LinearLayout mBottomLl;
    protected TextView mRightTv;
    protected TextView mLeftTv;
    protected TextView mTitleTv;
    protected TextView mMsgTv;

    protected void init() {
        mRoot = mDialog.getLayoutInflater().inflate(mCxtResId != -1 ? mCxtResId : R.layout.dialog_tips, null);
        mDialog.setContentView(mRoot);
        mLayoutParams = mDialog.getWindow().getAttributes();
        mLayoutParams.width = mDialog.getContext().getResources().getDisplayMetrics().widthPixels
                - SizeUtils.dp2px(mDialog.getContext(), 80);
        findView();
        initView();

    }

    public TDBuilder setWidth(boolean byHorizontal) {
        if (byHorizontal) {
            mLayoutParams.width = mDialog.getContext().getResources().getDisplayMetrics().widthPixels / 2;
        }
        return this;
    }

    int themeColor = Color.GRAY;

    @SuppressLint("NewApi")
    protected void initView() {
        Context cxt = mDialog.getContext();
        final float radious = 10.0f;

        try {
            themeColor = cxt.getResources().getColor(ResUtils.iColor(cxt, "colorPrimary"));
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("TDBuilder", "colorPrimary not found!!!");
        }
        final int txtColor = Color.BLACK;

        bgTitle = new RoundRectDrawable() {
            {
                setColor(themeColor);
                setRadius(radious, radious, 0, 0);
            }
        };
        bgBottom = new RoundRectDrawable() {
            {
                try {
                    setColor(cxt.getResources().getColor(ResUtils.iColor(cxt, "dividerColor")));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.w("TDBuilder", "dividerColor not found!!!");
                    setColor(Color.GRAY);
                }

                setRadius(0, 0, radious, radious);
            }
        };
        bgNoTitleCxt = new RoundRectDrawable() {
            {
                setColor(Color.WHITE);
                setRadius(radious, radious, 0, 0);
            }
        };
        bgLeft = new StateRoundRectDrawable(Color.WHITE, themeColor).setRadius(0, 0, radious, 0);
        bgRight = new StateRoundRectDrawable(Color.WHITE, themeColor).setRadius(0, 0, 0,
                radious);
        bgSingleBtn = new StateRoundRectDrawable(Color.WHITE, themeColor).setRadius(0, 0,
                radious, radious);
        txtColorLeft = new ColorStateList(txtColorStates, new int[]{Color.WHITE, txtColor});
        txtColorRight = new ColorStateList(txtColorStates, new int[]{Color.WHITE, themeColor});

        ViewCompat.setBackground(mTitleTv, bgTitle);
        ViewCompat.setBackground(mBottomLl, bgBottom);
        ViewCompat.setBackground(mLeftTv, bgLeft);
        ViewCompat.setBackground(mRightTv, bgRight);

        mLeftTv.setTextColor(txtColorLeft);
        mRightTv.setTextColor(txtColorRight);
    }

    public TDBuilder changeBtnStyleChange() {
        ViewCompat.setBackground(mLeftTv, bgLeft);
        ViewCompat.setBackground(mRightTv, bgRight);

        mLeftTv.setTextColor(txtColorRight);
        mRightTv.setTextColor(txtColorLeft);
        return this;
    }

    public TDBuilder setOnDismissListener(OnDismissListener disListener) {
        if (disListener != null) {
            mDialog.setOnDismissListener(disListener);
        }
        return this;
    }

    public TDBuilder setCancel(boolean can) {
        mDialog.setCancelable(can);
        mDialog.setCanceledOnTouchOutside(can);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T view(int resId) {
        return (T) mDialog.findViewById(resId);
    }

    private String str(Object args) {
        if (args instanceof Integer) {
            return mDialog.getContext().getString((Integer) args);
        } else if (args instanceof String) {
            return args.toString();
        }
        return null;
    }

    public TDBuilder setBtn(TextView tv, Object args) {
        if (args == null) {
            tv.setVisibility(View.GONE);
        } else {
            tv.setText(str(args));
        }
        return this;
    }

    public TDBuilder setBtns(Object btn1Args, Object btn2Args, TDListener listener) {
        mListener = listener;
        setBtn(mLeftTv, btn1Args);
        setBtn(mRightTv, btn2Args);
        return this;
    }

    public TDBuilder setBtns(Object btnArgs, TDListener listener) {
        return setBtns(null, btnArgs, listener);
    }

    public TDBuilder setBtn(Object btn1Args, TDListener listener) {
        return setBtns(null, btn1Args, listener);
    }

    public TDBuilder setCxtView(View view) {
        try {
            mCxtLl.removeAllViews();
            mCxtLl.addView(view, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public TDBuilder setCxtView(int layoutId) {
        return setCxtView(LayoutInflater.from(mDialog.getContext()).inflate(layoutId, mCxtLl));
    }

    public View getCxtView() {
        return mCxtLl;
    }

    public void setbtnLeftVisibility(boolean visibility) {
        if (visibility) {
            mLeftTv.setVisibility(View.VISIBLE);
        } else {
            mLeftTv.setVisibility(View.GONE);
        }

    }

    public TDBuilder setMsgLeft(Object args) {
        mMsgTv.setText(str(args));
        mMsgTv.setGravity(Gravity.CENTER | Gravity.LEFT);
        return this;
    }

    public TDBuilder setMsgRight(Object args) {
        mMsgTv.setText(str(args));
        mMsgTv.setGravity(Gravity.CENTER | Gravity.RIGHT);
        return this;
    }

    public TDBuilder setMsg(Object args) {
        mMsgTv.setText(str(args));
        return this;
    }

    public TDBuilder setTitleTxt(Object args) {
        mTitleTv.setText(str(args));
        mTitleTv.setVisibility(View.VISIBLE);
        return this;
    }

    @SuppressLint("Override")
    public TDBuilder create() {
        if (mCxtResId == -1) {
            if (mLeftTv.getVisibility() == View.GONE) {
                ViewCompat.setBackground(mRightTv, bgSingleBtn);
            }
            if (mTitleTv.getVisibility() == View.GONE) {
                ViewCompat.setBackground(mCxtLl, bgNoTitleCxt);
            }
        }
        return this;
    }

    public View findView(int id) {
        return mRoot.findViewById(id);
    }

    protected void findView() {

        mCxtLl = (LinearLayout) findView(R.id.ll_tips_cxt);
        mBottomLl = (LinearLayout) findView(R.id.ll_tips_bottom);
        mRightTv = (TextView) findView(R.id.tv_right);
        mLeftTv = (TextView) findView(R.id.tv_left);
        mTitleTv = (TextView) findView(R.id.tv_title_center);
        mMsgTv = (TextView) findView(R.id.tv_tips_cxt);

//        ViewUtils.inject(this, mRoot);
        (findView(R.id.tv_left)).setOnClickListener(
                new SingleClickListener<TextView>() {
                    @Override
                    public void click(TextView view) {
                        if (mListener != null) {
                            mListener.onBtnClick(mDialog, TDListener.BTN1_CLICK);
                            dismiss();
                        }
                    }
                });
        (findView(R.id.tv_right)).setOnClickListener(
                new SingleClickListener<TextView>() {
                    @Override
                    public void click(TextView view) {
                        if (mListener != null) {
                            mListener.onBtnClick(mDialog, TDListener.BTN2_CLICK);
                            dismiss();
                        }
                    }
                });

    }


    public TDBuilder setUnDismissBtnClickListener() {
        (findView(R.id.tv_left)).setOnClickListener(null);
        (findView(R.id.tv_left)).setOnClickListener(new SingleClickListener<TextView>() {
            @Override
            public void click(TextView view) {
                if (mListener != null) {
                    mListener.onBtnClick(mDialog, TDListener.BTN1_CLICK);
                }
            }
        });
        (findView(R.id.tv_right)).setOnClickListener(null);
        (findView(R.id.tv_right)).setOnClickListener(new SingleClickListener<TextView>() {
            @Override
            public void click(TextView view) {
                if (mListener != null) {
                    mListener.onBtnClick(mDialog, TDListener.BTN2_CLICK);
                }
            }
        });
        return this;
    }

    public TDBuilder(Context context, boolean cancelable, OnCancelListener cancelListener) {
        dismiss();
        mDialog = new Dialog(context, R.style.TDStyle);
        mDialog.setCancelable(cancelable);
        mDialog.setOnCancelListener(cancelListener);
        init();
    }

    public void show() {
        try {
            mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TDBuilder(Context context, int layoutId) {
        dismiss();
        mDialog = new Dialog(context, R.style.TDStyle);
        mCxtResId = layoutId;
        init();
    }

    public TDBuilder(Context context) {
        dismiss();
        mDialog = new Dialog(context, R.style.TDStyle);
        // mDialog.setCanceledOnTouchOutside(true);
        // mDialog.setCancelable(true);
        init();
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
