package com.lazylibs.component.ui.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lazylibs.component.ui.IContainer;
import com.lazylibs.component.ui.IViewBindingContainer;
import com.lazylibs.component.util.FragmentUtils;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

/**
 *
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements IContainer {


    /**
     * 获取布局的资源ID
     *
     * @return layout Resource id;
     */
    @LayoutRes
    protected abstract int layoutRes();


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalUtil.checkLanguage(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
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

    @Override
    public void initData() {

    }

}
