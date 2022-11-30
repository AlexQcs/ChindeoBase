package com.lazylibs.lifecycle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

import com.lazylibs.lifecycle.net.NetStateChangeObserver;
import com.lazylibs.lifecycle.net.NetworkType;

import java.util.ArrayList;
import java.util.List;

public class NetworkStateManager {

    private static class InstanceHolder {

        private static NetworkStateReceiver INSTANCE = new NetworkStateReceiver();

        static NetworkStateReceiver getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new NetworkStateReceiver();
            }
            return INSTANCE;
        }

        static void clear() {
            INSTANCE = null;
        }

    }

    private static class NetworkStateReceiver extends BroadcastReceiver {

        private List<NetStateChangeObserver> mObservers = new ArrayList<>();

        @Override
        public void onReceive(Context context, Intent intent) {
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                NetworkType networkType = getNetworkType(context);
                notifyObservers(networkType);
            }
        }

        /**
         * 通知所有的Observer网络状态变化
         */
        private void notifyObservers(NetworkType networkType) {
            if (networkType == NetworkType.DIS_CONNECT) {
                for (NetStateChangeObserver observer : mObservers) {
                    observer.onNetDisconnected();
                }
            } else {
                for (NetStateChangeObserver observer : mObservers) {
                    observer.onNetConnected(networkType);
                }
            }
        }

    }

    /**
     * 注册网络监听
     */
    static void registerReceiver(@NonNull Context context) {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(InstanceHolder.getInstance(), intentFilter);
    }

    /**
     * 取消网络监听
     */
    static void unregisterReceiver(@NonNull Context context) {
        context.unregisterReceiver(InstanceHolder.getInstance());
        InstanceHolder.clear();
    }

    /**
     * 注册网络变化Observer
     */
    public static void registerObserver(NetStateChangeObserver observer) {
        if (observer == null)
            return;
        if (!InstanceHolder.getInstance().mObservers.contains(observer)) {
            InstanceHolder.getInstance().mObservers.add(observer);
        }
    }

    /**
     * 取消网络变化Observer的注册
     */
    public static void unregisterObserver(NetStateChangeObserver observer) {
        if (observer == null)
            return;
        if (InstanceHolder.getInstance().mObservers == null)
            return;
        InstanceHolder.getInstance().mObservers.remove(observer);
    }

    public static NetworkType getNetworkType(Context context) {
        NetworkType netType;
        NetworkInfo info = getActiveNetworkInfo(context);
        if (info != null && info.isAvailable() && info.getState() == NetworkInfo.State.CONNECTED) {
            netType = NetworkType.CONNECT;
        } else {
            netType = NetworkType.DIS_CONNECT;
        }
        return netType;
    }

    /**
     * 获取活动网络信息
     * <p>需添加权限
     * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />}</p>
     *
     * @return NetworkInfo
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) return null;
        return manager.getActiveNetworkInfo();
    }

}
