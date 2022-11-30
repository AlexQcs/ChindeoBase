package com.lazylibs.http.util;

import android.util.Log;

import com.lazylibs.http.data.converter.FastJsonConverterFactory;

import org.reactivestreams.Publisher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by chenyp on 16/8/11.
 * the simple retrofit util
 */
public final class RetrofitUtil {

    private static OkHttpClient mOkHttpClient;

    private static Retrofit.Builder mBuilder;

    private static final long DEFAULT_HTTP_CONNECT_TIMEOUT = 20000L;// HTTP连接超时时间

    private static final long DEFAULT_HTTP_READ_TIMEOUT = 20000L;// HTTP读取超时时间

    private static final int DEFAULT_HTTP_MAX_CONNECT_COUNT = 10;// HTTP最大连接数

    private static final long DEFAULT_HTTP_KEEP_ALIVE_CONNECT_COUNT = 5;// HTTP保持KeepAlive的连接数

    private RetrofitUtil() {
        // no instance
    }

    /**
     * 获取Retrofit实例
     *
     * @param baseUrl http base url default
     * @return Retrofit
     */
    public static Retrofit get(String baseUrl) {
        if (baseUrl == null || baseUrl.length() == 0) {
            throw new NullPointerException("baseUrl is null or \"\".");
        }
        return getRetrofitBuilder().baseUrl(baseUrl)
                .client(getOkHttpClient())
                .build();
    }

    /**
     * 获取Retrofit实例
     *
     * @param baseUrl      http base url
     * @param okHttpClient OkHttpClient
     * @return Retrofit
     */
    public static Retrofit get(String baseUrl, OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            okHttpClient = getOkHttpClient();
        }
        Log.d("-------------",baseUrl);
        return getRetrofitBuilder().baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    /**
     * 获取RetrofitUtil内默认的OkHttpClient
     *
     * @return OkHttpClient
     */
    public static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            initClient(null);
        }
        return mOkHttpClient;
    }

    /**
     * 获取RetrofitUtil内默认的Retrofit.Builder
     *
     * @return Retrofit.Builder
     */
    public static Retrofit.Builder getRetrofitBuilder() {
        if (mBuilder == null) {
            initBuilder(null);
        }
        return mBuilder;
    }

    /**
     * 通过默认的Retrofit和OkHttpClient构建接口
     *
     * @param service 接口类 {@link Class<T>}
     * @param baseUrl http base url default
     * @param <T>     接口类名
     * @return 接口实例
     */
    public static <T> T create(Class<T> service, String baseUrl) {
        return get(baseUrl).create(service);
    }

    /**
     * 通过默认的Retrofit和OkHttpClient构建接口，并且通过判断
     *
     * @param service 接口类 {@link Class<T>}
     * @param baseUrl http base url default
     * @param config  配置
     * @param <T>     接口类名
     * @return 接口实例
     */
    public static <T> T create(Class<T> service, String baseUrl, final ProxyConfig config) {
        if (config != null) {
            //noinspection unchecked
            return (T) Proxy.newProxyInstance(service.getClassLoader(),
                    new Class<?>[]{service}, newInvocationHandler(config, create(service, baseUrl)));
        }
        return create(service, baseUrl);
    }

    /**
     * 初始化RetrofitUtil默认的{@link Retrofit.Builder}
     *
     * @param builder {@link Retrofit.Builder} default {@link RetrofitUtil#newDefaultRetrofitBuilder()}
     */
    public static void initBuilder(Retrofit.Builder builder) {
        mBuilder = builder == null ? newDefaultRetrofitBuilder() : builder;
    }

    /**
     * 初始化RetrofitUtil默认的{@link OkHttpClient.Builder}
     *
     * @param okHttpClient {@link RetrofitUtil#newDefaultOkHttpBuilder()}
     *                     {@link OkHttpClient.Builder#build()}
     */
    public static void initClient(OkHttpClient okHttpClient) {
        mOkHttpClient = okHttpClient == null ? newDefaultOkHttpBuilder().build() : okHttpClient;
    }

    /**
     * 获取新的{@link Retrofit.Builder}
     *
     * @return Retrofit.Builder
     */
    public static Retrofit.Builder newDefaultRetrofitBuilder() {
        return new Retrofit.Builder()
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    /**
     * 获取新的{@link Retrofit.Builder}
     *
     * @return Retrofit.Builder
     */
    public static Retrofit.Builder newDefaultRetrofitBuilderByNoConverter() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    /**
     * 获取新的{@link OkHttpClient.Builder}
     *
     * @return OkHttpClient.Builder
     */
    public static OkHttpClient.Builder newDefaultOkHttpBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(DEFAULT_HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectionPool(new ConnectionPool(DEFAULT_HTTP_MAX_CONNECT_COUNT
                        , DEFAULT_HTTP_KEEP_ALIVE_CONNECT_COUNT, TimeUnit.MINUTES));
    }

    /**
     * 通过动态代理去设置是否需要重新请求接口
     *
     * @param config  配置
     * @param service 接口
     * @return InvocationHandler
     */
    private static <T> InvocationHandler newInvocationHandler(final ProxyConfig config, final T service) {
        return new InvocationHandler() {

            private int retryCount = 0;

            @Override
            public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
                Object result;
                result = Flowable.just(service)
                        .flatMap(new Function<Object, Publisher<?>>() {
                            @Override
                            public Publisher<?> apply(Object o) throws Exception {
                                try {
                                    Object obj = method.invoke(o, args);
                                    if (obj instanceof Flowable) {
                                        return ((Flowable<?>) obj);
                                    } else if (obj instanceof Observable) {
                                        return ((Observable<?>) obj)
                                                .toFlowable(BackpressureStrategy.BUFFER);
                                    } else if (obj instanceof Single) {
                                        return ((Single<?>) obj).toFlowable();
                                    } else if (obj instanceof Completable) {
                                        return ((Completable) obj).toFlowable();
                                    } else if (obj instanceof Maybe) {
                                        return ((Maybe<?>) obj).toFlowable();
                                    }
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                                return Flowable.just(method.invoke(o, args));
                            }
                        })
                        .retryWhen(new Function<Flowable<Throwable>, Publisher<?>>() {
                            @Override
                            public Publisher<?> apply(Flowable<Throwable> throwableFlowable) throws Exception {
                                return throwableFlowable
                                        .flatMap(new Function<Throwable, Publisher<?>>() {
                                            @Override
                                            public Publisher<?> apply(Throwable throwable) throws Exception {
                                                if (config.getProxyHandlers().size() > 0) {
                                                    boolean isRetry = false;
                                                    if (retryCount < config.getMaxRetryCount()) {
                                                        for (IProxyHandler handler : config.getProxyHandlers()) {
                                                            if (handler.isRetry(throwable)) {
                                                                if (!isRetry) {
                                                                    isRetry = true;
                                                                }
                                                            }
                                                        }
                                                        retryCount++;
                                                        return Flowable.just(true);
                                                    }
                                                }
                                                return Flowable.error(throwable);
                                            }
                                        });
                            }
                        });
                return result;
            }
        };
    }

}
