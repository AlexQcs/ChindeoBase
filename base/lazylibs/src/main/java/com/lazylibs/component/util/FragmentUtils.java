package com.lazylibs.component.util;


import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

/**
 */
public final class FragmentUtils {

    private FragmentUtils() {
        // no instance
    }

    /**
     * showFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     */
    public static void showFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                              Fragment fragment) {
        showFragmentToActivity(containerId, fragmentManager, fragment, null, null, 0);
    }

    /**
     * showFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param tag             标记
     */
    public static void showFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                              Fragment fragment, String tag) {
        showFragmentToActivity(containerId, fragmentManager, fragment, null, tag, 0);
    }

    /**
     * showFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param styleRes        transitionStyle资源ID
     */
    public static void showFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                              Fragment fragment, @StyleRes int styleRes) {
        showFragmentToActivity(containerId, fragmentManager, fragment, null, null, styleRes);
    }

    /**
     * showFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param state           {@link Lifecycle.State}
     */
    public static void showFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                              Fragment fragment, Lifecycle.State state) {
        showFragmentToActivity(containerId, fragmentManager, fragment, state, null, 0);
    }

    /**
     * showFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param state           {@link Lifecycle.State}
     * @param tag             标记
     * @param styleRes        transitionStyle资源ID
     */
    public static void showFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager, Fragment fragment,
                                              Lifecycle.State state, String tag, @StyleRes int styleRes) {
        if (!fragment.isAdded() && fragmentManager.findFragmentByTag(tag) ==null ) {
            addFragmentToActivity(containerId, fragmentManager, fragment, state, tag, styleRes);
        }
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (state != null) {
            ft.setMaxLifecycle(fragment, state);
        }
        ft.show(fragment);
        if (styleRes != 0) {
            ft.setTransitionStyle(styleRes);
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * hideFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     */
    public static void hideFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                              Fragment fragment) {
        hideFragmentToActivity(containerId, fragmentManager, fragment, null, null, 0);
    }

    /**
     * hideFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param tag             标记
     */
    public static void hideFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                              Fragment fragment, String tag) {
        hideFragmentToActivity(containerId, fragmentManager, fragment, null, tag, 0);
    }

    /**
     * hideFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param styleRes        transitionStyle资源ID
     */
    public static void hideFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                              Fragment fragment, @StyleRes int styleRes) {
        hideFragmentToActivity(containerId, fragmentManager, fragment, null, null, styleRes);
    }

    /**
     * hideFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param state           {@link Lifecycle.State}
     */
    public static void hideFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                              Fragment fragment, Lifecycle.State state) {
        hideFragmentToActivity(containerId, fragmentManager, fragment, state, null, 0);
    }

    /**
     * hideFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param state           {@link Lifecycle.State}
     * @param tag             标记
     * @param styleRes        transitionStyle资源ID
     */
    public static void hideFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager, Fragment fragment,
                                              Lifecycle.State state, String tag, @StyleRes int styleRes) {
        if (!fragment.isAdded()) {
            addFragmentToActivity(containerId, fragmentManager, fragment, state, tag, styleRes);
        }
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (state != null) {
            ft.setMaxLifecycle(fragment, state);
        }
        ft.hide(fragment);
        if (styleRes != 0) {
            ft.setTransitionStyle(styleRes);
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * addFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     */
    public static void addFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                             Fragment fragment) {
        addFragmentToActivity(containerId, fragmentManager, fragment, null, null, 0);
    }

    /**
     * addFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param tag             标记
     */
    public static void addFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                             Fragment fragment, String tag) {
        addFragmentToActivity(containerId, fragmentManager, fragment, null, tag, 0);
    }

    /**
     * addFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param styleRes        transitionStyle资源ID
     */
    public static void addFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                             Fragment fragment, @StyleRes int styleRes) {
        addFragmentToActivity(containerId, fragmentManager, fragment, null, null, styleRes);
    }

    /**
     * addFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param state           {@link Lifecycle.State}
     */
    public static void addFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                             Fragment fragment, Lifecycle.State state) {
        addFragmentToActivity(containerId, fragmentManager, fragment, state, null, 0);
    }

    /**
     * addFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param state           {@link Lifecycle.State}
     * @param tag             标记
     * @param styleRes        transitionStyle资源ID
     */
    public static void addFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager, Fragment fragment,
                                             Lifecycle.State state, String tag, @StyleRes int styleRes) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.remove(fragment);
        if (state != null) {
            ft.setMaxLifecycle(fragment, state);
        }
        if (tag == null) {
            ft.add(containerId, fragment);
        } else {
            ft.add(containerId, fragment, tag);
        }
        if (styleRes != 0) {
            ft.setTransitionStyle(styleRes);
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * addFragmentToBackStack
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     */
    public static void addFragmentToActivityBackStack(@IdRes int containerId, FragmentManager fragmentManager,
                                                      Fragment fragment) {
        addFragmentToActivityBackStack(containerId, fragmentManager, fragment, null);
    }

    /**
     * addFragmentToBackStack
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param tag             标记
     */
    public static void addFragmentToActivityBackStack(@IdRes int containerId, FragmentManager fragmentManager,
                                                      Fragment fragment, @Nullable String tag) {
        addFragmentToActivityBackStack(containerId, fragmentManager, fragment, tag, 0);
    }

    /**
     * addFragmentToBackStack
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param styleRes        transitionStyle资源ID
     */
    public static void addFragmentToActivityBackStack(@IdRes int containerId, FragmentManager fragmentManager,
                                                      Fragment fragment, @StyleRes int styleRes) {
        addFragmentToActivityBackStack(containerId, fragmentManager, fragment, null, styleRes);
    }

    /**
     * addFragmentToBackStack
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param tag             标记
     * @param styleRes        transitionStyle资源ID
     */
    public static void addFragmentToActivityBackStack(@IdRes int containerId, FragmentManager fragmentManager,
                                                      Fragment fragment, @Nullable String tag, @StyleRes int styleRes) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(containerId, fragment, tag);
        ft.addToBackStack(tag);
        if (styleRes != 0) {
            ft.setTransitionStyle(styleRes);
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * replaceFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     */
    public static void replaceFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                                 Fragment fragment) {
        replaceFragmentToActivity(containerId, fragmentManager, fragment, null, null, 0);
    }

    /**
     * replaceFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param tag             标记
     */
    public static void replaceFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                                 Fragment fragment, @Nullable String tag) {
        replaceFragmentToActivity(containerId, fragmentManager, fragment, null, tag, 0);
    }

    /**
     * replaceFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param styleRes        transitionStyle资源ID
     */
    public static void replaceFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                                 Fragment fragment, @StyleRes int styleRes) {
        replaceFragmentToActivity(containerId, fragmentManager, fragment, null, null, styleRes);
    }

    /**
     * replaceFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param state           {@link Lifecycle.State}
     */
    public static void replaceFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager,
                                                 Fragment fragment, Lifecycle.State state) {
        replaceFragmentToActivity(containerId, fragmentManager, fragment, state, null, 0);
    }



    /**
     * replaceFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     * @param state           {@link Lifecycle.State}
     * @param tag             标记
     * @param styleRes        transitionStyle资源ID
     */
    public static void replaceFragmentToActivity(@IdRes int containerId, FragmentManager fragmentManager, Fragment fragment,
                                                 Lifecycle.State state, @Nullable String tag, @StyleRes int styleRes) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        fragmentManager.executePendingTransactions();
        if (state != null) {
            ft.setMaxLifecycle(fragment, state);
        }
        if (tag == null) {
            ft.replace(containerId, fragment);
        } else {
            ft.replace(containerId, fragment, tag);
        }
        if (styleRes != 0) {
            ft.setTransitionStyle(styleRes);
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * replaceFragment
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例
     */
    public static void replaceFragmentToActivityBackStack(@IdRes int containerId, FragmentManager fragmentManager,
                                                          Fragment fragment) {
        replaceFragmentToActivity(containerId, fragmentManager, fragment, null, null, 0);
    }

    /**
     * replaceFragmentToBackStack
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例标记
     * @param tag             标记
     */
    public static void replaceFragmentToActivityBackStack(@IdRes int containerId, FragmentManager fragmentManager,
                                                          Fragment fragment, @Nullable String tag) {
        replaceFragmentToActivityBackStack(containerId, fragmentManager, fragment, null, tag, 0);
    }

    /**
     * replaceFragmentToBackStack
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例标记
     * @param styleRes        transitionStyle资源ID
     */
    public static void replaceFragmentToActivityBackStack(@IdRes int containerId, FragmentManager fragmentManager,
                                                          Fragment fragment, @StyleRes int styleRes) {
        replaceFragmentToActivityBackStack(containerId, fragmentManager, fragment, null, null, styleRes);
    }

    /**
     * replaceFragmentToBackStack
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例标记
     * @param state           {@link Lifecycle.State}
     */
    public static void replaceFragmentToActivityBackStack(@IdRes int containerId, FragmentManager fragmentManager,
                                                          Fragment fragment, Lifecycle.State state) {
        replaceFragmentToActivityBackStack(containerId, fragmentManager, fragment, state, null, 0);
    }

    /**
     * replaceFragmentToBackStack
     *
     * @param containerId     容器ID
     * @param fragmentManager Fragment管理器
     * @param fragment        fragment实例标记
     * @param state           {@link Lifecycle.State}
     * @param tag             标记
     * @param styleRes        transitionStyle资源ID
     */
    public static void replaceFragmentToActivityBackStack(@IdRes int containerId, FragmentManager fragmentManager, Fragment fragment,
                                                          Lifecycle.State state, @Nullable String tag, @StyleRes int styleRes) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        fragmentManager.executePendingTransactions();
        if (state != null) {
            ft.setMaxLifecycle(fragment, state);
        }
        ft.replace(containerId, fragment, tag);
        ft.addToBackStack(tag);
        if (styleRes != 0) {
            ft.setTransitionStyle(styleRes);
        }

        ft.commitAllowingStateLoss();
    }

    /**
     * 退出当前FragmentBackStack的一个实例
     *
     * @param fragmentManager Fragment管理器
     * @return true : success  false: fail
     */
    public static boolean popBackStack(FragmentManager fragmentManager) {
        boolean isBackSuccess = false;
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            isBackSuccess = true;
        }
        return isBackSuccess;
    }

    /**
     * 退出当前FragmentBackStack一个指tag的实例
     *
     * @param tag             标记
     * @param fragmentManager Fragment管理器
     * @return true : success  false: fail
     */
    public static boolean popBackStack(String tag, FragmentManager fragmentManager) {
        boolean isBackSuccess = false;
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            isBackSuccess = true;
        }
        return isBackSuccess;
    }

    /**
     * 退出当前所有FragmentBackStack
     *
     * @param fragmentManager Fragment管理器
     */
    public static void clearBackStack(FragmentManager fragmentManager) {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}
