package com.lazylibs.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/**
 */
public final class LifecycleComponent {

    static ActivityLifecycleCallbacks callbacks;

    private LifecycleComponent() {
        // no instance
    }

    /**
     * 初始化Component工具
     *
     * @param app Application
     */
    public static void onCreate(Application app) {
        if (isMain(app)) {
            app.registerActivityLifecycleCallbacks(getCallbacks());
            NetworkStateManager.registerReceiver(app);
        }
    }

    /**
     * 销毁Component工具
     *
     * @param app Application
     */
    public static void onTerminate(Application app) {
        if (isMain(app)) {
            app.unregisterActivityLifecycleCallbacks(getCallbacks());
            NetworkStateManager.unregisterReceiver(app);
        }
    }

    private static ActivityLifecycleCallbacks getCallbacks() {
        if (callbacks == null) {
            callbacks = new SimpleActivityLifecycleCallbacks() {

                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    if (activity instanceof FragmentActivity) {
                        ((FragmentActivity) activity).getSupportFragmentManager()
                                .registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {

                                    @Override
                                    public void onFragmentDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                                        super.onFragmentDestroyed(fm, f);
                                        DisposableManager.getInstance().clear(f);
                                    }

                                }, true);
                    }
                    ActivityManager.getInstance().push(activity);
                }

                @Override
                public void onActivityResumed(Activity activity) {
                    super.onActivityResumed(activity);
                    ActivityManager.getInstance().push(activity);
                }

                @Override
                public void onActivityStopped(Activity activity) {
                    super.onActivityStopped(activity);
                    if (activity.isFinishing()) {
                        DisposableManager.getInstance().clear(activity);
                        ActivityManager.getInstance().pop(activity);
                    }
                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    DisposableManager.getInstance().clear(activity);
                    ActivityManager.getInstance().pop(activity);
                }

            };
        }
        return callbacks;
    }

    private static boolean isMain(Application application) {
        int pid = android.os.Process.myPid();
        String processName = "";
        android.app.ActivityManager mActivityManager = (android.app.ActivityManager) application.getSystemService(Context.ACTIVITY_SERVICE);
        for (android.app.ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                processName = appProcess.processName;
                break;
            }
        }
        String packageName = application.getPackageName();
        return processName.equals(packageName);
    }

    public static boolean isInit() {
        return callbacks != null;
    }

}
