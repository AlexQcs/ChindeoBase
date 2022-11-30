package com.lazylibs.lifecycle;

import android.app.Activity;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 *
 */

public class DisposableManager {

    private static volatile DisposableManager disposableManager;

    private ConcurrentHashMap<String, CompositeDisposable> map;

    private DisposableManager() {
        if (!LifecycleComponent.isInit()) {
            throw new RuntimeException("must be use LifecycleComponent.onCreate() and LifecycleComponent.onTerminate() in Application");
        }
        //no instance
        map = new ConcurrentHashMap<>();
    }

    /**
     * 创建单例类，提供静态方法调用
     *
     * @return DisposableManger
     */
    public static DisposableManager getInstance() {
        if (disposableManager == null) {
            synchronized (DisposableManager.class) {
                if (disposableManager == null) {
                    disposableManager = new DisposableManager();
                }
            }
        }
        return disposableManager;
    }

    private void init(String tag) {
        if (!map.containsKey(tag)) {
            map.put(tag, new CompositeDisposable());
        }
    }

    /**
     * 添加disposable到DisposableManager，并关联Activity{@link LifecycleComponent#callbacks}
     *
     * @param activity   Activity
     * @param disposable Disposable
     */
    public void add(Activity activity, Disposable disposable) {
        add(activity.toString(), disposable);
    }

    /**
     * 添加disposable到DisposableManager，并关联Fragment{@link LifecycleComponent#callbacks}
     *
     * @param fragment   support Fragment
     * @param disposable Disposable
     */
    public void add(Fragment fragment, Disposable disposable) {
        add(fragment.toString(), disposable);
    }

    /**
     * 添加多个disposable到DisposableManager，并关联Activity{@link LifecycleComponent#callbacks}
     *
     * @param activity   Activity
     * @param disposable Disposable
     */
    public void addAll(Activity activity, Disposable... disposable) {
        addAll(activity.toString(), disposable);
    }

    /**
     * 添加多个disposable到DisposableManager，并关联Fragment{@link LifecycleComponent#callbacks}
     *
     * @param fragment   support Fragment
     * @param disposable Disposable
     */
    public void addAll(Fragment fragment, Disposable... disposable) {
        addAll(fragment.toString(), disposable);
    }

    /**
     * 移除DisposableManager指定的disposable，并关联Activity{@link LifecycleComponent#callbacks}
     *
     * @param activity   Activity
     * @param disposable Disposable
     */
    public void remove(Activity activity, Disposable disposable) {
        remove(activity.toString(), disposable);
    }

    /**
     * 移除DisposableManager指定的disposable，并关联Fragment{@link LifecycleComponent#callbacks}
     *
     * @param fragment   support fragment
     * @param disposable Disposable
     */
    public void remove(Fragment fragment, Disposable disposable) {
        remove(fragment.toString(), disposable);
    }

    /**
     * 清除DisposableManager关联Activity以及FragmentManager内所有Fragment所有disposable{@link LifecycleComponent#callbacks}
     *
     * @param activity activity
     */
    public void clear(Activity activity) {
        if (activity instanceof FragmentActivity) {
            List<Fragment> list = ((FragmentActivity) activity)
                    .getSupportFragmentManager().getFragments();
            for (Fragment fragment : list) {
                clear(fragment);
            }
        }
        clear(activity.getClass().getSimpleName());
    }

    /**
     * 清除DisposableManager关联Fragment所有disposable{@link LifecycleComponent#callbacks}
     *
     * @param fragment support fragment
     */
    public void clear(Fragment fragment) {
        clear(fragment.toString());
    }


    /**
     * 清除DisposableManager所有disposable
     */
    public void clearAll() {
        for (CompositeDisposable disposable : map.values()) {
            disposable.dispose();
            disposable.clear();
        }
        map.clear();
    }

    /**
     * 添加指定tag的Disposable到DisposableManager
     *
     * @param tag        tag
     * @param disposable disposable
     */
    public void add(String tag, Disposable disposable) {
        clear(tag);
        init(tag);
        CompositeDisposable compositeDisposable = map.get(tag);
        if (compositeDisposable != null) {
            compositeDisposable.add(disposable);
        }
    }

    /**
     * 添加多个指定tag的Disposable到DisposableManager
     *
     * @param tag        tag
     * @param disposable disposable
     */
    public void addAll(String tag, Disposable... disposable) {
        init(tag);
        CompositeDisposable compositeDisposable = map.get(tag);
        if (compositeDisposable != null) {
            compositeDisposable.addAll(disposable);
        }
    }

    /**
     * 移除DisposableManager内指定tag的Disposable
     *
     * @param tag        tag
     * @param disposable disposable
     */
    public void remove(String tag, Disposable disposable) {
        init(tag);
        CompositeDisposable compositeDisposable = map.get(tag);
        if (compositeDisposable != null) {
            compositeDisposable.remove(disposable);
        }
    }

    /**
     * 移除DisposableManager内所有指定tag的Disposable
     *
     * @param tag tag
     */
    public void clear(String tag) {
        if (map.containsKey(tag)) {
            Log.i("DisposableManager", "clear: " + tag);
            CompositeDisposable compositeDisposable = map.get(tag);
            if (compositeDisposable != null) {
                compositeDisposable.dispose();
                compositeDisposable.clear();
            }
            map.remove(tag);
        }
    }

    public void clear(String... tags) {
        for (String tag : tags) {
            clear(tag);
        }
    }

}