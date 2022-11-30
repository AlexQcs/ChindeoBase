package com.lazylibs.weight.skeleton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazylibs.R;

/**
 */

public class SimpleStateView implements Skeleton.IStateView {

    @Override
    public View onCreateView(Context context, ViewGroup container, int viewState) {
        int layoutRes = 0;
        switch (viewState) {
            case ViewState.EMPTY:
                layoutRes = R.layout.default_empty_layout;
                break;
            case ViewState.ERROR:
                layoutRes = R.layout.default_error_layout;
                break;
            case ViewState.LOADING:
                layoutRes = R.layout.default_loading_layout;
                break;
            case ViewState.NO_INTERNET:
                layoutRes = R.layout.default_no_internet_layout;
                break;
        }
        return LayoutInflater.from(context).inflate(layoutRes, container, false);
    }

}
