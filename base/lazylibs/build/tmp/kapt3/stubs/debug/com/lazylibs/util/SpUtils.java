package com.lazylibs.util;

import java.lang.System;

/**
 * 本地储类
 * Created by zqs on 2022/5/17
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0018\u0010\u000b\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\bJ\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u0011\u001a\u00020\u00122\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u0013\u001a\u00020\u00142\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u0015\u001a\u00020\u00162\b\u0010\t\u001a\u0004\u0018\u00010\nJ*\u0010\u0017\u001a\u0004\u0018\u00010\u0018\"\b\b\u0000\u0010\u0019*\u00020\u00182\b\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001bJ\u0010\u0010\u001c\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0018\u0010\u001f\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010 \u001a\u00020\u0001J\u001a\u0010!\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\"\u001a\u0004\u0018\u00010\u0018J\u001e\u0010#\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\n0\u001eJ\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\n0&J\u000e\u0010\'\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u0019\u0010(\u001a\u00020\u00062\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\n0*\u00a2\u0006\u0002\u0010+R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/lazylibs/util/SpUtils;", "", "()V", "mv", "Lcom/tencent/mmkv/MMKV;", "clearAll", "", "containKey", "", "key", "", "decodeBoolean", "def", "decodeBytes", "", "decodeDouble", "", "decodeFloat", "", "decodeInt", "", "decodeLong", "", "decodeParcelable", "Landroid/os/Parcelable;", "T", "tClass", "Ljava/lang/Class;", "decodeString", "decodeStringSet", "", "encode", "value", "encodeParcelable", "obj", "encodeSet", "sets", "getAllKeys", "", "removeValueForKey", "removeValuesForKeys", "keys", "", "([Ljava/lang/String;)V", "lazylibs_debug"})
public final class SpUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.lazylibs.util.SpUtils INSTANCE = null;
    private static com.tencent.mmkv.MMKV mv;
    
    private SpUtils() {
        super();
    }
    
    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param value
     */
    public final void encode(@org.jetbrains.annotations.Nullable()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.Object value) {
    }
    
    public final void encodeSet(@org.jetbrains.annotations.Nullable()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> sets) {
    }
    
    public final void encodeParcelable(@org.jetbrains.annotations.Nullable()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    android.os.Parcelable obj) {
    }
    
    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    public final int decodeInt(@org.jetbrains.annotations.Nullable()
    java.lang.String key) {
        return 0;
    }
    
    public final double decodeDouble(@org.jetbrains.annotations.Nullable()
    java.lang.String key) {
        return 0.0;
    }
    
    public final long decodeLong(@org.jetbrains.annotations.Nullable()
    java.lang.String key) {
        return 0L;
    }
    
    public final boolean decodeBoolean(@org.jetbrains.annotations.Nullable()
    java.lang.String key) {
        return false;
    }
    
    public final boolean decodeBoolean(@org.jetbrains.annotations.Nullable()
    java.lang.String key, boolean def) {
        return false;
    }
    
    public final float decodeFloat(@org.jetbrains.annotations.Nullable()
    java.lang.String key) {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] decodeBytes(@org.jetbrains.annotations.Nullable()
    java.lang.String key) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String decodeString(@org.jetbrains.annotations.Nullable()
    java.lang.String key) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> decodeStringSet(@org.jetbrains.annotations.Nullable()
    java.lang.String key) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final <T extends android.os.Parcelable>android.os.Parcelable decodeParcelable(@org.jetbrains.annotations.Nullable()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.Class<T> tClass) {
        return null;
    }
    
    /**
     * 清除所有key
     */
    public final void clearAll() {
    }
    
    /**
     * 获取所有key
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getAllKeys() {
        return null;
    }
    
    /**
     * 是否包含某个key
     */
    public final boolean containKey(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return false;
    }
    
    /**
     * 移除指定key的value
     */
    public final void removeValueForKey(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
    }
    
    /**
     * 移除指定key集合的value
     */
    public final void removeValuesForKeys(@org.jetbrains.annotations.NotNull()
    java.lang.String[] keys) {
    }
}