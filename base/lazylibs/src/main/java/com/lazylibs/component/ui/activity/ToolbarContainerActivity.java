package com.lazylibs.component.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.lazylibs.R;
import com.lazylibs.component.util.FragmentUtils;


/**
 */

public abstract class ToolbarContainerActivity extends BaseToolbarActivity {

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
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(
                new FragmentManager.FragmentLifecycleCallbacks() {
                    @Override
                    public void onFragmentViewCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull View v, Bundle savedInstanceState) {
                        super.onFragmentViewCreated(fm, f, v, savedInstanceState);
                        if (f instanceof DialogFragment || f.getView() == null) {
                            return;
                        }
                        if (f.getTag() != null) {
                            toolbarTitle.setText(f.getTag());
                        }
                    }
                }, false);
        FragmentUtils.replaceFragmentToActivity(R.id.container,
                getSupportFragmentManager(), newFragmentInstance(), getToolbarTitle());
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        updateTitle();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateTitle();
    }

    protected void updateTitle() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            FragmentManager.BackStackEntry entry =
                    getSupportFragmentManager().getBackStackEntryAt(count - 1);
            toolbarTitle.setText(entry.getName());
        } else if (count == 1) {
            toolbarTitle.setText(getToolbarTitle());
        }
    }

    /**
     * get toolbar title
     *
     * @return string resource id
     */
    @NonNull
    protected abstract String getToolbarTitle();

    /**
     * new fragment instance
     *
     * @return fragment instance
     */
    @NonNull
    public abstract Fragment newFragmentInstance();

}
