package com.lazylibs.component.ui.provider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.core.view.ActionProvider;

/**
 */

public abstract class BaseActionProvider extends ActionProvider {

    private MenuItem forItem;

    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public BaseActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView(MenuItem forItem) {
        this.forItem = forItem;
        return super.onCreateActionView(forItem);
    }

    /**
     * Factory method for creating new action views.
     *
     * @return A new action view.
     */
    @Override
    public View onCreateActionView() {
        View root = LayoutInflater.from(getContext()).inflate(layoutRes(),
                new FrameLayout(getContext()), false);
        initView(root, forItem);
        return root;
    }

    /**
     * get layout resource id
     *
     * @return layout Resource id;
     */
    @LayoutRes
    protected abstract int layoutRes();

    /**
     * init view
     *
     * @param root action provider rootView
     * @param item action provider menuItem
     */
    protected abstract void initView(View root, MenuItem item);

}
