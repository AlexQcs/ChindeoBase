package com.lazylibs.component.ui;

import android.os.Bundle;

import androidx.annotation.LayoutRes;

/**
 */
public interface IContainer {

    /**
     * 初始化
     *
     * @param savedInstanceState 实例的状态
     */
    void init(Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    void initData();

}
