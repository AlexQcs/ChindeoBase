package com.lazylibs.util;

import androidx.fragment.app.FragmentActivity;

public interface IActivityGetter {
    /**
     * 返回可以弹出Fragment的Activity...
     *
     * @return
     */
    FragmentActivity requireActivity();
}