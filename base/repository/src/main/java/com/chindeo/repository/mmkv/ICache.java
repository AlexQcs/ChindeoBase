package com.chindeo.repository.mmkv;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.annotation.JSONField;
import com.chindeo.repository.util.AppConfigManager;
import com.tencent.mmkv.MMKV;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ICache {

    public static ICache face() {
        return create("face");
    }

    public static ICache defaults() {
        return create("defaults");
    }

    public static ICache settings() {
        return create("settings");
    }

    public static ICache module() {
        return create("module");
    }

    public static ICache accessToken() {
        return create("accessToken");
    }

    public static void onCreate(Application application) {
        try {
            String root = MMKV.initialize(application);
            Log.e("TAG", "MMKV.initialize ---------- " + root);
        }catch (Exception e){
            Log.e("TAG", "  mmkv 初始化错误 -- ");
        }
    }

    public static void onTerminate(Application application) {
        if (MAPS != null) {
            for (ICache iCache : MAPS.values()) {
                if (iCache != null) {
                    iCache.onTerminate();
                }
            }
            MAPS.clear();
            MAPS = null;
        }
        MMKV.onExit();
    }

    private static Map<String, ICache> MAPS;

    public static ICache create(@NonNull String id) {
        if (MAPS == null) {
            MAPS = new HashMap<>();
        }
        id = toId(id);
        if (!MAPS.containsKey(id)) {
            MAPS.put(id, new ICache(id));
        }
        return MAPS.get(id);
    }

    private static String toId(String id) {
        return String.format("chindeo.mmkv.%s", id);
    }

    @JSONField(serialize = false, deserialize = false)
    private MMKV mmkv;

    private ICache(String id) {
        if (AppConfigManager.get().mmkvMultiProcessMode) {
            mmkv = MMKV.mmkvWithID(id, MMKV.MULTI_PROCESS_MODE,
                    null, Environment.getExternalStorageDirectory() + "/mmkv_2");
        }else{
            mmkv = MMKV.mmkvWithID(id);
        }
    }


    @JSONField(serialize = false, deserialize = false)
    public String get(String key) {
        return getString(key, "");
    }

    @JSONField(serialize = false, deserialize = false)
    public MMKV core() {
        return mmkv;
    }

    /**
     * 写入 String
     *
     * @param key   键
     * @param value 值
     */
    @JSONField(serialize = false, deserialize = false)
    public void put(@NonNull final String key, @NonNull final String value) {
        put(key, value, false);
    }

    /**
     * SP 中写入 String
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    @JSONField(serialize = false, deserialize = false)
    public void put(@NonNull final String key,
                    @NonNull final String value,
                    final boolean isCommit) {
        mmkv.putString(key, value);
    }

    /**
     * SP 中读取 String
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code ""}
     */
    @JSONField(serialize = false, deserialize = false)
    public String getString(@NonNull final String key) {
        return getString(key, "");
    }

    /**
     * SP 中读取 String
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    @JSONField(serialize = false, deserialize = false)
    public String getString(@NonNull final String key, @NonNull final String defaultValue) {
        return mmkv.getString(key, defaultValue);
    }

    /**
     * SP 中写入 int
     *
     * @param key   键
     * @param value 值
     */
    @JSONField(serialize = false, deserialize = false)
    public void put(@NonNull final String key, final int value) {
        put(key, value, false);
    }

    /**
     * SP 中写入 int
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    @JSONField(serialize = false, deserialize = false)
    public void put(@NonNull final String key, final int value, final boolean isCommit) {
        mmkv.putInt(key, value);
    }

    /**
     * SP 中读取 int
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    @JSONField(serialize = false, deserialize = false)
    public int getInt(@NonNull final String key) {
        return getInt(key, -1);
    }

    /**
     * SP 中读取 int
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    @JSONField(serialize = false, deserialize = false)
    public int getInt(@NonNull final String key, final int defaultValue) {
        return mmkv.getInt(key, defaultValue);
    }

    /**
     * SP 中写入 long
     *
     * @param key   键
     * @param value 值
     */
    @JSONField(serialize = false, deserialize = false)
    public void put(@NonNull final String key, final long value) {
        put(key, value, false);
    }

    /**
     * SP 中写入 long
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    @JSONField(serialize = false, deserialize = false)
    public void put(@NonNull final String key, final long value, final boolean isCommit) {
        mmkv.putLong(key, value);
    }

    /**
     * SP 中读取 long
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    @JSONField(serialize = false, deserialize = false)
    public long getLong(@NonNull final String key) {
        return getLong(key, -1L);
    }

    /**
     * SP 中读取 long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    @JSONField(serialize = false, deserialize = false)
    public long getLong(@NonNull final String key, final long defaultValue) {
        return mmkv.getLong(key, defaultValue);
    }

    /**
     * SP 中写入 float
     *
     * @param key   键
     * @param value 值
     */
    @JSONField(serialize = false, deserialize = false)
    public void put(@NonNull final String key, final float value) {
        put(key, value, false);
    }

    /**
     * SP 中写入 float
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    @JSONField(serialize = false, deserialize = false)
    public void put(@NonNull final String key, final float value, final boolean isCommit) {
        mmkv.putFloat(key, value);
    }

    /**
     * SP 中读取 float
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    @JSONField(serialize = false, deserialize = false)
    public float getFloat(@NonNull final String key) {
        return getFloat(key, -1f);
    }

    /**
     * SP 中读取 float
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    @JSONField(serialize = false, deserialize = false)
    public float getFloat(@NonNull final String key, final float defaultValue) {
        return mmkv.getFloat(key, defaultValue);
    }

    /**
     * SP 中写入 boolean
     *
     * @param key   键
     * @param value 值
     */
    @JSONField(serialize = false, deserialize = false)
    public void put(@NonNull final String key, final boolean value) {
        put(key, value, false);
    }

    /**
     * SP 中写入 boolean
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    @JSONField(serialize = false, deserialize = false)
    public void put(@NonNull final String key, final boolean value, final boolean isCommit) {
        mmkv.putBoolean(key, value);
    }

    /**
     * SP 中读取 boolean
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    @JSONField(serialize = false, deserialize = false)
    public boolean getBoolean(@NonNull final String key) {
        return getBoolean(key, false);
    }

    /**
     * SP 中读取 boolean
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    @JSONField(serialize = false, deserialize = false)
    public boolean getBoolean(@NonNull final String key, final boolean defaultValue) {
        return mmkv.getBoolean(key, defaultValue);
    }

    /**
     * SP 中写入 String 集合
     *
     * @param key    键
     * @param values 值
     */
    @JSONField(serialize = false, deserialize = false)
    public void put(@NonNull final String key, @NonNull final Set<String> values) {
        put(key, values, false);
    }

    /**
     * SP 中写入 String 集合
     *
     * @param key      键
     * @param values   值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    @JSONField(serialize = false, deserialize = false)
    public void put(@NonNull final String key,
                    @NonNull final Set<String> values,
                    final boolean isCommit) {
        mmkv.putStringSet(key, values);
    }

    /**
     * SP 中读取 StringSet
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code Collections.<String>emptySet()}
     */
    @JSONField(serialize = false, deserialize = false)
    public Set<String> getStringSet(@NonNull final String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /**
     * SP 中读取 StringSet
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    @JSONField(serialize = false, deserialize = false)
    public Set<String> getStringSet(@NonNull final String key,
                                    @NonNull final Set<String> defaultValue) {
        return mmkv.getStringSet(key, defaultValue);
    }

    /**
     * SP 中获取所有键值对
     *
     * @return Map 对象
     */
    @JSONField(serialize = false, deserialize = false)
    public Map<String, ?> getAll() {
        return mmkv.getAll();
    }

    public String[] allKeys(){
        return mmkv.allKeys();
    }

    /**
     * SP 中是否存在该 key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    @JSONField(serialize = false, deserialize = false)
    public boolean contains(@NonNull final String key) {
        return mmkv.contains(key);
    }

    /**
     * SP 中移除该 key
     *
     * @param key      键
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    @JSONField(serialize = false, deserialize = false)
    public void remove(@NonNull final String key, final boolean isCommit) {
        mmkv.remove(key);
    }

    /**
     * SP 中移除该 key
     *
     * @param key 键
     */
    @JSONField(serialize = false, deserialize = false)
    public void remove(@NonNull final String key) {
        remove(key, false);
    }

    /**
     * SP 中清除所有数据
     *
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    @JSONField(serialize = false, deserialize = false)
    public void clear(final boolean isCommit) {
        mmkv.clear();
    }

    /**
     * SP 中清除所有数据
     */
    @JSONField(serialize = false, deserialize = false)
    public void clear() {
        clear(false);
    }

    private void onTerminate() {
        if (mmkv != null) {
            mmkv.commit();
        }
        mmkv = null;
    }

    public static void clearAllMMKVCache() {
        String root = MMKV.getRootDir();
        if (!TextUtils.isEmpty(root)) {
            File rootFile = new File(root);
            if (rootFile.exists()) {
                rootFile.delete();
            }
        }
    }

    public long size(){
        return mmkv.actualSize();
    }
}
