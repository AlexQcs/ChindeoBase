package com.lazylibs.component.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lazylibs.component.ui.IContainer;


/**
 *
 */

public abstract class BaseSupportFragment extends Fragment implements IContainer {

    protected View root;

    private boolean isFirstLoadData = true;

    /**
     * 获取布局的资源ID
     *
     * @return layout Resource id;
     */
    @LayoutRes
    protected abstract int layoutRes();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return root = inflater.inflate(layoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && !onceLoadData()) {
            initData();
        }
    }

    public void loadData() {
        // view is nonnull or fragment is visible
        if (root != null) {
            if (isFirstLoadData) {
                initData();
                isFirstLoadData = false;
            } else if (!onceLoadData()) {
                initData();
            }
        }
    }

    /**
     * default true
     *
     * @return true:once loading false:loop loading
     */
    public boolean onceLoadData() {
        return true;
    }


}
