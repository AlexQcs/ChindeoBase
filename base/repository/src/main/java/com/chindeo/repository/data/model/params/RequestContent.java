package com.chindeo.repository.data.model.params;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chindeo.repository.data.model.common.IPage;
import com.chindeo.repository.data.model.params.common.RepositoryParams;

import java.util.HashMap;
import java.util.TreeMap;

public class RequestContent extends HashMap<String, Object> {

    private RequestContent() {
        // no instance
    }

    public RequestContent append(String key, Object val) {
        put(key, val);
        return this;
    }

    /**
     * 构建请求参数
     *
     * @param extraParams 额外的请求参数类
     * @param <T>         请求参数类的聲明
     * @return {@link RequestContent} 请求参数
     */
    public static <T> RequestContent create(T extraParams) {
        return new Builder<T>()
                .setExtraParams(extraParams)
                .build();
    }


    /**
     * 创建{@link RepositoryParams}的公共参数的Map
     */
    public static RequestContent createRepositoryParams() {
        return new Builder()
                .setCommon(new RepositoryParams())
                .build();
    }

    /**
     * 创建{@link RepositoryParams}的公共参数的Map
     */
    public static <T> RequestContent createRepositoryParams(T extraParams) {
        return new Builder<T>()
                .setCommon(new RepositoryParams())
                .setExtraParams(extraParams)
                .build();
    }

    /**
     * 创建{@link RepositoryParams}的公共参数的Map
     */
    public static <T> RequestContent createRepositoryParams(T extraParams, IPage page) {
        return new RequestContent.Builder<T>()
                .setCommon(new RepositoryParams())
                .setExtraParams(extraParams)
                .build(page);
    }

    public static final class Builder<T> {

        private Object common;

        private T extraParams;

        public Builder<T> setCommon(Object common) {
            this.common = common;
            return this;
        }

        public Builder<T> setExtraParams(T extraParams) {
            this.extraParams = extraParams;
            return this;
        }

        public RequestContent build() {
            RequestContent map = new RequestContent();
            if (common != null) {
                map.putAll(JSON.parseObject(JSON.toJSONString(common),
                        new TypeReference<TreeMap<String, Object>>() {
                        }));
            }
            if (extraParams != null) {
                map.putAll(JSON.parseObject(JSON.toJSONString(extraParams),
                        new TypeReference<TreeMap<String, Object>>() {
                        }));
            }
            return map;
        }

        public RequestContent build(IPage page) {
            RequestContent map = build();
            if (page != null) {
                map.putAll(JSON.parseObject(JSON.toJSONString(page),
                        new TypeReference<TreeMap<String, Object>>() {
                        }));
            }
            return map;
        }
    }

}
