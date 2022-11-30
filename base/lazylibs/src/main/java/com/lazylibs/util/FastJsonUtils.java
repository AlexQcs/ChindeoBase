package com.lazylibs.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * fastJson工具类
 */
public class FastJsonUtils {


    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }


    /**
     *
     *
     * @param text json
     * @return  转换为数组
     */
    public static <T> Object[] toArray(String text) {
        return toArray(text, null);
    }

    /**
     *
     *
     * @param text Json
     * @param clazz
     * @return 转换为数组
     */
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    /**
     *
     *
     * @param text Json
     * @param clazz 转换对象
     * @return 转换为List
     */
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     *
     *
     * @return 将string转化为序列化的json字符串
     */
    public static Object textToJson(String text) {
        Object objectJson = JSON.parse(text);
        return objectJson;
    }

    /**
     *
     *
     * @param s json
     * @return json字符串转化为map
     */
    public static <K, V> Map<K, V> stringToCollect(String s) {
        Map<K, V> m = (Map<K, V>) JSONObject.parseObject(s);
        return m;
    }

    /**
     *
     *
     * @param jsonData json
     * @param clazz  对象
     * @return 转换JSON字符串为对象
     */
    public static Object convertJsonToObject(String jsonData, Class<?> clazz) {
        return JSONObject.parseObject(jsonData, clazz);
    }

    /**
     *
     *
     * @param m map
     * @return 将map转化为string
     */
    public static <K, V> String collectToString(Map<K, V> m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }


    /**
     * 功能描述：把JSON数据转换成指定的java对象
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return 指定的java对象
     */
    public static <T> T getJsonToBean(String jsonData, Class<T> clazz) {
        return JSON.parseObject(jsonData, clazz);
    }

    /**
     * 功能描述：把Object数据转换成指定的java对象
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return 指定的java对象
     */
    public static <T> T getJsonToBean(Object jsonData, Class<T> clazz) {
        return JSON.parseObject(Object2String(jsonData), clazz);
    }

    /**
     * 功能描述：把java对象转换成JSON数据
     *
     * @param object java对象
     * @return JSON数据
     */
    public static String getBeanToJson(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 功能描述：把JSON数据转换成指定的java对象列表
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return List<T>
     */
    public static <T> List<T> getJsonToList(String jsonData, Class<T> clazz) {
        return JSON.parseArray(jsonData, clazz);
    }

    /**
     * 功能描述：把JSON数据转换成较为复杂的List<Map<String, Object>>
     *
     * @param jsonData JSON数据
     * @return List<Map < String, Object>>
     */
    public static List<Map<String, Object>> getJsonToListMap(String jsonData) {
        return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    /**
     *
     *
     * @param obj 对象转json
     * @return List集合转换成json字符串
     */
    public static String list2Json(Object obj) {
        return JSONArray.toJSONString(obj, true);
    }

    /**
     *
     * (不需要实体类)
     * @param jsonStr json
     * @return object转List
     */
    public static JSONArray json2List(Object jsonStr) {
        return JSON.parseArray(Object2String(jsonStr));
    }

    /**
     * @param jsonStr  json
     * @return Object 转 json
     */
    public static JSONObject Object2json(Object jsonStr) {
        return JSONObject.parseObject(Object2String(jsonStr));
    }

    /**
     * @param jsonStr json
     * @return Object转JSON字符串
     */
    public static String Object2String(Object jsonStr) {
        return JSONObject.toJSONString(jsonStr);
    }


    public static String getData(Map data, String key) {
        String result = "";
        if (data != null && data.containsKey(key)) {
            result = data.get(key) + "";
            if (result.equals("null")) {
                result = "";
            }
        }
        return result;
    }


    /**
     *
     *
     * @param s json
     * @return json字符串转化为map
     */
    public static Map stringToMap(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }

    /**
     *
     *
     * @param m map
     * @return 将map转化为string
     */
    public static String mapToString(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }

    /**
     *
     *
     * @param jsonData json
     * @param clazz bean
     * @return 从服务器返回的json data下找list
     */
    public static <T> List<T> findListByKey(String jsonData, String key, Class<T> clazz) {
        Map map = FastJsonUtils.stringToMap(jsonData);
        String records = FastJsonUtils.getData(map, key);
        return FastJsonUtils.toList(records, clazz);
    }


}