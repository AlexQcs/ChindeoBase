package com.chindeo.repository.data.model.response.quick;

import android.app.Activity;
import android.content.Context;

/**
 * 模块通信的接口定义
 */
public interface OnModuleEventCallback {
    void onEvent(Context from, Class<? extends Activity> where, ModuleEvent data);
}
