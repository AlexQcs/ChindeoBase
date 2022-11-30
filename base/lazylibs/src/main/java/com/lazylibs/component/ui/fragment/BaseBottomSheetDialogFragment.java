package com.lazylibs.component.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.lazylibs.component.ui.IContainer;


public abstract class BaseBottomSheetDialogFragment extends BottomSheetDialogFragment implements IContainer {

    protected View root;

    private boolean isFirstLoadData = true;
    /**
     * 获取布局的资源ID
     *
     * @return layout Resource id;
     */
    @LayoutRes
    protected int layoutRes(){
        return 0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(layoutRes(), container, false);
        return root;
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

    private void loadData() {
        // view is nonnull or fragment is visible
        if (root != null && (isVisible() || isResumed())) {
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
