package com.lazylibs.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * URl拼接
 */

public class GetUrlUtil {

    /**
     * 在指定URL后面拼接参数
     * @param url 网址
     * @param params get请求的Map参数
     */
    public static String getUrl(String url, Map<String, String> params) {
        // 添加url参数
        if (params != null&&params.size()>0) {
            Iterator<String> it = params.keySet().iterator();
            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                try {
                    String value = params.get(key);
                    if (sb == null) {
                        sb = new StringBuffer();
                        sb.append("?");
                    } else {
                        sb.append("&");
                    }
                    sb.append(key);
                    sb.append("=");
                    sb.append(value);
                }catch (Exception e){
                    //忽略
                }
            }
            url += Objects.requireNonNull(sb).toString();
        }
        return url;
    }
}
