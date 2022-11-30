package com.lazylibs.component.ui.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.lazylibs.R;
import com.lazylibs.component.ui.IContainer;
import com.lazylibs.component.util.FragmentUtils;

/**
 */

public abstract class BaseToolbarActivity extends AppCompatActivity implements IContainer {

    protected Toolbar toolbar;


    /**
     * 获取布局的资源ID
     *
     * @return layout Resource id;
     */
    @LayoutRes
    protected int layoutRes(){
        return 0;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalUtil.checkLanguage(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        toolbar = findViewById(getToolbarId());
        if (toolbar != null) {
            toolbar.setContentInsetStartWithNavigation(0);
            toolbar.setContentInsetEndWithActions(0);
            toolbar.setContentInsetsRelative(0, 0);
            setSupportActionBar(toolbar);
            //noinspection ConstantConditions
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        init(savedInstanceState);
        initData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (FragmentUtils.popBackStack(getSupportFragmentManager())) {
            return true;
        }
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (FragmentUtils.popBackStack(getSupportFragmentManager())) {
            return;
        }
        super.onBackPressed();
    }

    @IdRes
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    public void initData() {

    }

}