package com.study.util;

import com.alibaba.fastjson.JSON;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-03
 */
public class JsonUtil {
    private JsonUtil() {
    }

    public static Object convertJsonToObject(String json, Class<?> cls) {
        return JSON.parseObject(json, cls);
    }

    public static Object convertJsonToObject(String json) {
        return JSON.parse(json);
    }

    public static String convertObjectToJson(Object obj) {
        return JSON.toJSONString(obj);
    }
}
