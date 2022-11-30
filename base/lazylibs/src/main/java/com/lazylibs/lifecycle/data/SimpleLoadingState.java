package com.lazylibs.lifecycle.data;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lazylibs.lifecycle.ActivityManager;

/**
 */

public class SimpleLoadingState implements ILoadingState {

    private LoadingV4DialogFragment v4Fragment;

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
                ft.add(v4Fragment = LoadingV4DialogFragment.newInstance(),
                        LoadingV4DialogFragment.class.getSimpleName());
                ft.commitAllowingStateLoss();
            } else {
                android.app.FragmentManager fm = current.getFragmentManager();
                android.app.FragmentTransaction ft = fm.beginTransaction();
                ft.add(fragment = LoadingDialogFragment.newInstance(),
                        LoadingV4DialogFragment.class.getSimpleName());
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
            v4Fragment=null;
        }
        if (fragment != null) {
            fragment.dismissAllowingStateLoss();
            fragment=null;
        }
    }

    /**
     * show complete view
     */
    @Override
    public void onComplete() {
        if (v4Fragment != null) {
            v4Fragment.dismissAllowingStateLoss();
            v4Fragment=null;
        }
        if (fragment != null) {
            fragment.dismissAllowingStateLoss();
            fragment=null;
        }
    }

}
