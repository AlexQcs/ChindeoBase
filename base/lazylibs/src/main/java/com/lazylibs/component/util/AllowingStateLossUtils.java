package com.lazylibs.component.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class AllowingStateLossUtils {

    /**
     * Display the dialog, immediately adding the fragment to the given FragmentManager.  This
     * is a convenience for explicitly creating a transaction, adding the
     * fragment to it with the given tag, and calling {@link FragmentTransaction#commitNow()}.
     * This does <em>not</em> add the transaction to the fragment back stack.  When the fragment
     * is dismissed, a new transaction will be executed to remove it from
     * the activity.
     *
     * @param manager The FragmentManager this fragment will be added to.
     * @param tag     The tag for this fragment, as per
     *                {@link FragmentTransaction#add(Fragment, String) FragmentTransaction.add}.
     */
    public static Fragment showNow(FragmentManager manager, Fragment fragment, String tag) {
        try {
            Class c = Class.forName("androidx.fragment.app.DialogFragment");
            Constructor con = c.getConstructor();
            Object obj = con.newInstance();
            Field dismissed = c.getDeclaredField("mDismissed");
            dismissed.setAccessible(true);
            dismissed.set(obj, false);
            Field shownByMe = c.getDeclaredField("mShownByMe");
            shownByMe.setAccessible(true);
            shownByMe.set(obj, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(fragment, tag);
        ft.commitNowAllowingStateLoss();
        return fragment;
    }

    /**
     * Display the dialog, adding the fragment to the given FragmentManager.  This
     * is a convenience for explicitly creating a transaction, adding the
     * fragment to it with the given tag, and {@link FragmentTransaction#commit() committing} it.
     * This does <em>not</em> add the transaction to the fragment back stack.  When the fragment
     * is dismissed, a new transaction will be executed to remove it from
     * the activity.
     *
     * @param manager The FragmentManager this fragment will be added to.
     * @param tag     The tag for this fragment, as per
     *                {@link FragmentTransaction#add(Fragment, String) FragmentTransaction.add}.
     */
    public static Fragment show(FragmentManager manager, Fragment fragment, String tag) {
        try {
            Class c = Class.forName("androidx.fragment.app.DialogFragment");
            Constructor con = c.getConstructor();
            Object obj = con.newInstance();
            Field dismissed = c.getDeclaredField("mDismissed");
            dismissed.setAccessible(true);
            dismissed.set(obj, false);
            Field shownByMe = c.getDeclaredField("mShownByMe");
            shownByMe.setAccessible(true);
            shownByMe.set(obj, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!fragment.isAdded() && null == manager.findFragmentByTag(tag)) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(fragment, tag);
            ft.commitAllowingStateLoss();
        }
        return fragment;
    }

    public static Fragment showDialog(FragmentActivity parent, Fragment dialogFragment) {
        return show(parent.getSupportFragmentManager(), dialogFragment, dialogFragment.getClass().getSimpleName());
    }

    public static Fragment showDialog(Fragment parent, Fragment dialogFragment) {
        return show(parent.getChildFragmentManager(), dialogFragment, dialogFragment.getClass().getSimpleName());
    }


    public static void showDialog(DialogFragment parent, Fragment dialogFragment) {
        String TAG = dialogFragment.getClass().getSimpleName();
        FragmentManager fragmentManager;
        Fragment prent = parent.getParentFragment();
        if (prent != null) {
            fragmentManager = prent.getChildFragmentManager();
            parent.dismissAllowingStateLoss();
            show(fragmentManager, dialogFragment, TAG);
        } else {
            fragmentManager = parent.getFragmentManager();
            if (fragmentManager != null) {
                parent.dismissAllowingStateLoss();
                show(fragmentManager, dialogFragment, TAG);
            } else {
                fragmentManager = parent.getChildFragmentManager();
                show(fragmentManager, dialogFragment, TAG);
            }
        }
    }


    @Nullable
    public static Activity getActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return getActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    @Nullable
    public static FragmentActivity getFragmentActivity(Context context) {
        if (context instanceof FragmentActivity) {
            return (FragmentActivity) context;
        }
        if (context instanceof ContextWrapper) {
            return getFragmentActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

}