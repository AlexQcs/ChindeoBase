package com.lazylibs.component.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * 唤醒监听广播
 */
public class BootBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            LocalBroadcastManager.getInstance(context)
                    .sendBroadcast(new Intent(ActionConstants.ACTION_BOOT_COMPLETED));
        }
    }

}
