package com.lazylibs.weight.skeleton;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.lazylibs.weight.skeleton.ViewState.EMPTY;
import static com.lazylibs.weight.skeleton.ViewState.ERROR;
import static com.lazylibs.weight.skeleton.ViewState.LOADING;
import static com.lazylibs.weight.skeleton.ViewState.NO_INTERNET;

/**
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({LOADING, ERROR, EMPTY, NO_INTERNET})
public @interface ViewState {

    int LOADING = 0;

    int EMPTY = 1;

    int ERROR = 2;

    int NO_INTERNET = 3;

}
