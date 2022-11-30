package com.chindeo.repository;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.chindeo.repository.mmkv.ICache;
import com.lazylibs.http.HttpUtilFactory;
import com.lazylibs.lifecycle.LifecycleComponent;

import java.util.List;


public class RepositoryComponent {

    public volatile static Application INSTANCE;

    private volatile static Application.ActivityLifecycleCallbacks callbacks;

    private volatile static Boolean isRunInForeground = false;

    private RepositoryComponent() {
        // no INSTANCE
    }

    /**
     * 初始化Component工具
     *
     * @param app Application
     */
    public static void onCreate(Application app) {
        INSTANCE = app;
        if (isMain()) {
            LifecycleComponent.onCreate(app);
            if (callbacks == null) {
                INSTANCE.registerActivityLifecycleCallbacks(
                        callbacks = new Application.ActivityLifecycleCallbacks() {
                            @Override
                            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                                String name = getLauncherActivity(INSTANCE, activity.getPackageName());
                                if (name.equals(activity.getLocalClassName())) {
//                                    XgcConstants.init();
                                }
                            }

                            @Override
                            public void onActivityStarted(Activity activity) {

                            }

                            @Override
                            public void onActivityResumed(Activity activity) {
                                isRunInForeground = true;
                            }

                            @Override
                            public void onActivityPaused(Activity activity) {
                                isRunInForeground = false;
                            }

                            @Override
                            public void onActivityStopped(Activity activity) {

                            }

                            @Override
                            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                            }

                            @Override
                            public void onActivityDestroyed(Activity activity) {

                            }
                        });
            }
        }
    }

    /**
     * 销毁Component工具
     *
     * @param app Application
     */
    public static void onTerminate(Application app) {
        LifecycleComponent.onTerminate(app);
        ICache.onTerminate(app);
        INSTANCE.unregisterActivityLifecycleCallbacks(callbacks);
        INSTANCE = null;
        callbacks = null;
        isRunInForeground = null;
        HttpUtilFactory.clear();
    }

    public static Application getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException(RepositoryComponent.class.getSimpleName() + " must be use onCreate() and onTerminate() in Application");
        }
        return INSTANCE;
    }

    public static boolean isMain() {
        return isMain(INSTANCE);
    }

    /**
     * 判断是否是主进程
     *
     * @param application 当前的Application
     * @return 是否是主进程
     */
    private static boolean isMain(Application application) {
        if (application == null) {
            return false;
        }
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager mActivityManager = (ActivityManager) application.getSystemService(Context.ACTIVITY_SERVICE);
        if (mActivityManager.getRunningAppProcesses() != null) {
            for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
                if (appProcess.pid == pid) {
                    processName = appProcess.processName;
                    break;
                }
            }
        }
        String packageName = application.getPackageName();
        return processName.equals(packageName);
    }

    public static boolean isRunInForeground() {
        return isRunInForeground != null && isRunInForeground;
    }

    public static String getLauncherActivity(@NonNull Context context, @NonNull final String pkg) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setPackage(pkg);
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> info = pm.queryIntentActivities(intent, 0);
        if (info != null && info.size() > 0) {
            ResolveInfo next = info.iterator().next();
            if (next != null) {
                return next.activityInfo.name;
            }
        }
        return "no launcher activity of " + pkg;
    }


}
