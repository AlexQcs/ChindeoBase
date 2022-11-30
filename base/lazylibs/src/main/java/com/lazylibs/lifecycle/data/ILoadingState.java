package com.lazylibs.lifecycle.data;

/**
 */

public interface ILoadingState {

    /**
     * show loading view
     */
    void onLoading();

    /**
     * show error view
     *
     * @param throwable Throwable
     */
    void onError(Throwable throwable);

    /**
     * show complete view
     */
    void onComplete();

}
