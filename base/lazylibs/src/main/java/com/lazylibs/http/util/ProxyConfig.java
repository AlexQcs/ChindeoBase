package com.lazylibs.http.util;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class ProxyConfig {

    private int maxRetryCount;

    private static final int MAX_RETRY_COUNT = 2;

    private final List<IProxyHandler> proxyHandlers = new ArrayList<>();

    public ProxyConfig() {
        this.maxRetryCount = MAX_RETRY_COUNT;
    }

    /**
     * 代理服务，设置接口重试的次数
     *
     * @param maxRetryCount 最大重试次数
     */
    public ProxyConfig(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    /**
     * 设置重试的测试
     *
     * @param maxRetryCount 重试的次数
     * @return ProxyConfig
     */
    public ProxyConfig setMexRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
        return this;
    }

    /**
     * @param handler 接口
     * @return ProxyConfig
     */
    public ProxyConfig addProxyHandler(IProxyHandler handler) {
        proxyHandlers.add(handler);
        return this;
    }

    public ProxyConfig clearProxyHandlerList() {
        proxyHandlers.clear();
        return this;
    }

    public int getMaxRetryCount() {
        return maxRetryCount;
    }

    public List<IProxyHandler> getProxyHandlers() {
        return proxyHandlers;
    }

}
