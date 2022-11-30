package com.lazylibs.lifecycle;

import android.app.Activity;

import com.lazylibs.util.ActivityUtils;

import java.lang.ref.SoftReference;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 */

public final class ActivityManager {

    private CopyOnWriteArrayList<SoftReference<Activity>> mStack;

    private volatile static ActivityManager instance;

    private ActivityManager() {
        if (!LifecycleComponent.isInit()) {
            throw new RuntimeException("must be use LifecycleComponent.onCreate() and LifecycleComponent.onTerminate() in Application");
        }
        //no instance
        mStack = new CopyOnWriteArrayList<>();
    }

    public static ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity
     *
     * @param activity Activity
     * @return the boolean{@code true}: 成功<br>{@code false}: 失败
     */
    public boolean push(Activity activity) {
        return activity != null && mStack.add(new SoftReference<>(activity));
    }

    /**
     * 移除和退出Activity
     *
     * @param activity Activity
     * @return the boolean{@code true}: 成功<br>{@code false}: 失败
     */
    public boolean pop(Activity activity) {
        if (activity != null) {
            activity.finish();
        }
        //        Log.e("TAG", "pop -- " + is);
        return activity != null && null != findStackActivity(activity.getClass()) && mStack.remove(findStackActivity(activity.getClass()));
    }

    /**
     * 移除和退出ActivityManager内所有的Activity
     */
    public void popAll() {
        for (SoftReference<Activity> activity : mStack) {
            // ArrayList 允许空值
            if (isNotNull(activity)) {
                activity.get().finish();
            }
        }
        mStack.clear();
    }

    /**
     * 返回栈内的Activity的数量
     *
     * @return size
     */
    public int size() {
        return mStack.size();
    }

    /**
     * 获取栈顶的Activity
     *
     * @return activity or null
     */
    public Activity topOfStackActivity() {
        return mStack.size() > 0 && isNotNull(mStack.get(mStack.size() - 1)) ? mStack.get(mStack.size() - 1).get() : null;
    }

    /**
     * 获取栈底的Activity
     *
     * @return activity or null
     */
    public Activity bottomOfStackActivity() {
        return mStack.size()  > 0 && isNotNull(mStack.get(0)) ? mStack.get(0).get() : null;
    }

    /**
     * 根据Activity的类名获取栈内的Activity
     *
     * @param cls {@link Class#getClass()}
     * @return activity or null
     */
    public SoftReference<Activity> findStackActivity(Class<? extends Activity> cls) {
        if (cls == null || mStack == null || mStack.isEmpty()) {
            return null;
        }
        SoftReference<Activity> stackActivity = null;
        for (SoftReference<Activity> activity : mStack) {
            if (isNotNull(activity) && activity.get().getClass().equals(cls)) {
                stackActivity = activity;
            }
        }
        return stackActivity;
    }

    public boolean isActivityAlive(Class<? extends Activity> cls) {
        SoftReference<Activity> activity = findStackActivity(cls);
        if(activity == null) {
            return false;
        }
        return isNotNull(activity) && ActivityUtils.isActivityAlive(activity.get());
    }

    private Boolean isNotNull(SoftReference<Activity> softReference){
        return null != softReference && null != softReference.get();
    }

}
