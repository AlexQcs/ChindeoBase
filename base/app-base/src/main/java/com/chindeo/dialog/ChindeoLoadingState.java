package com.chindeo.dialog;

import android.app.Activity;

import com.lazylibs.lifecycle.ActivityManager;
import com.lazylibs.lifecycle.data.ILoadingState;
import com.lazylibs.lifecycle.data.LoadingDialogFragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 *
 */

public class ChindeoLoadingState implements ILoadingState {

    private ChindeoLoadingV4DialogFragment v4Fragment;

    private LoadingDialogFragment fragment;

    /**
     * show onLoading view
     */
    @Override
    public void onLoading() {
        Activity current = ActivityManager.getInstance().topOfStackActivity();
        if (current != null) {
            if (current instanceof FragmentActivity) {
                FragmentActivity activity = (FragmentActivity) current;
                FragmentManager fm = activity.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(v4Fragment = ChindeoLoadingV4DialogFragment.newInstance(),
                        ChindeoLoadingV4DialogFragment.class.getSimpleName());
                ft.commitAllowingStateLoss();
            } else {
                android.app.FragmentManager fm = current.getFragmentManager();
                android.app.FragmentTransaction ft = fm.beginTransaction();
                ft.add(fragment = LoadingDialogFragment.newInstance(),
                        ChindeoLoadingV4DialogFragment.class.getSimpleName());
                ft.commitAllowingStateLoss();
            }
        }
    }

    /**
     * show error view
     *
     * @param throwable Throwable
     */
    @Override
    public void onError(Throwable throwable) {
        if (v4Fragment != null) {
            v4Fragment.dismissAllowingStateLoss();
        }
        if (fragment != null) {
            fragment.dismissAllowingStateLoss();
        }
    }

    /**
     * show complete view
     */
    @Override
    public void onComplete() {
        if (v4Fragment != null) {
            v4Fragment.dismissAllowingStateLoss();
        }
        if (fragment != null) {
            fragment.dismissAllowingStateLoss();
        }
    }

}
