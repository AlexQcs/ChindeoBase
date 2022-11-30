package com.lazylibs.weight.skeleton;

import android.view.View;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;

/**空状态 布局
 *{LOADING,加载中  ERROR,错误  EMPTY,空状态  NO_INTERNET 无网络
 */

public class ViewSkeleton implements IViewSkeleton {

    private static final String TAG = ViewSkeleton.class.getName();

    private ViewReplacer mViewReplacer;

    private final View mActualView;

    private final View mErrorView;

    private final View mEmptyView;

    private final View mLoadingView;

    private final View mNoInternetView;

    private int viewState;

    private OnClickListener listener;

    private boolean isShow = false;

    public interface OnClickListener {
        void onClick(View view, @ViewState int viewState);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    private ViewSkeleton(Builder builder) {
        mActualView = builder.mActualView;
        mErrorView = builder.error;
        mEmptyView = builder.empty;
        mLoadingView = builder.loading;
        mNoInternetView = builder.noInternetView;
        mViewReplacer = new ViewReplacer(mActualView);
    }

    private View getClickView(@NonNull View clickView) {
        if (clickView instanceof NestedScrollView) {
            if (((NestedScrollView) clickView).getChildCount() > 0) {
                clickView = ((NestedScrollView) clickView).getChildAt(0);
            }
        } else if (clickView instanceof ScrollView) {
            if (((ScrollView) clickView).getChildCount() > 0) {
                clickView = ((ScrollView) clickView).getChildAt(0);
            }
        }
        return clickView;
    }

    public void showLoading(){
        show(ViewState.LOADING);
    }

    public void showError(){
        show(ViewState.ERROR);
    }
    public void showEmpty(){
        show(ViewState.EMPTY);
    }
    public void showNoInternet(){
        show(ViewState.NO_INTERNET);
    }

    /**
     * show skeleton
     *
     * @param viewState view state for unknown/error/empty/loading
     */
    @Override
    public void show(@ViewState int viewState) {
        View view = null;
        switch (viewState) {
            case ViewState.ERROR:
                view = mErrorView;
                break;
            case ViewState.EMPTY:
                view = mEmptyView;
                break;
            case ViewState.LOADING:
                view = mLoadingView;
                break;
            case ViewState.NO_INTERNET:
                view = mNoInternetView;
                break;
        }
        if (view != null) {
            getClickView(view)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.onClick(v, viewState);
                            }
                        }
                    });
            mViewReplacer.replace(view);
        }
        this.viewState = viewState;
        isShow = true;
    }

    /**
     * hide view skeleton
     */
    @Override
    public void hide() {
        if (isShow()) {
            mViewReplacer.restore();
            isShow = false;
        }
    }

    @Override
    public boolean isShow() {
        return isShow;
    }

    /**
     * current view state
     *
     * @return default {@link ViewState#LOADING}
     */
    @ViewState
    public int getViewState() {
        return viewState;
    }

    public static final class Builder {

        private View mActualView;
        private View error;
        private View empty;
        private View loading;
        private View noInternetView;

        public Builder(@NonNull View mActualView) {
            this.mActualView = mActualView;
        }

        public Builder error(View view) {
            this.error = view;
            return this;
        }

        public Builder empty(View view) {
            this.empty = view;
            return this;
        }

        public Builder loading(View view) {
            loading = view;
            return this;
        }

        public Builder noInternet(View view) {
            this.noInternetView = view;
            return this;
        }

        public ViewSkeleton build() {
            return new ViewSkeleton(this);
        }

    }

}
