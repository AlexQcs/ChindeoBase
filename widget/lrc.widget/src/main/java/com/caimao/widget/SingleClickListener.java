package com.caimao.widget;

import android.view.View;

/**
 * 项目名: HFL-ReCode
 * 包 名: lrc.core.app.listener
 * Copyright © 2018, CAIMAO.COM All Rights Reserved.
 * $Id$
 */

public abstract class SingleClickListener<iCallView extends View> implements View.OnClickListener {
    final static long CLICK_INTERVAL = 500L;
    protected long mLastClickTime = 0L;

    @Override
    public final void onClick(View v) {
        if (System.currentTimeMillis() - mLastClickTime < CLICK_INTERVAL) {
            return;
        }
        mLastClickTime = System.currentTimeMillis();
        click((iCallView) v);
    }

    public abstract void click(iCallView view);
}
