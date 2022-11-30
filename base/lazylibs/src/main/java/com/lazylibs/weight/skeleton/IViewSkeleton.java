package com.lazylibs.weight.skeleton;

/**
 */

public interface IViewSkeleton {

    /**
     * show skeleton
     *
     * @param viewState view state for unknown/error/empty/loading
     */
    void show(@ViewState int viewState);

    void hide();

    /**
     * current view state
     *
     * @return default {@link ViewState#LOADING}
     */
    @ViewState
    int getViewState();

    /**
     * skeleton view is show
     *
     * @return true: show false: hide
     */
    boolean isShow();

}
