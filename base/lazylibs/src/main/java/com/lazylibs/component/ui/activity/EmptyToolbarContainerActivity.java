package com.lazylibs.component.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import com.lazylibs.R;


/**
 */

public abstract class EmptyToolbarContainerActivity extends BaseToolbarActivity {

    protected TextView toolbarTitle;

    @Override
    public int layoutRes() {
        return R.layout.lazylibs_activity_toolbar_container;
    }

    @Override
    @CallSuper
    public void init(Bundle savedInstanceState) {
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getToolbarTitle());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initData() {

    }

    /**
     * get toolbar title
     *
     * @return string resource id
     */
    @NonNull
    protected abstract String getToolbarTitle();

}
